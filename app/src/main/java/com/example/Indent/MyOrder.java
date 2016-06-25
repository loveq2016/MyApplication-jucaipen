package com.example.Indent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.adapter.AllAdapter;
import com.example.androidnetwork.R;
import com.example.model.ChargeOrder;
import com.example.utils.JsonUtil;
import com.example.utils.StoreUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
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
 * Created by Administrator on 2016/6/2.
 * <p/>
 * 我的订单
 */
public class MyOrder extends FragmentActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageButton order_back;
    private XRecyclerView charge_list;
    private RadioGroup rp_order;
    private AllAdapter adapter;
    private RadioButton order_all;
    private String orderUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getchargerecoder";
    private RadioButton order_finish;
    private RadioButton order_unfinished;
    private  int uId;
    private Map<String, Object> map = new HashMap<>();
    private List<ChargeOrder> list = new ArrayList<>();
    private  int page=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder);
        init();
    }

    private void initData(Map<String,Object> mp) {
        RequestParams params = NetUtils.sendHttpGet(orderUrl, mp);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (result != null) {
                   list= JsonUtil.getmyorder(result);
                    adapter.setList(list);
                }
                adapter.notifyDataSetChanged();

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
        uId= StoreUtils.getUserInfo(this);
        adapter = new AllAdapter(list);
        order_back = (ImageButton) findViewById(R.id.order_back);
        order_back.setOnClickListener(this);

        rp_order = (RadioGroup) findViewById(R.id.rp_order);
        rp_order.setOnCheckedChangeListener(this);

        order_all = (RadioButton) findViewById(R.id.order_all);
        order_finish = (RadioButton) findViewById(R.id.order_finish);
        order_unfinished = (RadioButton) findViewById(R.id.order_unfinished);
        charge_list = (XRecyclerView) findViewById(R.id.charge_list);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        charge_list.setLayoutManager(lm);
        charge_list.setAdapter(adapter);
        order_all.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_back:
                this.finish();
                break;
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        map.clear();
        switch (checkedId) {
            case R.id.order_all:
                //已支付
                list.clear();
                map.put("userId",uId);
                map.put("page",page);
                map.put("state",2);
                initData(map);
                break;
            case R.id.order_finish:
                //未支付
                list.clear();
                map.put("userId",uId);
                map.put("page",page);
                map.put("state",1);
                initData(map);
                break;
            case R.id.order_unfinished:
                //支付失败
                list.clear();
                map.put("userId",uId);
                map.put("page",page);
                map.put("state",3);
                initData(map);
                break;
            default:
                break;
        }
    }
}
