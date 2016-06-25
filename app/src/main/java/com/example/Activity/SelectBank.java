package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/4.
 * 选择银行卡界面
 */
public class SelectBank extends Activity implements View.OnClickListener {
    private ImageButton ibt_add;
    private ImageButton select_ka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectbank);

        init();
    }

    private void init() {
        ibt_add = (ImageButton) findViewById(R.id.ibt_add);
        ibt_add.setOnClickListener(this);
        select_ka = (ImageButton) findViewById(R.id.select_ka);
        select_ka.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibt_add:
                intent.setClass(this, AddBank.class);
                startActivity(intent);
                break;
            case R.id.select_ka:
                this.finish();
                break;
        }
    }
}
