package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 更换手机号
 */
public class ChangePhone extends Activity implements View.OnClickListener {
    private ImageButton ibt_change;
    private EditText old_phone;
    private EditText old_code;
    private EditText new_phone;
    private EditText new_code;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changephone);

        init();
    }

    private void init() {
        ibt_change = (ImageButton) findViewById(R.id.ibt_change);
        ibt_change.setOnClickListener(this);
        old_phone = (EditText) findViewById(R.id.old_phone);
        old_code = (EditText) findViewById(R.id.old_code);
        new_phone = (EditText) findViewById(R.id.new_phone);
        new_code = (EditText) findViewById(R.id.new_code);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_change:
                this.finish();
                break;
            case R.id.btn_next:
                String oldPhone = old_phone.getText().toString().trim();
                String oldCode = old_code.getText().toString().trim();
                String newPhone = new_phone.getText().toString().trim();
                String newCode = new_code.getText().toString().trim();


                break;
        }
    }
}
