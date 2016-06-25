package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/20.
 * <p/>
 * 提交申请
 */
public class SubmitApply extends Activity implements View.OnClickListener {
    private ImageButton comint_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submitapply);

        init();
    }

    private void init() {
        comint_back = (ImageButton) findViewById(R.id.comint_back);
        comint_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comint_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}
