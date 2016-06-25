package com.example.FragmentItems;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import com.example.Activity.QuestionAnswer;
import com.example.adapter.QuestionAdapter;
import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.utils.JsonUtil;
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
 * Created by jucaipen on 16/5/6.
 * <p/>
 * 主页    －－－问答模块
 */
public class FragmentQuestion extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private TestListView questionListview;
    private View view;
    private String answerUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getquestion";
    private QuestionAdapter adapter;
    private ImageButton ibt_ask;
    private Intent intent;
    private Map<String, Object> map = new HashMap<>();
    private List<Interlocution> list = new ArrayList<>();
    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.question, container, false);

        init();
        GetAnser();
        return view;
    }

    private void GetAnser() {
        map.put("page", page);
        map.put("type", 0);


        RequestParams params = NetUtils.sendHttpGet(answerUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                list = JsonUtil.getInter(result);
                adapter.setList(list);
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
        });

    }

    private void init() {
        intent = new Intent();
        ibt_ask = (ImageButton) view.findViewById(R.id.ibt_ask);
        ibt_ask.setOnClickListener(this);


        questionListview = (TestListView) view.findViewById(R.id.questionListview);
        questionListview.setOnItemClickListener(this);
        adapter = new QuestionAdapter(getActivity(), list);
        questionListview.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.questionListview:
                intent.setClass(getActivity(), QuestionAnswer.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_ask:
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.askwindow);
                if (!dialog.isShowing()) {
                    dialog.show();
                }
                break;
            default:
                break;
        }
    }
}

























