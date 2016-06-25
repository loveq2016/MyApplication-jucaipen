package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.adapter.DetailsAdapter;
import com.example.androidnetwork.R;
import com.example.model.QuestionBean;
import com.example.utils.NetUtils;
import com.example.view.TestListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/31.
 * <p/>
 * 问答详情
 */
public class QuestionAnswer extends Activity implements View.OnClickListener {
    private TestListView answer_lv;
    private DetailsAdapter adapter;
    private Context context = this;
    private ImageButton ibt_back;
    private EditText answer_edt;
    private ImageButton answer_send;
    private ScrollView answer_scroll;
    private List<QuestionBean> questions = new ArrayList<>();
    private String questionUrl = "";
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionanswer);

        init();
        initData();
        GetAnswerDate();

    }

    private void GetAnswerDate() {
        RequestParams params = NetUtils.sendHttpGet(questionUrl, map);

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {

                Toast.makeText(QuestionAnswer.this, "" + result, Toast.LENGTH_SHORT).show();
                Log.e("111", "onSuccess: " + result);

                if (result != null) {


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

    private void initData() {
        for (int i = 0; i < 15; i++) {
            QuestionBean questionBean = new QuestionBean();
            if (i % 2 == 0) {
                questionBean.setType(1);
            } else {
                questionBean.setType(2);
            }
            questions.add(questionBean);
        }
        adapter.notifyDataSetChanged();
    }

    private void init() {
        answer_lv = (TestListView) findViewById(R.id.answer_lv);
        ibt_back = (ImageButton) findViewById(R.id.ibt_back);
        answer_edt = (EditText) findViewById(R.id.answer_edt);
        answer_send = (ImageButton) findViewById(R.id.answer_send);
        answer_send.setOnClickListener(this);
        answer_scroll = (ScrollView) findViewById(R.id.answer_scroll);
        answer_scroll.smoothScrollTo(0, 0);


        ibt_back.setOnClickListener(this);
        adapter = new DetailsAdapter(context, questions);
        answer_lv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibt_back:
                this.finish();
                break;
            case R.id.answer_send:
                String msg = answer_edt.getText().toString().trim();
                if (msg.length() <= 0) {
                    Toast.makeText(QuestionAnswer.this, "消息不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuestionAnswer.this, msg, Toast.LENGTH_SHORT).show();
                    answer_edt.setText("");
                }

                break;
        }
    }
}
