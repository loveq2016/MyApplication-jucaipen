package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/14.
 * <p/>
 * 提问
 */
public class AskdQuestion extends Activity implements View.OnClickListener {
    private ImageButton ask_back;
    private ImageButton question_ibt;
    private EditText edt_ask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_ask);
        initView();
    }

    private void initView() {
        ask_back = (ImageButton) findViewById(R.id.ask_back);
        ask_back.setOnClickListener(this);
        question_ibt = (ImageButton) findViewById(R.id.question_ibt);
        question_ibt.setOnClickListener(this);
        edt_ask= (EditText) findViewById(R.id.edt_ask);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ask_back:
                this.finish();
                break;
            case R.id.question_ibt:
                String data=edt_ask.getText().toString().trim();
                if (data!=null&&data.length()>0){
                    Toast.makeText(AskdQuestion.this, "提问成功", Toast.LENGTH_SHORT).show();
                    edt_ask.setText("");
                }else {
                    Toast.makeText(AskdQuestion.this, "请输入问题", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }
}
