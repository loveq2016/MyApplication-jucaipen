package com.example.MyRecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.Activity.PayMoneny;
import com.example.androidnetwork.R;
import com.example.model.Moneny;
import com.example.utils.JsonUtil;
import com.example.utils.StoreUtils;
import com.example.adapter.MoneyAdapter;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.view.TestListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/21.
 * <p/>
 * 我的聚财币 积分 公共界面
 */
public class Mymoney extends Activity implements View.OnClickListener {
    private MoneyAdapter adapter;
    private TestListView lv_money;
    private TextView tv_titles;
    private TextView tv_help;
    private String myUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getaccountdetail";
    private ImageButton ibt_callback;
    private ScrollView scrollview;
    private ImageButton ibt_pay;
    private List<Moneny> list = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private TextView moneynumbers;
    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymoney);

        init();
    }

    private void GetmyMoneny( int state) {
        map.clear();
        map.put("userId", StoreUtils.getUserInfo(this));
        map.put("page", page);
        map.put("state", state);


        RequestParams params = NetUtils.sendHttpGet(myUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                //[{"page":1,"totlePage":1,"id":588,"insertDate":"2016-06-16 17:59:07.07",
                // "detailMoney":10.0,"type":0,"remark":"成为【远清看市】的守护者【1】天，账户积分+10",
                // "Integeral":27,"jucaiBills":68},


                // {"page":1,"totlePage":1,"id":585,
                // "insertDate":"2016-06-16 17:54:00.213","detailMoney":12.0,"type":0,
                // "remark":"打赏给名师：【明飞】，账户积分+12","Integeral":27,"jucaiBills":68}]

                if (result != null) {
                    list = JsonUtil.getBills(result);
                    adapter.setList(list);
                    int s= (int) map.get("state");
                    if (s == 1) {
                        //积分
                        moneynumbers.setText(list.get(0).getIntegeral() + "");
                    } else {
                        //聚财币
                        moneynumbers.setText(list.get(0).getJucaiBills() + "");
                    }

                    adapter.notifyDataSetChanged();

                    // [{"page":1,"totlePage":1,"id":588,"insertDate":"2016-06-16 17:59:07.07",
                    // "detailMoney":10.0,"type":0,"remark":"成为【远清看市】的守护者【1】天，账户积分+10"},


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
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        scrollview.smoothScrollTo(0, 0);
        tv_titles = (TextView) findViewById(R.id.tv_titles);
        tv_help = (TextView) findViewById(R.id.tv_help);
        ibt_pay = (ImageButton) findViewById(R.id.ibt_pay);
        int type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            //积分
            tv_titles.setText("我的积分");
            ibt_pay.setVisibility(View.GONE);
            GetmyMoneny(1);

        } else {
            //聚财币
            tv_titles.setText("我的聚财币");
            tv_help.setVisibility(View.GONE);
            ibt_pay.setOnClickListener(this);
            GetmyMoneny(0);
        }
        ibt_callback = (ImageButton) findViewById(R.id.ibt_callback);
        ibt_callback.setOnClickListener(this);

        moneynumbers = (TextView) findViewById(R.id.moneynumbers);
        //       moneynumbers.setText(list.get(0).getMoney()+"");
        lv_money = (TestListView) findViewById(R.id.lv_money);
        adapter = new MoneyAdapter(this, list);
        lv_money.setAdapter(adapter);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ibt_callback:
                this.finish();
                break;
            case R.id.ibt_pay:
                intent.setClass(this, PayMoneny.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
