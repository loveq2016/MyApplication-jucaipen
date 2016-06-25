package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.adapter.InviteAdapter;
import com.example.model.Friend;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/23.
 * 我的邀请
 */
public class Invitefriend extends Activity implements View.OnClickListener {
    private ListView lv_invite;
    private InviteAdapter adapter;
    private Context context = Invitefriend.this;
    private TextView tv_invite;
    private String inviteUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getinvite";
    private ImageButton ibt_finish;
    private int page=1;
    private Map<String, Object> map=new HashMap<>();
    private List<Friend> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitefriend);

        init();
        GetInvitefriend();
    }

    private void GetInvitefriend() {
        map.put("userId",6750);
        map.put("page",page);
        RequestParams params= NetUtils.sendHttpGet(inviteUrl,map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                //[{"page":1,"totlePage":1,"id":39,"recommderDate":"2016-06-20 08:59:57.643",
                // "recommder":"telTest","recommderPhone":null,"recommderImage":"http:20166209940.jpg"}]

                if(result!=null){
                    list= JsonUtil.getinvite(result);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void init() {
        lv_invite = (ListView) findViewById(R.id.lv_invite);
        tv_invite = (TextView) findViewById(R.id.tv_invite);
        tv_invite.setOnClickListener(this);
        ibt_finish = (ImageButton) findViewById(R.id.ibt_finish);
        ibt_finish.setOnClickListener(this);
        adapter = new InviteAdapter(context,list);
        lv_invite.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_invite:
                intent.setClass(this, MyInvite.class);
                startActivity(intent);
                break;
            case R.id.ibt_finish:
                this.finish();
                break;
            default:
                break;
        }
    }
}
