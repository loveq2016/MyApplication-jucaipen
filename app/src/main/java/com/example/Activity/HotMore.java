package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.HotAdapter;
import com.example.androidnetwork.R;
import com.example.model.Press;
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
 * Created by Administrator on 2016/6/6.
 * 今日热点更多
 */
public class HotMore extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv_hotmore;
    private HotAdapter hotAdapter;
    private int page = 1;
    private List<Press> list;
    private String hoturl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/gethotnews";
    private Map<String, Object> map = new HashMap<>();
    private ImageButton hot_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotmore);

        init();
    }

    private void init() {
        list = new ArrayList<>();
        lv_hotmore = (ListView) findViewById(R.id.lv_hotmore);
        lv_hotmore.setOnItemClickListener(this);
        hot_back = (ImageButton) findViewById(R.id.hot_back);
        hot_back.setOnClickListener(this);
        hotAdapter = new HotAdapter(this, list);
        lv_hotmore.setAdapter(hotAdapter);
        GetIdea();

    }

    private void GetIdea() {
        map.put("isIndex", 1 + "");
        map.put("page", page + "");
        RequestParams params = NetUtils.sendHttpGet(hoturl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    list = JsonUtil.getpress(result);
                    hotAdapter.setList(list);
                    hotAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hot_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();
        intent.setClass(this, HotCareful.class);
        startActivity(intent);
    }
}
