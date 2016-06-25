package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/20.
 * <p/>
 * 资格认证
 */
public class StatusApprove extends Activity implements View.OnClickListener {

    private ImageView approve_finsh;
    private ImageButton btn_approve;
    private RelativeLayout status_type;
    private RelativeLayout station_job;
    private RelativeLayout status_code;
    private RelativeLayout on_work;
    private RelativeLayout big_field;
    private RelativeLayout rel_jianli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statusapprove);

        init();
    }

    private void init() {
        approve_finsh = (ImageView) findViewById(R.id.approve_finsh);
        approve_finsh.setOnClickListener(this);
        btn_approve = (ImageButton) findViewById(R.id.btn_approve);
        btn_approve.setOnClickListener(this);
        status_type = (RelativeLayout) findViewById(R.id.status_type);
        status_type.setOnClickListener(this);
        station_job = (RelativeLayout) findViewById(R.id.station_job);
        station_job.setOnClickListener(this);
        status_code = (RelativeLayout) findViewById(R.id.status_code);
        status_code.setOnClickListener(this);
        on_work = (RelativeLayout) findViewById(R.id.on_work);
        on_work.setOnClickListener(this);
        big_field = (RelativeLayout) findViewById(R.id.big_field);
        big_field.setOnClickListener(this);
        rel_jianli = (RelativeLayout) findViewById(R.id.rel_jianli);
        rel_jianli.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.approve_finsh:
                this.finish();
                break;
            case R.id.btn_approve:
                intent.setClass(this, SubmitApply.class);
                startActivity(intent);
                break;
            case R.id.status_type:
                Toast.makeText(StatusApprove.this, "资格证种类", Toast.LENGTH_SHORT).show();
                break;
            case R.id.station_job:
                Toast.makeText(StatusApprove.this, "所在岗位", Toast.LENGTH_SHORT).show();
                break;
            case R.id.status_code:
                Toast.makeText(StatusApprove.this, "资格号码", Toast.LENGTH_SHORT).show();
                break;
            case R.id.on_work:
                Toast.makeText(StatusApprove.this, "所在公司", Toast.LENGTH_SHORT).show();
                break;
            case R.id.big_field:
                Toast.makeText(StatusApprove.this, "擅长领域", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rel_jianli:
                Toast.makeText(StatusApprove.this, "个人简历", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
