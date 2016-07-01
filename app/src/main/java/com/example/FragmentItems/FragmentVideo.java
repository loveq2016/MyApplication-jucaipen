package com.example.FragmentItems;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.Activity.VideoPlay;
import com.example.adapter.VideoPlayAdapter;
import com.example.androidnetwork.R;
import com.example.model.Live;
import com.example.model.Video;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;
import com.example.utils.TimeUtils;
import com.example.view.MyGridView;
import com.example.view.media.MyMediaController;

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
 * 主页   －－－视频模块
 */
public class FragmentVideo extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private View view;
    private VideoPlayAdapter adapter;
    private MyGridView video_gridview;
    private List<String> list = new ArrayList<>();
    private RadioGroup two_grop;
    private List<Live> typelist = new ArrayList<>();
    private List<Live> classList = new ArrayList<>();
    private List<Live> teacherList = new ArrayList<>();
    private List<Live> secondList = new ArrayList<>();
    private String videoUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideoclass";
    private String typeUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideotype";
    private String teacherUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideoteacher";
    private String liveUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getvideo";
    private RadioButton button;
    private Map<String, Object> map = new HashMap<>();
    private RadioGroup type_rgp;//分类
    private RadioGroup video_type;//类型
    private RadioGroup teacher_rad;//讲师
    private List<Video> videoList = new ArrayList<>();
    private int classId;
    private int typeId = -1;
    private int teacherId = -1;
    private RelativeLayout rel_screen;
    private RelativeLayout rela_video;
    private View video_view;
    private boolean isview;
    private ScrollView video_scroll;
    private ProgressBar fr_progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.video, container, false);
        initWight();
        return view;
    }


    private void GetDate(Map<String, Object> map, final RadioGroup group, String url, final int position) {
        RequestParams params = NetUtils.sendHttpGet(url, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                fr_progress.setVisibility(View.GONE);
                fr_progress.clearAnimation();
                video_scroll.setVisibility(View.VISIBLE);

                group.removeAllViews();
                if (position == 1) {
                    //分类信息
                    classList.clear();
                    classList = JsonUtil.getLiveType(result);
                    parseType(classList, group);
                } else if (position == 2) {
                    //类型
                    typelist.clear();
                    typelist = JsonUtil.getLiveType(result);
                    parseType(typelist, group);
                } else if (position == 3) {
                    teacherList.clear();
                    teacherList = JsonUtil.getLiveType(result);
                    parseType(teacherList, group);
                } else {
                    secondList.clear();
                    secondList = JsonUtil.getLiveType(result);
                    parseType(secondList, group);
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


    private void getLiveList(int classId, int typeId, int teacherId, int page) {
        {
            map.clear();
            map.put("page", page);
            map.put("typeId", typeId);
            map.put("classId", classId);
            map.put("teacherId", teacherId);
            RequestParams params = NetUtils.sendHttpGet(liveUrl, map);
            x.http().get(params, new Callback.CacheCallback<String>() {
                @Override
                public boolean onCache(String result) {
                    return false;
                }

                @Override
                public void onSuccess(String result) {
                    if (result != null && result.length() > 0) {
                        videoList = JsonUtil.getvideo(result);
                        adapter.setList(videoList);
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


    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void parseType(List<Live> classList, RadioGroup gro) {
        for (int i = 0; i < classList.size(); i++) {
            String title = classList.get(i).getTitle();
            button = new RadioButton(getActivity());
            button.setText(title);
            button.setButtonDrawable(0);
            button.setTextSize(12);
            button.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            button.setTextColor(ContextCompat.getColorStateList(getActivity(), R.color.video_txt_sele));
            button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.video_border_se));
            button.setId(View.generateViewId());
            button.setGravity(Gravity.CENTER);
            ViewGroup.LayoutParams lm = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(lm);
            gro.addView(button);
        }
        RadioButton rb = (RadioButton) gro.getChildAt(0);
        rb.setChecked(true);

    }


    //类型     2
    private void TypeVideo() {
        map.clear();
        GetDate(map, video_type, typeUrl, 2);

    }

    //分类      1
    private void GetVideo() {
        map.clear();
        map.put("parentId", 0);
        GetDate(map, type_rgp, videoUrl, 1);

    }

    //按讲师    3
    private void GetTeacherType() {
        map.clear();
        GetDate(map, teacher_rad, teacherUrl, 3);

    }


    private void initWight() {
        rela_video = (RelativeLayout) view.findViewById(R.id.rela_video);
        rela_video.setOnClickListener(this);


        type_rgp = (RadioGroup) view.findViewById(R.id.type_rgp);
        two_grop = (RadioGroup) view.findViewById(R.id.two_grop);
        video_type = (RadioGroup) view.findViewById(R.id.video_type);
        teacher_rad = (RadioGroup) view.findViewById(R.id.teacher_rad);
        rela_video = (RelativeLayout) view.findViewById(R.id.rela_video);
        rela_video.setOnClickListener(this);
        rel_screen = (RelativeLayout) view.findViewById(R.id.rel_screen);
        video_view = view.findViewById(R.id.video_view);
        video_scroll = (ScrollView) view.findViewById(R.id.video_scroll);
        fr_progress = (ProgressBar) view.findViewById(R.id.fr_progress);

        video_gridview = (MyGridView) view.findViewById(R.id.video_gridview);
        video_gridview.setOnItemClickListener(this);
        adapter = new VideoPlayAdapter(getActivity(), videoList);
        video_gridview.setAdapter(adapter);

        type_rgp.setOnCheckedChangeListener(this);
        video_type.setOnClickListener(this);
        teacher_rad.setOnCheckedChangeListener(this);
        two_grop.setOnCheckedChangeListener(this);



        GetVideo();
        TypeVideo();
        GetTeacherType();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rela_video:
                if (isview == false) {
                    rel_screen.setVisibility(View.VISIBLE);
                    video_view.setVisibility(View.VISIBLE);
                    isview = true;
                } else {
                    rel_screen.setVisibility(View.GONE);
                    video_view.setVisibility(View.GONE);
                    isview = false;
                }

                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb = (RadioButton) view.findViewById(checkedId);
        int position = group.indexOfChild(rb);
        switch (group.getId()) {
            case R.id.type_rgp:
                //分类
                classId = classList.get(position).getId();
                map.clear();
                map.put("parentId", classId);
                GetDate(map, two_grop, videoUrl, 4);

                getLiveList(classId, typeId, teacherId, 1);


                break;
            case R.id.video_type:
                //分类
                typeId = typelist.get(position).getId();

                getLiveList(classId, typeId, teacherId, 1);


                break;
            case R.id.teacher_rad:
                //讲师
                teacherId = teacherList.get(position).getId();


                getLiveList(classId, typeId, teacherId, 1);

                break;
            case R.id.two_grop:
                //二级分类
                classId = secondList.get(position).getId();

                getLiveList(classId, typeId, teacherId, 1);

                break;

            default:
                break;

        }


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       /* Intent intent = new Intent();
        intent.setClass(getActivity(), VideoPlay.class);
        intent.putExtra("videoUrl", videoList.get(position).getVideoUrl());

        Toast.makeText(getActivity(), "url=" + videoList.get(position).getVideoUrl(), Toast.LENGTH_SHORT).show();
        startActivity(intent);*/
        Intent intent = new Intent();
        intent.putExtra("id", videoList.get(position).getId());
        intent.putExtra("videoUrl", videoList.get(position).getVideoUrl());
        intent.putExtra("classId", videoList.get(position).getClassId());
        intent.putExtra("title", videoList.get(position).getTitle());
        intent.putExtra("hit", videoList.get(position).getHits());
        intent.putExtra("isSpecial", videoList.get(position).isSpecial());
        intent.putExtra("isCharge", videoList.get(position).isCharge());
        intent.setClass(getActivity(), VideoPlay.class);
        startActivity(intent);


    }
}
