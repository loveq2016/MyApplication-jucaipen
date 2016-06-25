package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/14.
 */
public class AskdQuestion extends Activity implements View.OnClickListener{
    private ImageView ask_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_ask);
        initView();
    }

    private void initView() {
        ask_back= (ImageView) findViewById(R.id.ask_back);
        ask_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ask_back:
                this.finish();
                break;
        }

    }
}
