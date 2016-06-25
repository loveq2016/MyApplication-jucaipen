package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
 * Created by Administrator on 2016/6/1.
 * <p/>
 * 找回密码
 */
public class FoundPassword extends Activity implements View.OnClickListener {
    private EditText find_account;
    private ImageButton ibt_find;
    private EditText found_code;
    private EditText found_newPass;
    private EditText found_surePass;
    private ImageView found_back;
    private ImageButton found_sure;
    private Map<String, Object> map = new HashMap<>();
    private String updatePass = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/sendmobile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foundpassword);

        init();
    }

    private void init() {
        found_sure = (ImageButton) findViewById(R.id.found_sure);
        found_sure.setOnClickListener(this);
        find_account = (EditText) findViewById(R.id.find_account);
        ibt_find = (ImageButton) findViewById(R.id.ibt_find);
        ibt_find.setOnClickListener(this);
        found_code = (EditText) findViewById(R.id.found_code);
        found_newPass = (EditText) findViewById(R.id.found_newPass);
        found_surePass = (EditText) findViewById(R.id.found_surePass);
        found_back = (ImageView) findViewById(R.id.found_back);
        found_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.found_sure:
                String account = find_account.getText().toString().trim();
                String code = found_code.getText().toString().trim();
                String pass = found_newPass.getText().toString().trim();
                String surePass = found_surePass.getText().toString().trim();


                break;
            case R.id.ibt_find:
                String phone = find_account.getText().toString().trim();
                if (phone.length() >= 11) {
                    UpdatePass(phone);
                } else {
                    Toast.makeText(FoundPassword.this, "请输入正确的电话", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.found_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    private void UpdatePass(String phone) {
        map.put("telPhone", phone);
        map.put("msgId", 3);

        RequestParams params = NetUtils.sendHttpGet(updatePass, map);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {

                    //Log.e("111", "onSuccess: " + result);
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
}
