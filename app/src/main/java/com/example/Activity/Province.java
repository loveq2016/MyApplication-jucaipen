package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.ProviceAdapter;
import com.example.androidnetwork.R;
import com.example.model.City;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 * 省市区  获取省份
 */
public class Province extends Activity implements View.OnClickListener ,AdapterView.OnItemClickListener{
    private ImageButton ibt_province;
    private String provinceUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getprovince";
    private ProviceAdapter adapter;
    private List<City> list = new ArrayList<>();
    private ListView lv_province;
    private Context context=Province.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province);

        init();
        GetProvince();
    }

    private void GetProvince() {
        RequestParams params = NetUtils.sendHttpGet(provinceUrl, null);
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
                            int pager = object.getInt("pager");
                            int totlePager = object.getInt("totlePager");
                            String name = object.getString("name");
                            int id = object.getInt("id");
                            int ProSort = object.optInt("ProSort", -1);

                            City city = new City();
                            city.setId(id);
                            city.setPager(pager);
                            city.setTotlePager(totlePager);
                            city.setName(name);
                            city.setProSort(ProSort);
                            list.add(city);
                        }
                        adapter.notifyDataSetChanged();

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

    private void init() {
        ibt_province = (ImageButton) findViewById(R.id.ibt_province);
        ibt_province.setOnClickListener(this);
        lv_province = (ListView) findViewById(R.id.lv_province);
        lv_province.setOnItemClickListener(this);
        adapter = new ProviceAdapter(context, list);
        lv_province.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_province:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent();
        intent.putExtra("proiceId",list.get(position).getId());
        intent.putExtra("proice",list.get(position).getName());


        intent.setClass(this,CityTown.class);
        startActivity(intent);
        this.finish();

    }
}
