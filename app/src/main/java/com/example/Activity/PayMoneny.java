package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/3.
 * <p/>
 * 充值界面
 */
public class PayMoneny extends Activity implements View.OnClickListener {
    private ImageButton pay_back;
    private ImageButton pay_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymoneny);

        init();
    }

    private void init() {
        pay_back = (ImageButton) findViewById(R.id.pay_back);
        pay_back.setOnClickListener(this);
        pay_sure = (ImageButton) findViewById(R.id.pay_sure);
        pay_sure.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.pay_back:
                this.finish();
                break;
            case R.id.pay_sure:
                intent.setClass(this,PayType.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
