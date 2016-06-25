package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import com.example.adapter.InviteCodeAdaper;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/6/2.
 *
 * 我的邀请 邀请码界面
 */
public class MyInvite extends Activity implements View.OnClickListener {
    private GridView invite_grd;
    private ImageButton friend_back;
    private InviteCodeAdaper adaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinvite);

     //   init();
    }

    private void init() {
       /* adaper=new InviteCodeAdaper();
        invite_grd = (GridView) findViewById(R.id.invite_grd);
        friend_back = (ImageButton) findViewById(R.id.friend_back);
        friend_back.setOnClickListener(this);
        invite_grd.setAdapter(adaper);*/

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.friend_back:
                this.finish();
                break;
        }
    }
}
