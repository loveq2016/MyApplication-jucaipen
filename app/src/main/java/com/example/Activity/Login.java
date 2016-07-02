package com.example.Activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Thirdutil.QQLoginUtil;
import com.example.androidnetwork.R;
import com.example.utils.StoreUtils;
import com.example.Thirdutil.WeiXinLoginUtils;
import com.example.reciver.RecQQLogin;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/31.
 * 登录
 */
public class Login extends Activity implements View.OnClickListener {
    private ImageView login_back;
    private TextView tv_register;
    private TextView forget_pass;
    private String loginUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/login";
    private ImageButton btn_login;
    private EditText edt_phone;
    private EditText edt_passWord;
    private Map<String, Object> map = new HashMap<>();

    private ImageButton ibt_qq;
    private ImageButton ibt_xinlang;
    private ImageButton ibt_weixin;
    private RecQQLogin qqReceiver;
    private boolean isReginQq;
    private boolean isReginWeixin;
    private BroadcastReceiver weiXin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        init();
    }

    private void init() {

        ibt_qq = (ImageButton) findViewById(R.id.ibt_qq);
        ibt_qq.setOnClickListener(this);
        ibt_xinlang = (ImageButton) findViewById(R.id.ibt_xinlang);
        ibt_xinlang.setOnClickListener(this);
        ibt_weixin = (ImageButton) findViewById(R.id.ibt_weixin);
        ibt_weixin.setOnClickListener(this);

        login_back = (ImageView) findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        forget_pass = (TextView) findViewById(R.id.forget_pass);
        forget_pass.setOnClickListener(this);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_passWord = (EditText) findViewById(R.id.edt_passWord);
        btn_login = (ImageButton) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.login_back:
                this.finish();
                break;
            case R.id.tv_register:
                intent.setClass(this, Register.class);
                startActivity(intent);
                break;
            case R.id.forget_pass:
                intent.setClass(this, FoundPassword.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String phone = edt_phone.getText().toString().trim();
                String pass = edt_passWord.getText().toString().trim();
                if (phone.length() > 0 && pass.length() > 0) {
                    UserLogin(phone, pass);
                } else {
                    Toast.makeText(Login.this, "账号或密码有误", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.ibt_qq:
                Toast.makeText(Login.this, "QQ登录", Toast.LENGTH_SHORT).show();
                reginQQ();
                break;
            case R.id.ibt_xinlang:
                Toast.makeText(Login.this, "暂不支持登录", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_weixin:
                Toast.makeText(Login.this, "微信登录", Toast.LENGTH_SHORT).show();
                reginWeiXin();

                break;
            default:
                break;
        }
    }

    private void UserLogin(String phone, String pass) {
        map.put("userId", -1);
        map.put("userName", phone);
        map.put("password", pass);

        RequestParams params = NetUtils.sendHttpGet(loginUrl, map);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    //解析 保存数据即可
                    try {
                        JSONObject object = new JSONObject(result);
                        int ret_code = object.getInt("ret_code");
                        if (ret_code == 0) {
                            Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                            int uId = object.getInt("userId");
                            String userName = object.getString("userName");
                            Login.this.finish();
                            StoreUtils.saveUserDate(Login.this, userName);
                            StoreUtils.saveData(Login.this, uId);
                        } else {
                            Toast.makeText(Login.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void reginQQ() {
        // QQ登录
        QQLoginUtil qqLogin = QQLoginUtil.getInstance();
        qqLogin.initTencen(this);
        qqLogin.qqLogin(this);
        IntentFilter filter = new IntentFilter("com.receiverQQ");
        qqReceiver = new RecQQLogin() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String qqOpenId = arg1.getStringExtra("qqOpenId");
                if (qqOpenId.length() > 0) {
                    Toast.makeText(Login.this, "" + qqOpenId, Toast.LENGTH_SHORT).show();
                    // new qqLoginApplication(qqOpenId).execute(0);
                }
            }

        };
        this.registerReceiver(qqReceiver, filter);
        isReginQq = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (isReginQq) {
//            this.unregisterReceiver(qqReceiver);
//        }
//        if (isReginWeixin) {
//            this.unregisterReceiver(weiXin);
//        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void reginWeiXin() {
        // 微信登录
        WeiXinLoginUtils.getInstance(this).sendAuthMsg();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.receiverWeiXin");
        weiXin = new RecQQLogin() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String wexinOpenId = arg1.getStringExtra("weixinOpenId");
                if (wexinOpenId.length() > 0) {
                    Toast.makeText(Login.this, "unionid:" + wexinOpenId, Toast.LENGTH_SHORT).show();
                    // new qqLoginApplication(wexinOpenId).execute(1);
                }
            }
        };
        this.registerReceiver(weiXin, filter);
        isReginWeixin = true;

    }

    private void reginSINA() {
        // 新浪登录
    }

}
