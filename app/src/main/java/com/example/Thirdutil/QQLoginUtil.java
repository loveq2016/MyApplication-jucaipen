package com.example.Thirdutil;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 
 * Ӧ��QQ��¼������
 * 
 * @author YLF
 * 
 * 
 */

public class QQLoginUtil {

	public String token = ",";

	public Tencent tencen = null;

	private Context context;
	protected UserInfo userInfo;

	private static String APP_ID = "1105031327";

	private QQLoginUtil() {
	}

	private static QQLoginUtil instance = new QQLoginUtil();

	public static QQLoginUtil getInstance() {
		return instance;

	}

	public void initTencen(Context context) {

		tencen = Tencent.createInstance(APP_ID, context);
		this.context = context;
	}


	public  Tencent getTencenInstance(Context context){
		tencen = Tencent.createInstance(APP_ID, context);
		this.context = context;
		return  tencen;
	}

	/**
	 * @param activity
	 *            ��¼QQ
	 */
	public void qqLogin(Activity activity) {

		if (!tencen.isSessionValid()) {
			IUiListener loginListen = new BaseUiListener() {

				@Override
				protected void doComplete(JSONObject values) {
					if (values == null) {
						return;
					}
					try {
						int ret = values.getInt("ret");
						if (ret == 0) {
							String openId = values.getString("openid");
							String accessToken = values
									.getString("access_token");
							String expires = values.getString("expires_in");
							tencen.setOpenId(openId);
							tencen.setAccessToken(accessToken, expires);
							getUserInfo();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}

				@Override
				public void onCancel() {
					super.onCancel();
				}
			};
			// ��¼
			tencen.login(activity, "all", loginListen);
		} else {
			// ע��
			tencen.logout(activity);

		}

	}

	/*
	 * ��ȡ�û���Ϣ
	 */
	public void getUserInfo() {
		userInfo = new UserInfo(context, tencen.getQQToken());
		userInfo.getUserInfo(new IUiListener() {

			@Override
			public void onError(UiError arg0) {

			}

			@Override
			public void onComplete(Object arg0) {
				if (arg0 == null) {
					return;
				}
				JSONObject object = (JSONObject) arg0;
				try {
					@SuppressWarnings("unused")
					int ret = object.getInt("ret");
					String nickname = object.getString("nickname");
					@SuppressWarnings("unused")
					String gender = object.getString("gender");
					String figureurl_qq_1=object.getString("figureurl_qq_1");
					Intent intent=new Intent("com.receiverQQ");
					intent.putExtra("qqOpenId", tencen.getOpenId());
					intent.putExtra("nikName", nickname);
					intent.putExtra("userLogo", figureurl_qq_1);
					context.sendBroadcast(intent);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onCancel() {
			}
		});
	}

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {

			// Util.showResultDialog��MainActivity.this�� response.toString������
			// "��¼�ɹ�"��;


			doComplete((JSONObject) response);

		}

		protected void doComplete(final JSONObject values) {

		}

		@Override
		public void onError(UiError e) {


		}

		@Override
		public void onCancel() {


		}

	}

}
