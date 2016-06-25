package com.example.androidnetwork.wxapi;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.utils.KeepNetwork;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import cn.sharesdk.wechat.utils.WXAppExtendObject;
import cn.sharesdk.wechat.utils.WXMediaMessage;

/** 微信客户端回调activity示例 */
public class WXEntryActivity extends Activity {

	private static final String APP_ID = "wx4e03910ab5dec31d";
	private IWXAPI api;
	public String path = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public Map<String, String> parames = new HashMap<String, String>();
	public static final String APP_SECRET = "59825b766d9eb9ed7afd07e33547e0f1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
		api = WXAPIFactory.createWXAPI(this, APP_ID, true);
		api.registerApp(APP_ID);
	}

	/**
	 * 处理微信发出的向第三方应用请求app message
	 * <p>
	 * 在微信客户端中的聊天页面有“添加工具”，可以将本应用的图标添加到其中 此后点击图标，下面的代码会被执行。Demo仅仅只是打开自己而已，但你可
	 * 做点其他的事情，包括根本不打开任何页面
	 */
	public void onGetMessageFromWXReq(WXMediaMessage msg) {
		Intent iLaunchMyself = getPackageManager().getLaunchIntentForPackage(
				getPackageName());
		startActivity(iLaunchMyself);
	}

	/**
	 * 处理微信向第三方应用发起的消息
	 * <p>
	 * 此处用来接收从微信发送过来的消息，比方说本demo在wechatpage里面分享
	 * 应用时可以不分享应用文件，而分享一段应用的自定义信息。接受方的微信 客户端会通过这个方法，将这个信息发送回接收方手机上的本demo中，当作 回调。
	 * <p>
	 * 本Demo只是将信息展示出来，但你可做点其他的事情，而不仅仅只是Toast
	 */
	public void onShowMessageFromWXReq(WXMediaMessage msg) {
		if (msg != null && msg.mediaObject != null
				&& (msg.mediaObject instanceof WXAppExtendObject)) {
			WXAppExtendObject obj = (WXAppExtendObject) msg.mediaObject;
			Toast.makeText(this, obj.extInfo, Toast.LENGTH_SHORT).show();
		}
	}

	private void handleIntent(Intent intent) {
		SendAuth.Resp resp = new SendAuth.Resp(intent.getExtras());
		if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
			// 用户同意
			String code = resp.code;
			new getWeiXinOpenId().execute(code);
		} else {
			Toast.makeText(WXEntryActivity.this, "fail：" + resp.errCode, Toast.LENGTH_SHORT).show();
			return;
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		handleIntent(intent);
	}

	class getWeiXinOpenId extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				String weiXinCode = arg0[0];
				parames.put("appid", APP_ID);
				parames.put("secret", APP_SECRET);
				parames.put("code", weiXinCode);
				parames.put("grant_type", "authorization_code");
				byte bs[] = KeepNetwork.networkGet(path, parames);
				String openId = new String(bs, "UTF-8");
				return openId;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			try {
				JSONObject object = new JSONObject(result);
				String openId = object.getString("openid");
				Intent intent = new Intent("com.receiverWeiXin");
				intent.putExtra("weixinOpenId", openId);
				WXEntryActivity.this.sendBroadcast(intent);
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}

}
