package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/19.
 * 修改密码
 */
public class UpdatePassword extends Activity implements View.OnClickListener {
    private ImageButton ibt_update;
    private String updatePrss = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/changepassword";
    private EditText old_password;
    private EditText password_code;
    private EditText update_code;
    private EditText new_password;
    private EditText sure_password;
    private Button update_sure;
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatepass);

        init();
    }

    private void init() {
        ibt_update = (ImageButton) findViewById(R.id.ibt_update);
        ibt_update.setOnClickListener(this);

        old_password = (EditText) findViewById(R.id.old_password);
        password_code = (EditText) findViewById(R.id.password_code);
        update_code = (EditText) findViewById(R.id.update_code);
        new_password = (EditText) findViewById(R.id.new_password);
        sure_password = (EditText) findViewById(R.id.sure_password);


        update_sure = (Button) findViewById(R.id.update_sure);
        update_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_update:
                this.finish();
                break;
            case R.id.update_sure:
                String oldpass = old_password.getText().toString().trim();
                String passcode = password_code.getText().toString().trim();
                String code = update_code.getText().toString().trim();
                String newPass = new_password.getText().toString().trim();
                String surePass = sure_password.getText().toString().trim();
                if (oldpass.length() <= 0) {
                    Toast.makeText(UpdatePassword.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                } else if (passcode.length() <= 11) {
                    Toast.makeText(UpdatePassword.this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                } else if (code.length() <= 0) {
                    Toast.makeText(UpdatePassword.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else if (newPass.length() <= 0) {
                    Toast.makeText(UpdatePassword.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                } else if (surePass.length() <= 0) {
                    Toast.makeText(UpdatePassword.this, "重复新密码", Toast.LENGTH_SHORT).show();
                } else if (!newPass.equals(surePass)) {
                    Toast.makeText(UpdatePassword.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    UpdataPress(oldpass,passcode,code,newPass,surePass);
                }


                break;
        }
    }

    private void UpdataPress(String oldpass, String passcode, String code, String newPass, String surePass) {
        map.put("userId", "");
        map.put("telephone", passcode);
        map.put("oldPwd", oldpass);
        map.put("actionCode", code);
        map.put("newPwd", newPass);
        map.put("reptPwd", surePass);

        RequestParams params = NetUtils.sendHttpGet(updatePrss, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {

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
