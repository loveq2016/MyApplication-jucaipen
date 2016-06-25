package com.example.TeacherDate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adapter.VideioAdapter;
import com.example.androidnetwork.R;
import com.example.model.School;
import com.example.utils.NetUtils;
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
 * <p/>
 * 视频直播
 */
public class FrVideoPlay extends Fragment {
    private View view;
    private FragmentActivity context;
    private TestListView writer_history;
    private String videoUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getideaask";
    private VideioAdapter adapter;
    private List<School> list = new ArrayList<>();
    private Map<String, Object> map = new HashMap<>();
    private int teacherId;
    private int page = 1;
    private TextView video_play;
    private TextView live_body;
    private TextView video_personnum;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fr_videoplay, container, false);

        init();
        return view;
    }

    private void init() {
        teacherId = (int) getArguments().getSerializable("teacherId");
        video_play = (TextView) view.findViewById(R.id.video_play);
        live_body = (TextView) view.findViewById(R.id.live_body);
        video_personnum = (TextView) view.findViewById(R.id.video_personnum);
        context = getActivity();
        GetWriterlive();
        writer_history = (TestListView) view.findViewById(R.id.writer_history);
        adapter = new VideioAdapter(getActivity(), list);
        writer_history.setAdapter(adapter);
    }

    private void GetWriterlive() {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("typeId", 3);
        map.put("isIndex", 1);
        map.put("page", page);

        RequestParams params = NetUtils.sendHttpGet(videoUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                //{"err_msg":"暂无数据"}
                // [{"id":37,"image":"httg","title":"沪指震荡涨0.3% 两市逾200股涨停","startDate":null,"hits":0,"videoType":0,
                // "desc":"沪指震荡涨0.3% 两市逾200股涨停"}

                // id":1,"image":"","title":"","startDate":"2016-05-19 17:16:42.327","isEnd":3
                if (result != null) {
                    try {
                        JSONArray array = new JSONArray(result);

                        JSONObject object = array.getJSONObject(0);
                        String err_msg = object.optString("err_msg", "");
                        if (!err_msg.equals("")) {
                            return;
                        }

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(0);
                            int id = object1.getInt("id");
                            String image = object.optString("image", "");
                            String startDate = object.optString("startDate", "");
                            String title = object.optString("title", "");
                            int isEnd = object.optInt("isEnd", -1);
                            if (isEnd == 1) {
                                video_play.setText("未开始");
                            } else if (isEnd == 2) {
                                video_play.setText("正在直播");
                            } else if (isEnd == 3) {
                                video_play.setText("已结束");
                            }
                            live_body.setText(title);
                            video_personnum.setText("20人");

                            JSONArray array1 = array.getJSONArray(1);
                            for (int a = 0; a < array1.length(); a++) {
                                JSONObject object2 = array1.getJSONObject(a);
                                int ids = object2.optInt("id", -1);
                                int hits = object2.optInt("hits", -1);
                                int videoType = object2.optInt("videoType", -1);
                                String images = object2.optString("image", "");
                                String titles = object2.optString("title", "");
                                String startDates = object2.optString("startDate", "");
                                String desc = object2.optString("desc", "");

                                School school = new School();
                                school.setId(ids);
                                school.setHits(hits);
                                school.setVideoType(videoType);
                                school.setImage(images);
                                school.setTitle(titles);
                                school.setStartDate(startDates);
                                school.setDesc(desc);
                                list.add(school);
                            }

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
}
