package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/20.
 * 申请开通
 */
public class ApplyforDredge extends Activity implements View.OnClickListener {

    private ImageButton btn_next;
    private ImageButton ibt_attestation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applydredge);

        init();
    }

    private void init() {
        btn_next = (ImageButton) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);
        ibt_attestation = (ImageButton) findViewById(R.id.ibt_attestation);
        ibt_attestation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_next:
                intent.setClass(this, StatusApprove.class);
                startActivity(intent);
                break;
            case R.id.ibt_attestation:
                this.finish();
                break;
            default:
                break;
        }

    }
}
