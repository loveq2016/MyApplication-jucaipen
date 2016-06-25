package com.example.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/28.
 * 开通守护
 */
public class IntoGuard extends Activity implements View.OnClickListener {
    private ImageView guard_back;
    private ImageView iv_start;
    private View view;
    private ImageButton popwidow_iv;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intoguard);

        init();
    }

    private void init() {
        view = LayoutInflater.from(this).inflate(R.layout.guardpopwidow, null);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.guardpopwidow);
        popwidow_iv = (ImageButton) dialog.findViewById(R.id.popwidow_ivs);
        popwidow_iv.setOnClickListener(this);


        guard_back = (ImageView) findViewById(R.id.guard_back);
        guard_back.setOnClickListener(this);
        iv_start = (ImageView) findViewById(R.id.iv_start);
        iv_start.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guard_back:
                this.finish();
                break;
            case R.id.iv_start:
                if (!dialog.isShowing()) {
                    dialog.show();
                }
                break;
            case R.id.popwidow_ivs:
                dialog.dismiss();
                break;
            default:
                break;
        }
    }
}
