package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.TeacherDate.FmHomePage;
import com.example.TeacherDate.FrViewpoint;
import com.example.TeacherDate.FrWriterLive;
import com.example.androidnetwork.R;
import com.example.model.FamousTeacher;
import com.example.utils.StoreUtils;
import com.example.TeacherDate.FrAnswer;
import com.example.TeacherDate.FrVideoPlay;
import com.example.TeacherDate.FrWarfare;
import com.example.adapter.ViewPagerAdapter;
import com.example.utils.NetUtils;
import com.example.utils.StringUntils;

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
 * Created by Administrator on 2016/5/14.
 * 老师详情界面
 */
public class FamousPlain extends FragmentActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioGroup teacher_group;
    private RadioButton rad_homepage;
    private RadioButton rad_writing;
    private RadioButton rad_video;
    private RadioButton rad_viewpoint;
    private RadioButton rad_answer;
    private RadioButton rad_warfare;
    private ViewPager famous_viewpager;
    private FmHomePage fmHomePage;
    private FrWriterLive frWriterLive;
    private FrVideoPlay frVideoPlay;
    private FrViewpoint frViewpoint;
    private FrAnswer frAnswer;
    private FrWarfare frWarfare;
    private List<Fragment> list = new ArrayList<Fragment>();
    private ViewPagerAdapter pagerAdapter;
    private ImageButton ibt_ask;
    private ImageButton ibt_reward;
    private ImageButton ibt_attention;
    private ImageButton ibt_guard;
    private ImageView teacher_back;
    private String plainUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getbaseteacherinfo";


    private ImageView tImage;
    private TextView tName;
    private TextView famous_type;
    private TextView tv_rq;
    private TextView tv_gz;
    private TextView tv_sh;
    private TextView tv_plain;
    private Map<String, Object> map = new HashMap<>();
    private int teacherId;
    private String joinDate;
    private FamousTeacher teacher=new FamousTeacher();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_famous_detaile);

        init();

    }

    private void init() {
        teacherId=getIntent().getIntExtra("teacherId", 0);
        Bundle bundle = new Bundle();
        bundle.putInt("teacherId", teacherId);
        GetTeacherDate();

        //  teacherId=getIntent().
        fmHomePage = new FmHomePage();
        fmHomePage.setArguments(bundle);

        frWriterLive = new FrWriterLive();
        frWriterLive.setArguments(bundle);

        frVideoPlay = new FrVideoPlay();
        frVideoPlay.setArguments(bundle);

        frViewpoint = new FrViewpoint();
        frViewpoint.setArguments(bundle);

        frAnswer = new FrAnswer();
        frAnswer.setArguments(bundle);


        frWarfare = new FrWarfare();
        frWarfare.setArguments(bundle);
        list.add(fmHomePage);
        list.add(frWriterLive);
        list.add(frVideoPlay);
        list.add(frViewpoint);
        list.add(frAnswer);
        list.add(frWarfare);



        tImage= (ImageView) findViewById(R.id.tImage);
        tName = (TextView) findViewById(R.id.tName);
        famous_type = (TextView) findViewById(R.id.famous_type);
        tv_rq = (TextView) findViewById(R.id.tv_rq);
        tv_gz = (TextView) findViewById(R.id.tv_gz);
        tv_sh = (TextView) findViewById(R.id.tv_sh);
        tv_plain = (TextView) findViewById(R.id.tv_plain);


        teacher_back = (ImageView) findViewById(R.id.teacher_back);
        teacher_back.setOnClickListener(this);
        tv_plain = (TextView) findViewById(R.id.tv_plain);
        teacher_group = (RadioGroup) findViewById(R.id.teacher_group);
        rad_homepage = (RadioButton) findViewById(R.id.rad_homepage);
        rad_writing = (RadioButton) findViewById(R.id.rad_writing);
        rad_video = (RadioButton) findViewById(R.id.rad_video);
        rad_viewpoint = (RadioButton) findViewById(R.id.rad_viewpoint);
        rad_answer = (RadioButton) findViewById(R.id.rad_answer);
        rad_warfare = (RadioButton) findViewById(R.id.rad_warfare);
        famous_viewpager = (ViewPager) findViewById(R.id.famous_viewpager);
        ibt_ask = (ImageButton) findViewById(R.id.ibt_ask);
        ibt_ask.setOnClickListener(this);
        ibt_reward = (ImageButton) findViewById(R.id.ibt_reward);
        ibt_reward.setOnClickListener(this);
        ibt_attention = (ImageButton) findViewById(R.id.ibt_attention);
        ibt_attention.setOnClickListener(this);
        ibt_guard = (ImageButton) findViewById(R.id.ibt_guard);
        ibt_guard.setOnClickListener(this);


        famous_viewpager.setOnPageChangeListener(this);
        tv_plain.setOnClickListener(this);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        famous_viewpager.setAdapter(pagerAdapter);
        rad_homepage.setChecked(true);
        teacher_group.setOnCheckedChangeListener(this);

    }

    private void GetTeacherDate() {
        map.clear();
        map.put("userId", StoreUtils.getUserInfo(this));
        map.put("teacherId",teacherId);
        RequestParams params = NetUtils.sendHttpGet(plainUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        joinDate=object.getString("joinDate");
                        String notice=object.getString("notice");
                        String hoby=object.getString("hoby");
                        boolean isAttention=object.getBoolean("isAttention");
                        String headFace = object.getString("headFace");
                        String nickName = object.getString("nickName");
                        String leavel = object.getString("leavel");
                        String plain = object.getString("plain");
                        int isV = object.getInt("isV");
                        int renQi = object.getInt("renQi");
                        int attention = object.getInt("attention");
                        int gurdian = object.getInt("gurdian");

                        teacher.setHeadFace(headFace);
                        teacher.setNickName(nickName);
                        teacher.setIsV(isV);
                        teacher.setLevel(leavel);
                        teacher.setJoinDate(joinDate);
                        teacher.setAttention(isAttention);
                        teacher.setIntroduce(plain);
                        teacher.setNotice(notice);
                        teacher.setHoby(hoby);

                        Glide.with(FamousPlain.this)
                                .load(headFace)
                                .bitmapTransform(new CropCircleTransformation(FamousPlain.this))
                                .placeholder(R.drawable.rentou)
                                .into(tImage);

                        tName.setText(nickName);
                        famous_type.setText(leavel);
                        tv_rq.setText(renQi+"");
                        tv_gz.setText(attention+"");
                        tv_sh.setText(gurdian+"");
                        tv_plain.setText("简介："+Html.fromHtml(plain));


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
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.tv_plain:
                Bundle bundle=new Bundle();
                bundle.putSerializable("teacher",teacher);
                intent.putExtras(bundle);
                intent.setClass(this, Teacherintro.class);
                startActivity(intent);
                break;

            case R.id.ibt_ask:
                intent.setClass(this, AskdQuestion.class);
                startActivity(intent);
                // Toast.makeText(FamousPlain.this, "提问", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_reward:
                Toast.makeText(FamousPlain.this, "打赏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_attention:
                Toast.makeText(FamousPlain.this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ibt_guard:
                intent.setClass(this, IntoGuard.class);
                startActivity(intent);
                // Toast.makeText(FamousPlain.this, "守护", Toast.LENGTH_SHORT).show();
                break;
            case R.id.teacher_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rad_homepage:
                famous_viewpager.setCurrentItem(0, false);
                break;
            case R.id.rad_writing:
                famous_viewpager.setCurrentItem(1, false);
                break;
            case R.id.rad_video:
                famous_viewpager.setCurrentItem(2, false);
                break;
            case R.id.rad_viewpoint:
                famous_viewpager.setCurrentItem(3, false);
                break;
            case R.id.rad_answer:
                famous_viewpager.setCurrentItem(4, false);
                break;
            case R.id.rad_warfare:
                famous_viewpager.setCurrentItem(5, false);

            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i) {
            case 0:
                teacher_group.check(R.id.rad_homepage);
                break;
            case 1:
                teacher_group.check(R.id.rad_writing);
                break;
            case 2:
                teacher_group.check(R.id.rad_video);
                break;
            case 3:
                teacher_group.check(R.id.rad_viewpoint);
                break;
            case 4:
                teacher_group.check(R.id.rad_answer);
                break;
            case 5:
                teacher_group.check(R.id.rad_warfare);
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
