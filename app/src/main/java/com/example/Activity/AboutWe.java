package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/15.
 * <p/>
 * 关于我们
 */
public class AboutWe extends Activity implements View.OnClickListener {
    private ImageButton about_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutwe);

        init();
    }

    private void init() {
        about_finish = (ImageButton) findViewById(R.id.about_finish);
        about_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_finish:
                this.finish();
                break;
        }
    }
}
