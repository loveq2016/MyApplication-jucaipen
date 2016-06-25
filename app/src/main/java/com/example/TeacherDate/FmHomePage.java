package com.example.TeacherDate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.utils.JsonUtil;
import com.example.Activity.HotCareful;
import com.example.adapter.PageAnswerAdapter;
import com.example.adapter.PageIdeaAdapter;
import com.example.model.Opinion;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
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

/**
 * Created by Administrator on 2016/5/14.
 * 他的主页
 */
public class FmHomePage extends Fragment implements AdapterView.OnItemClickListener {
    private View view;

    private Map<String, Object> map = new HashMap<>();
    //他的观点适配器
    private String pointUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getideaask";
    private PageIdeaAdapter pageIdeaAdapter;
    private int teacherId;
    private int page = 1;
    //他的问答适配器
    private PageAnswerAdapter answerAdapter;

    private TestListView homelv_idea;
    private TestListView homelv_answer;
    private List<Opinion> opinionList = new ArrayList<>();

    private List<Interlocution> list = new ArrayList<>();
    private Context context;
    private String answerUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getideaask";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fr_homepage, container, false);

        init();

        return view;
    }

    private void init() {
        teacherId = getArguments().getInt("teacherId");
        context = getActivity();
        GetPoint();
        GetAnswerDate();
        homelv_idea = (TestListView) view.findViewById(R.id.homelv_idea);
        homelv_idea.setOnItemClickListener(this);
        homelv_answer = (TestListView) view.findViewById(R.id.homelv_answer);
        pageIdeaAdapter = new PageIdeaAdapter(context, opinionList);
        answerAdapter = new PageAnswerAdapter(context, list);
        homelv_idea.setAdapter(pageIdeaAdapter);
        homelv_answer.setAdapter(answerAdapter);


//        ideaAdapter=new IdeaAdapter(getActivity(),opinionList);
//        questionAdapter=new QuestionAdapter(getActivity(),list);
//        homelv_idea.setAdapter(ideaAdapter);
//        homelv_answer.setAdapter(questionAdapter);
    }

    private void GetPoint() {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("typeId", 0);
        map.put("page", page);
        map.put("isIndex", 0);
        RequestParams params = NetUtils.sendHttpGet(pointUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }
            //[{"id":690,"title":"CCTV证券实战教学团队2016.3.21收评","desc":"<p>\r\n\t<spa

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            int id = object.getInt("id");
                            String title = object.getString("title");
                            String desc = object.getString("desc");
                            String insertDate = object.getString("insertDate");
                            Opinion opinion = new Opinion();
                            opinion.setId(id);
                            opinion.setTitle(title);
                            opinion.setDesc(desc);
                            opinion.setInsertdate(insertDate);
                            opinionList.add(opinion);
                        }
                        pageIdeaAdapter.notifyDataSetChanged();
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

    private void GetAnswerDate() {
        map.put("teacherId", teacherId);
        map.put("typeId", 1);
        map.put("page", page);
        map.put("isIndex", 0);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.homelv_idea:
                Intent intent = new Intent();
                intent.putExtra("id",opinionList.get(position).getId());
                intent.setClass(getActivity(), HotCareful.class);
                startActivity(intent);
                break;

        }
    }
}
