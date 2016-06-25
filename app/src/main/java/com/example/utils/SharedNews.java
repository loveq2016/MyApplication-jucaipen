package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.Thirdutil.QQLoginUtil;
import com.example.Thirdutil.WeiXinLoginUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 * <p/>
 * 分享
 */
public class SharedNews {


    // 分享到QQ空间
    public static void shareQz(Activity activity, final Context context, String title, String body, String adressUrl, ArrayList<String> imageList) {
        Bundle bubdle = new Bundle();
        bubdle.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        bubdle.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
        bubdle.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, body);//选填
        bubdle.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, adressUrl);//必填
        bubdle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageList);
        Tencent mTencent = QQLoginUtil.getInstance().getTencenInstance(context);
        mTencent.shareToQzone(activity, bubdle, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "取消分享", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //QQ分享
    public static void shareQQ(Activity activity, final Context context, String title, String url) {
        Bundle bundle = new Bundle();
        bundle.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, title);
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
        Tencent mTencent = QQLoginUtil.getInstance().getTencenInstance(context);
        mTencent.shareToQQ(activity, bundle, new IUiListener() {
            @Override
            public void onComplete(Object o) {
                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(UiError uiError) {
                Toast.makeText(context, "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //分享给微信好友
    public static void shareWX(Context context, String title, String msg) {
        WXTextObject object = new WXTextObject();
        object.text = title;

        WXMediaMessage wxm = new WXMediaMessage();
        wxm.description = msg;
        wxm.mediaObject = object;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text";
        req.message = wxm;
        req.scene = SendMessageToWX.Req.WXSceneSession;
        IWXAPI api = WeiXinLoginUtils.getApi(context);
        api.sendReq(req);
    }

    //分享到微信朋友圈
    public static void shareWXz(Context context, String title, String msg) {
        WXTextObject object = new WXTextObject();
        object.text = title;

        WXMediaMessage wxm = new WXMediaMessage();
        wxm.description = msg;
        wxm.mediaObject = object;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "text";
        req.message = wxm;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        IWXAPI api = WeiXinLoginUtils.getApi(context);
        api.sendReq(req);
    }
}
