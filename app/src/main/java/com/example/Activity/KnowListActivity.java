package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;

import com.example.adapter.KnowAdapter;
import com.example.androidnetwork.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.example.model.Know;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/12.
 * <p/>
 * 目录
 */
public class KnowListActivity extends Activity implements View.OnClickListener {

    private XRecyclerView know_xv;
    private KnowAdapter knowAdapter;
    private Map<String, Object> map = new HashMap<>();
    private String knowUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getknowlist";
    private int nId;
    private List<Know> list = new ArrayList<>();
    private ImageButton ibt_mulu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_knowlist);
        initView();
    }

    private void GetKnowList(final int nId) {
        map.put("typeId", nId);
        map.put("page", 1);
        RequestParams params = NetUtils.sendHttpGet(knowUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String title = object.getString("title");
                            int id = object.getInt("id");
                            Know know = new Know();
                            know.setId(id);
                            know.setClassId(nId);
                            know.setTitle(title);
                            list.add(know);
                        }
                        knowAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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

    private void initView() {
        nId = getIntent().getIntExtra("nId", -1);
        if (nId > 0) {
            GetKnowList(nId);
        }
        know_xv = (XRecyclerView) findViewById(R.id.know_xv);
        ibt_mulu = (ImageButton) findViewById(R.id.ibt_mulu);
        ibt_mulu.setOnClickListener(this);
        knowAdapter = new KnowAdapter(list);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        know_xv.setLayoutManager(lm);
        know_xv.setAdapter(knowAdapter);
        knowAdapter.setOnItemClickListener(new KnowAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, String position) {
                Intent intent = new Intent();
                int posi = new Integer(position);
                intent.putExtra("classId", list.get(posi).getClassId());
                intent.putExtra("id", list.get(posi).getId());
                intent.setClass(KnowListActivity.this, Knowlegdetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ibt_mulu:
                this.finish();
                break;
            default:
                break;
        }
    }
}
