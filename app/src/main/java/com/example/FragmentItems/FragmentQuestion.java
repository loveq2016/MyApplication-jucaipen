package com.example.FragmentItems;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.Activity.AskdQuestion;
import com.example.Activity.QuestionAnswer;
import com.example.adapter.QuestionAdapter;
import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
public class FragmentQuestion extends Fragment implements View.OnClickListener {
    private XRecyclerView questionListview;
    private View view;
    private String answerUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getquestion";
    private QuestionAdapter adapter;
    private Intent intent;
    private Map<String, Object> map = new HashMap<>();
    private List<Interlocution> list = new ArrayList<>();
    private int page = 1;
    private Dialog dialog;
    private ProgressBar question_progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.question, container, false);
        init();
        GetAnser(page);
        return view;
    }

    private void GetAnser(int pages) {
        map.put("page", pages);
        map.put("type", 0);
        RequestParams params = NetUtils.sendHttpGet(answerUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                question_progress.setVisibility(View.GONE);
                questionListview.setVisibility(View.VISIBLE);

                if (result != null) {
                    list = JsonUtil.getInter(result, list);
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
        });

    }

    private void init() {
        intent = new Intent();
        questionListview = (XRecyclerView) view.findViewById(R.id.questionListview);
        adapter = new QuestionAdapter(getActivity(), list);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.addview, null);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        questionListview.setLayoutManager(manager);
        questionListview.addHeaderView(v);
        questionListview.setAdapter(adapter);
        adapter.setViewItemClickListener(new QuestionAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void setonClick(View v, Integer postion) {
                intent.putExtra("askId", list.get(postion).getAskId());

                intent.putExtra("name", list.get(postion).getTrueName());
                intent.putExtra("insertDate", list.get(postion).getInsertDate());
                intent.putExtra("askBody", list.get(postion).getAskBodys());
                intent.putExtra("iamgurl", list.get(postion).getHeadFace());
                intent.setClass(getActivity(), QuestionAnswer.class);
                startActivity(intent);
            }
        });

        question_progress = (ProgressBar) view.findViewById(R.id.question_progress);

        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.askwindow);
        dialog.findViewById(R.id.liner_free).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), AskdQuestion.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "免费问", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.tv_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(getActivity(), AskdQuestion.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "付费问", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });

        //show  dialog
        v.findViewById(R.id.i_askQes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!dialog.isShowing()) {
                    dialog.show();
                }
            }
        });

        questionListview.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        questionListview.setPullRefreshEnabled(false);
        questionListview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                if (list != null && list.size() > 0) {
                    //int totlePage= list.get(0).getTotpager();
                    if (5 >= page) {
                        int p = page++;
                        GetAnser(p);
                    }

                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        questionListview.loadMoreComplete();
                    }
                }, 3000);

            }
        });

    }

    @Override
    public void onClick(View v) {

    }
   /* @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (question_scroll.getScrollY() == 0) {
                    //滑动到顶部
                    Toast.makeText(getActivity(), "顶部", Toast.LENGTH_SHORT).show();
                } else if (question_scroll.getScrollY() - scrollViewY < 2 && question_scroll.getScrollY() >= scrollViewY) {
                    // 底部
                    Toast.makeText(getActivity(), "底部", Toast.LENGTH_SHORT).show();

                    int totlepage = list.get(0).getTotlePage();
                    if (totlepage >= page) {
                        page++;
                        GetAnser(page);
                    } else {
                        Toast.makeText(getActivity(), "暂无更多数据", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(getActivity(), "page=" + page, Toast.LENGTH_SHORT).show();


                } else {
                    scrollViewY = question_scroll.getScrollY();
                }


                break;
        }
        return false;
    }*/
}




