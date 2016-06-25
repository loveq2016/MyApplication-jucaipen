package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.androidnetwork.R;
import com.example.utils.JsonUtil;
import com.example.adapter.AttentionAdapter;
import com.example.model.Attention;
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
 * 我的关注
 */
public class MyAttention extends Activity implements View.OnClickListener {

    private ListView action_lv;
    private Context context = MyAttention.this;
    private AttentionAdapter adapter;
    private String attenUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getattention";
    private ImageButton attention_back;
    private Map<String, Object> map = new HashMap<>();
    private List<Attention> list = new ArrayList<>();
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myattention);

        init();
        GetAttention();
    }

    private void GetAttention() {
        map.put("userId", 6750);
        map.put("type", 0);
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(attenUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                // [{"page":1,"totlePage":1,"id":62,"teacherImage":"http://img.jucaipen.com/jucjpg",
                // "nickName":"远清看市","isV":1,"notice":"主张攻守兼备，大波段的利润来自持股的耐心！",
                // "fans":151,"leavel":"CCTV产品讲师"}]

                if (result != null) {
                    list= JsonUtil.getAttention(result);
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

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

    private void init() {
        action_lv = (ListView) findViewById(R.id.action_lv);
        attention_back = (ImageButton) findViewById(R.id.attention_back);
        attention_back.setOnClickListener(this);
        adapter = new AttentionAdapter(context,list);
        action_lv.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attention_back:
                this.finish();
                break;
            default:
                break;
        }
    }
}
