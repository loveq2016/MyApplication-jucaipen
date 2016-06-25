package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/18.
 * <p/>
 * 其他账号
 */
public class Otheraccount extends Activity implements View.OnClickListener {
    private ImageView other_finsh;
    private RelativeLayout login_xinlang;
    private RelativeLayout login_qq;
    private RelativeLayout login_weixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otheraccount);
        init();
    }

    private void init() {
        other_finsh = (ImageView) findViewById(R.id.other_finsh);
        other_finsh.setOnClickListener(this);
        login_qq= (RelativeLayout) findViewById(R.id.login_qq);
        login_qq.setOnClickListener(this);
        login_weixin= (RelativeLayout) findViewById(R.id.login_weixin);
        login_weixin.setOnClickListener(this);
        login_xinlang= (RelativeLayout) findViewById(R.id.login_xinlang);
        login_xinlang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.other_finsh:
                this.finish();
                break;
            case R.id.login_qq:
                Toast.makeText(Otheraccount.this, "QQ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_weixin:
                Toast.makeText(Otheraccount.this, "微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.login_xinlang:
                Toast.makeText(Otheraccount.this, "新浪微博", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
