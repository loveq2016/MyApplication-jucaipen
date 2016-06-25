package com.example.MyRecord;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.adapter.RebateAdapter;
import com.example.androidnetwork.R;
import com.example.model.RebateIntegeralDetail;
import com.example.utils.StoreUtils;
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
 * Created by Administrator on 2016/6/17.
 * <p/>
 * 我的返利
 */
public class MyRebate extends Activity implements View.OnClickListener {
    private ListView lv_rebate;
    private ImageButton ibt_rebate;
    private RebateAdapter adapter;
    private String rebateUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getrebate";
    private Context context = MyRebate.this;
    private Map<String, Object> map = new HashMap<>();
    private int page = 1;
    private List<RebateIntegeralDetail> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rebate);

        init();
    }

    private void init() {
        ibt_rebate = (ImageButton) findViewById(R.id.ibt_rebate);
        ibt_rebate.setOnClickListener(this);
        lv_rebate = (ListView) findViewById(R.id.lv_rebate);
        adapter = new RebateAdapter(context,list);
        lv_rebate.setAdapter(adapter);
        GetMyRebage();

    }

    private void GetMyRebage() {
        map.put("userId", StoreUtils.getUserInfo(this));
        map.put("page", page);
        RequestParams params = NetUtils.sendHttpGet(rebateUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                if (result != null) {
                    //[{"id":91,"insertDate":"2016-06-16 17:44:28.593","rebateIntegerNum":5,
                    // "remark":"评论视频【投资互动《第二期5.19》】","type":"用户评论返利"}]
                    try {
                        JSONArray array=new JSONArray(result);
                        for (int i=0;i<array.length();i++){
                            JSONObject object=array.getJSONObject(i);
                            int id=object.getInt("id");
                            String insertDate=object.getString("insertDate");
                            int rebateIntegerNum=object.getInt("rebateIntegerNum");
                            String remark=object.getString("remark");
                            String type=object.getString("type");
                            RebateIntegeralDetail detail=new RebateIntegeralDetail();
                            detail.setId(id);
                            detail.setIntegralNum(rebateIntegerNum);
                            detail.setRemark(remark);
                            detail.setInsertDate(insertDate);
                            detail.setType(type);
                            list.add(detail);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_rebate:
                this.finish();
                break;
            default:
                break;
        }
    }
}
