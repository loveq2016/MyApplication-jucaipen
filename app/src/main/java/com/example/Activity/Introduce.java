package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/1.
 * <p/>
 * 个人简介填写
 */
public class Introduce extends Activity implements View.OnClickListener {
    private ImageButton intro_tvback;
    private EditText edt_persondate;
    private TextView tv_count;
    private TextView indruce_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce);
        init();
    }

    private void init() {
        intro_tvback = (ImageButton) findViewById(R.id.intro_tvback);
        intro_tvback.setOnClickListener(this);
        edt_persondate = (EditText) findViewById(R.id.edt_persondate);
        edt_persondate.setOnClickListener(this);
        tv_count = (TextView) findViewById(R.id.tv_count);
        indruce_finish= (TextView) findViewById(R.id.indruce_finish);
        indruce_finish.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intro_tvback:
                this.finish();
                break;
            case R.id.edt_persondate:
                int len = edt_persondate.getText().toString().length();
                tv_count.setText(len + "" + "/200");
                break;
            case R.id.indruce_finish:
                Intent intent=new Intent();
                intent.putExtra("value",edt_persondate.getText().toString());
                this.setResult(100, intent);
                Introduce.this.finish();
                break;

            default:
                break;
        }

    }
}
