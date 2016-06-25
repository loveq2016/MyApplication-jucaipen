package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/1.
 * 热门搜素
 */
public class Hothunt extends Activity implements View.OnClickListener {
    private ImageButton hunt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hothunt);

        init();
    }

    private void init() {
        hunt_back = (ImageButton) findViewById(R.id.hunt_back);
        hunt_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hunt_back:
                this.finish();
                break;
        }

    }
}
