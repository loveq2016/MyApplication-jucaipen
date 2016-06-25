package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/4.
 * 天假银行卡界面
 */
public class AddBank extends Activity implements View.OnClickListener {
    private ImageButton bank_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbank);

        init();
    }

    private void init() {
        bank_back = (ImageButton) findViewById(R.id.bank_back);
        bank_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bank_back:
                this.finish();
                break;
        }
    }
}
