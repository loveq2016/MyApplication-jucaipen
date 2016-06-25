package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidnetwork.R;
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
 * 注册
 */
public class Register extends Activity implements View.OnClickListener {
    private String loginUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/regin";
    private ImageView register_back;
    private EditText reg_phone;
    private EditText reg_code;
    private EditText reg_name;
    private EditText reg_password;
    private String codeUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/sendmobile";
    private EditText reg_twopassword;
    private EditText invite_code;
    private ImageButton btn_regiser;
    private ImageButton ibt_code;
    private Map<String, Object> map = new HashMap<>();
    private String phone;
    private String code;
    private String name;
    private String invites;
    private String password;
    private String twoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        init();
    }

    private void init() {
        register_back = (ImageView) findViewById(R.id.register_back);
        register_back.setOnClickListener(this);
        reg_phone = (EditText) findViewById(R.id.reg_phone);
        reg_code = (EditText) findViewById(R.id.reg_code);
        reg_name = (EditText) findViewById(R.id.reg_name);
        reg_password = (EditText) findViewById(R.id.reg_password);
        reg_twopassword = (EditText) findViewById(R.id.reg_twopassword);
        btn_regiser = (ImageButton) findViewById(R.id.btn_regiser);
        invite_code = (EditText) findViewById(R.id.invite_code);
        btn_regiser.setOnClickListener(this);
        ibt_code = (ImageButton) findViewById(R.id.ibt_code);
        ibt_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                this.finish();
                break;
            case R.id.btn_regiser:
                phone = reg_phone.getText().toString().trim();
                code = reg_code.getText().toString().trim();
                name = reg_name.getText().toString().trim();
                invites = invite_code.getText().toString().trim();
                password = reg_password.getText().toString().trim();
                twoPassword = reg_twopassword.getText().toString().trim();
                if (phone.length() <= 10) {
                    Toast.makeText(Register.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                } else if (code.length() <= 0) {
                    Toast.makeText(Register.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                } else if (name.length() <= 0) {
                    Toast.makeText(Register.this, "请输入用户名", Toast.LENGTH_SHORT).show();

                } else if (password.length() <= 0) {
                    Toast.makeText(Register.this, "请输入密码", Toast.LENGTH_SHORT).show();

                } else if (twoPassword.length() <= 0) {
                    Toast.makeText(Register.this, "请再次输入密码", Toast.LENGTH_SHORT).show();

                } else {
                    RegisgerPerson(phone, code, name, invites, password, twoPassword);
                }

                break;

            case R.id.ibt_code:
                String phone = reg_phone.getText().toString();
                if (phone.length() >= 11) {
                    GetCode(phone);
                } else {
                    Toast.makeText(Register.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }


                break;
            default:
                break;
        }

    }

    private void GetCode(String phone) {
        map.clear();
        map.put("telPhone", phone);
        map.put("msgId", 1);
        RequestParams params = NetUtils.sendHttpGet(codeUrl, map);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        String err_msg = object.getString("err_msg");
                        int ret_code = object.getInt("ret_code");

                        Toast.makeText(Register.this, err_msg, Toast.LENGTH_SHORT).show();
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

    private void RegisgerPerson(String phone, String code, String name, String invites, String password, String twoPassword) {

//        telPhone   手机号
//        checkCode   手机验证码
//        username  用户名
//        password   密码
//        reptPwd  确认密码
//        investCode   邀请码

        map.put("telPhone", phone);
        map.put("checkCode", code);
        map.put("userName", name);
        map.put("password", password);
        map.put("reptPwd", twoPassword);
        map.put("investCode", invites);
        RequestParams params = NetUtils.sendHttpGet(loginUrl, map);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                Toast.makeText(Register.this, "+++++++++++" + result, Toast.LENGTH_SHORT).show();
                Log.i("111", "onSuccess: " + result);

                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        String err_msg = object.getString("err_msg");
                        int ret_code = object.getInt("ret_code");
                        if (ret_code == 0) {
                            Toast.makeText(Register.this, err_msg, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Register.this, err_msg, Toast.LENGTH_SHORT).show();
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
}
