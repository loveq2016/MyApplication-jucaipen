package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.utils.StringUntils;
import com.example.utils.TimeUtils;
import com.example.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Knowlegdetails extends Activity implements View.OnClickListener {
    private TextView know_details;
    private ImageButton deteil_back;
    private Map<String, Object> map = new HashMap<>();
    private String detaUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getknowdetails";

    private TextView konw_title;
    private TextView know_time;
    private TextView know_num;
    private TextView know_good;
    private TextView know_last;
    private TextView know_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowlegdetails);

        init();
    }

    private void init() {
        int id = getIntent().getIntExtra("id", -1);
        int classId = getIntent().getIntExtra("classId", -1);
        know_details = (TextView) findViewById(R.id.know_details);
        deteil_back = (ImageButton) findViewById(R.id.deteil_back);
        deteil_back.setOnClickListener(this);
        konw_title= (TextView) findViewById(R.id.konw_title);
        know_time= (TextView) findViewById(R.id.know_time);
        know_num= (TextView) findViewById(R.id.know_num);
        know_good= (TextView) findViewById(R.id.know_good);
        know_last= (TextView) findViewById(R.id.know_last);
        know_next= (TextView) findViewById(R.id.know_next);


        if (id > 0 && classId > 0) {
            GetDetailes(classId,id );
        }


    }

    private void GetDetailes(int classId, int id) {
        map.put("knowId", id);
        map.put("classId", classId);
        Toast.makeText(Knowlegdetails.this, ""+id+"    "+classId, Toast.LENGTH_SHORT).show();

        RequestParams params = NetUtils.sendHttpGet(detaUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                //{"title":"期货交易的基本特征","from":"聚财盆","insertDate":"2016-04-19 16:17:18.26",
                // "body":"方能进场交易。那些处在场外的广大客户若想参与期货交易，只能委托期货经纪公司代理
                // ","readNum":4,"goods":0,"nextId":-1,"nextTitle":"没有下一篇","lastId":-1,"lastTitle":"没有上一篇"}

                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        String title = object.optString("title", "");
                        String from = object.optString("from", "");
                        String insertDate = object.optString("insertDate", "");
                        String body = object.optString("body", "");
                        int readNum = object.optInt("readNum", -1);
                        int goods = object.optInt("goods", -1);
                        int nextId = object.optInt("nextId", -1);
                        String nextTitle = object.optString("nextTitle", "");
                        int lastId = object.optInt("lastId", -1);
                        String lastTitle = object.optString("lastTitle", "");

                        konw_title.setText(title);
                        //TimeUtils.parseStrDate(press.getInsertDate(), "yyyy-MM-dd HH:mm")
                        String time= TimeUtils.parseStrDate(insertDate,"yyyy-mm-dd HH:mm");
                        know_time.setText("知识来源：" + from + "   发布时间" + time);
                        know_num.setText("阅读数： " + readNum);
                        know_good.setText(" " + goods + "");
                        know_last.setText(lastTitle);
                        know_next.setText(nextTitle);


                        know_details.setText(Html.fromHtml(body));


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deteil_back:
                this.finish();
                break;
        }
    }
}
