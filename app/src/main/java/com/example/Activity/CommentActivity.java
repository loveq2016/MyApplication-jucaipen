package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/14.
 * <p/>
 * 评论详情
 */
public class CommentActivity extends Activity implements View.OnClickListener {
    private ImageButton comm_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentactivity);

        init();
    }

    private void init() {
        comm_back = (ImageButton) findViewById(R.id.comm_back);
        comm_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comm_back:
                this.finish();
                break;
        }
    }
}
