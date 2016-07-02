package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.adapter.SchoolAdapter;
import com.example.androidnetwork.R;
import com.example.model.SchoolKnowledge;
import com.example.model.SmallType;
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
 * Created by Administrator on 2016/5/30.
 * <p/>
 * 证券
 */
public class SchoolSecurity extends Activity implements View.OnClickListener {
    private ExpandableListView lv_zq;
    private SchoolAdapter adapter;
    private List<List<SmallType>> childKnow = new ArrayList<>();
    private List<SchoolKnowledge> parentKnowledges = new ArrayList<>();
    private String path = "http://192.168.1.134:8080/Jucaipen/jucaipen/getbasetype?bigId=0";
    private String studyUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getbasetype";
    private Map<String, Object> map = new HashMap<>();
    private int btnId;
    private ImageButton ibt_zq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schoolsecurity);
        initView();
    }

    private void initView() {
        btnId = getIntent().getIntExtra("buttonId", -1);

        //获取分类
        if (btnId > 0) {
            GetType(btnId);
        }
        ibt_zq = (ImageButton) findViewById(R.id.ibt_zq);
        ibt_zq.setOnClickListener(this);
        lv_zq = (ExpandableListView) findViewById(R.id.lv_zq);
        lv_zq.setGroupIndicator(null);
        adapter = new SchoolAdapter(parentKnowledges, childKnow, this);
        lv_zq.setAdapter(adapter);
        lv_zq.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < parentKnowledges.size(); i++) {
                    if (groupPosition != i) {
                        lv_zq.collapseGroup(i);

                    }
                }

            }

        });

    }

    private void GetType(int buttonId) {
        map.clear();
        map.put("bigId", buttonId);
        map.put("leavel", 1);
        RequestParams params = NetUtils.sendHttpGet(studyUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    pareJson(result, btnId);

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

    public List<SchoolKnowledge> pareJson(String str, int bigId) {
        Log.i("111", "pareJson: " + str);
//        [{"id":2,"bigType":"初级教程","item":
//            [{"sId":21,"smallType":"基础知识"},{"sId":4,"smallType":"学习看盘"},{"sId":5,"smallType":"交易指南"}]},
//        {"id":19,"bigType":"中级教程","item":[{"sId":6,"smallType":"基本面分析"},{"sId":7,"smallType":"技术面分析"},
//            {"sId":23,"smallType":"技术指标"},{"sId":3,"smallType":"炒股技巧"},{"sId":8,"smallType":"实战操盘"}]},
//        ]{"id":20,"bigType":"高级教程","item":[{"sId":10,"smallType":"大师理论"},{"sId":11,"smallType":"老股民经验"},
//            {"sId":22,"smallType":"炒股心理学"}]}]
        parentKnowledges.clear();
        childKnow.clear();
        try {
            JSONArray array = new JSONArray(str);
            for (int i = 0; i < array.length(); i++) {
                //id      bif\gType b   bb
                SchoolKnowledge sk = new SchoolKnowledge();
                JSONObject obj1 = array.getJSONObject(i);
                int sId = obj1.getInt("id");
                String bigType = obj1.getString("bigType");
                sk.setSmallId(sId);
                sk.setBigType(bigType);
                parentKnowledges.add(sk);



                //  sId
                JSONArray item = obj1.getJSONArray("item");
                List<SmallType> sts = new ArrayList<>();
                //  cs
                for (int j = 0; j < item.length(); j++) {
                    JSONObject obj2 = item.getJSONObject(j);
                    int xxId = obj2.getInt("sId");
                    String smallType = obj2.getString("smallType").trim();
                    SmallType st = new SmallType();
                    st.smallType = smallType;
                    st.xxId = xxId;
                    sts.add(st);
                }
                childKnow.add(sts);
                adapter.setParents(parentKnowledges);
                adapter.setChild(childKnow);
            }

            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_zq:
                this.finish();
                break;
        }
    }
}
