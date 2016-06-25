package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.GuardAdapter;
import com.example.androidnetwork.R;
import com.example.model.Attention;
import com.example.utils.JsonUtil;
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
 * Created by Administrator on 2016/5/19.
 * <p/>
 * 我的守护
 */
public class MyGuard extends Activity implements View.OnClickListener {
    private ListView lv_guard;
    private GuardAdapter adapter;
    private ImageButton shouhu_finish;
    private String guardUrl="http://"+ StringUntils.getHostName()+"/Jucaipen/jucaipen/getmyguardian";
    private Map<String, Object> map=new HashMap<>();
    private List<Attention> list = new ArrayList<>();
    private int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myguard);

        init();
        GetMyGuard();
    }

    private void GetMyGuard() {
        map.put("userId",6750);
        map.put("type",0);
        map.put("page",page);
        RequestParams params= NetUtils.sendHttpGet(guardUrl,map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result!=null){
                    //[{"page":1,"totlePage":1,"id":25,"startDate":"2016-06-16 17:59:07.07",
                    // "endDate":"2016-06-17 17:59:07.07","teacherImage":"http://img.j47.jpg",
                    // "nickName":"远清看市","leavel":"CCTV产品讲师","isV":1,
                    // "introduce":"<spne-h\">现任CCTV在线教学课堂《证券实战教学》栏目公开课讲师。</span>"}]
                    list= JsonUtil.getGuard(result);
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
        shouhu_finish = (ImageButton) findViewById(R.id.shouhu_finish);
        shouhu_finish.setOnClickListener(this);
        lv_guard = (ListView) findViewById(R.id.lv_guard);
        adapter = new GuardAdapter(this,list);
        lv_guard.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shouhu_finish:
                this.finish();
                break;
        }
    }
}
