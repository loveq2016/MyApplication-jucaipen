package com.example.TeacherDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.PageAnswerAdapter;
import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/14.
 * 他的问答
 */
public class FrAnswer extends Fragment {
    private ListView answer_listView;
    private List<Interlocution> list = new ArrayList<>();
    private PageAnswerAdapter answerAdapter;
    private View view;
    private int teacherId;
    private String answerUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getideaask";
    private int page = 1;
    private Map<String, Object> map = new ArrayMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fr_answer, container, false);
        teacherId = (int) getArguments().getSerializable("teacherId");

        init();
        return view;
    }

    private void init() {
        GetAnswerDate();
        answer_listView = (ListView) view.findViewById(R.id.answer_listView);
        answerAdapter = new PageAnswerAdapter(getActivity(), list);

        answer_listView.setAdapter(answerAdapter);

    }

    private void GetAnswerDate() {
        map.put("teacherId", teacherId);
        map.put("typeId", 1);
        map.put("page", page);
        map.put("isIndex", 1);
        RequestParams params = NetUtils.sendHttpGet(answerUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                //{"page":1,"totlePage":1,"askId":173,"trueName":null,"insertDate":"2016-05-23 10:02:00.71"
                // ,"askBodys":"1111","headFace":null,"isPay":1,"replyCount":0,"answerBody":null},
                if (result != null) {
                    list = JsonUtil.getInter(result);
                }
                answerAdapter.notifyDataSetChanged();

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
}
