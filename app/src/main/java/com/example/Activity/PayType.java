package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/3.
 * <p/>
 * 支付方式
 */
public class PayType extends Activity implements View.OnClickListener {
    private ImageButton paytype_back;
    private RelativeLayout select_ka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paytype);

        init();
    }

    private void init() {
        paytype_back = (ImageButton) findViewById(R.id.paytype_back);
        paytype_back.setOnClickListener(this);
        select_ka = (RelativeLayout) findViewById(R.id.select_ka);
        select_ka.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.paytype_back:
                this.finish();
                break;
            case R.id.select_ka:
                intent.setClass(this, SelectBank.class);
                startActivity(intent);
                break;
        }
    }
}
