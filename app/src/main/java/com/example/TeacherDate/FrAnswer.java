package com.example.TeacherDate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Activity.AskdQuestion;
import com.example.Activity.QuestionAnswer;
import com.example.adapter.PageAnswerAdapter;
import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
    private XRecyclerView answer_listView;
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
        GetAnswerDate(page);

        answer_listView = (XRecyclerView) view.findViewById(R.id.answer_listView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        answer_listView.setLayoutManager(manager);

        answerAdapter = new PageAnswerAdapter(getActivity(), list);
        answer_listView.setAdapter(answerAdapter);

        answerAdapter.setAnswerAdapterOnItemClick(new PageAnswerAdapter.PageAnswerAdapterOnItemClick() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent();
                intent.putExtra("name",list.get(position).getTrueName());
                intent.putExtra("insertDate",list.get(position).getInsertDate());
                intent.putExtra("askBody",list.get(position).getAskBodys());
                intent.putExtra("iamgurl",list.get(position).getHeadFace());
                intent.putExtra("askId",list.get(position).getAskId());
                intent.setClass(getActivity(), QuestionAnswer.class);
                startActivity(intent);
            }
        });
        answer_listView.setPullRefreshEnabled(false);
        answer_listView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                if (list != null && list.size() > 0) {
                    int totlepage = list.get(0).getTotlePage();
                    if (totlepage > page) {
                        page++;
                        GetAnswerDate(page);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            answer_listView.loadMoreComplete();
                        }
                    }, 3000);
                }

            }
        });
    }

    private void GetAnswerDate(int pages) {
        map.put("teacherId", teacherId);
        map.put("typeId", 1);
        map.put("page", pages);
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
                    list = JsonUtil.getInter(result, list);
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
