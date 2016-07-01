package com.example.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adapter.DetailsAdapter;
import com.example.androidnetwork.R;
import com.example.model.QuestionBean;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.utils.TimeUtils;
import com.example.view.TestListView;

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

import jp.wasabeef.glide.transformations.CropCircleTransformation;

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
    private List<QuestionBean> list = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private String askUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getanswerdetails";
    private int askId;

    private ImageView picture;
    private TextView answer_name;
    private TextView question_time;
    private TextView body_question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionanswer);

        init();
        GetAnswerDate();

    }

    private void GetAnswerDate() {
        map.clear();
        map.put("askId", askId);
        RequestParams params = NetUtils.sendHttpGet(askUrl, map);
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
                            String teacherFace = object.optString("teacherFace", "");
                            String teacherNickName = object.optString("teacherNickName", "");
                            String teacherLeavel = object.optString("teacherLeavel", "");
                            String answerDate = object.optString("answerDate", "");
                            String answerBody = object.optString("answerBody", "");

                            QuestionBean bean = new QuestionBean();
                            bean.setTeacherFace(teacherFace);
                            bean.setTeacherNickName(teacherNickName);
                            bean.setTeacherLeavel(teacherLeavel);
                            bean.setAskDate(answerDate);
                            bean.setAnswerBody(answerBody);
                            list.add(bean);
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
        askId = getIntent().getIntExtra("askId", -1);
        String name = getIntent().getStringExtra("name");
        String insertDate = getIntent().getStringExtra("insertDate");
        String askBody = getIntent().getStringExtra("askBody");
        String iamgurl = getIntent().getStringExtra("iamgurl");


        answer_lv = (TestListView) findViewById(R.id.answer_lv);
        ibt_back = (ImageButton) findViewById(R.id.ibt_back);
        answer_edt = (EditText) findViewById(R.id.answer_edt);
        answer_send = (ImageButton) findViewById(R.id.answer_send);
        answer_send.setOnClickListener(this);
        answer_scroll = (ScrollView) findViewById(R.id.answer_scroll);
        answer_scroll.smoothScrollTo(0, 0);

        picture = (ImageView) findViewById(R.id.picture);
        answer_name = (TextView) findViewById(R.id.answer_name);
        question_time = (TextView) findViewById(R.id.question_time);
        body_question = (TextView) findViewById(R.id.body_question);

        answer_name.setText(name);
        body_question.setText(askBody);
        Glide.with(QuestionAnswer.this).load(iamgurl)
                .placeholder(R.drawable.rentou)
                .bitmapTransform(new CropCircleTransformation(QuestionAnswer.this))
                .into(picture);


        ibt_back.setOnClickListener(this);
        adapter = new DetailsAdapter(context, list);
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
