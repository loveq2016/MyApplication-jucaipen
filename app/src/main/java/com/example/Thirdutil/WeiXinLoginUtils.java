package com.example.Thirdutil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * @author Administrator
 *         <p/>
 *         <p/>
 *         ΢�ŵ�¼
 */
public class WeiXinLoginUtils {
    private static final String APP_ID = "wx4e03910ab5dec31d";
    private static IWXAPI api;
    private static WeiXinLoginUtils weixin;

    private WeiXinLoginUtils() {

    }


    public static IWXAPI getApi(Context context) {
        api = WXAPIFactory.createWXAPI(context, APP_ID, true);
        return api;
    }

    public static WeiXinLoginUtils getInstance(Context context) {
        if (weixin == null) {
            weixin = new WeiXinLoginUtils();
            api = WXAPIFactory.createWXAPI(context, APP_ID, true);
            if (!api.isWXAppInstalled()) {
                //Log.i("111", "getInstance: " + "没有安装微信");
                Toast.makeText(context, "没有安装微信", Toast.LENGTH_SHORT).show();
            }
            api.registerApp(APP_ID);
        }
        return weixin;
    }

    public void sendAuthMsg() {
        SendAuth.Req authReq = new SendAuth.Req();
        authReq.scope = "snsapi_userinfo";
        authReq.state = "jucaipen";
        api.sendReq(authReq);
    }

}
