package com.example.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.PersonAdapter;
import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.utils.StringUntils;
import com.example.model.Famous;
import com.example.utils.NetUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/7.
 * <p/>
 * 人气名家 更多
 */
public class PersonMore extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ListView lv_person;
    private String personUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getfamous";
    private Map<String, Object> map = new HashMap<>();
    private PersonAdapter personAdapter;
    private List<Famous> list = new ArrayList<>();
    private int page = 1;
    private ImageButton person_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personmore);

        init();
    }

    private void init() {
        lv_person = (ListView) findViewById(R.id.lv_person);
        lv_person.setOnItemClickListener(this);
        person_finish = (ImageButton) findViewById(R.id.person_finish);
        person_finish.setOnClickListener(this);
        personAdapter = new PersonAdapter(this, list);
        lv_person.setAdapter(personAdapter);
        GetPerson();


    }

    private void GetPerson() {
        map.clear();
        map.put("isIndex", 1);
        map.put("userId", 0 + "");
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(personUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {
                    list = JsonUtil.getFamous(result);
                    personAdapter.setList(list);
                    personAdapter.notifyDataSetChanged();
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
            case R.id.person_finish:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, FamousPlain.class);
        startActivity(intent);
    }
}
