package com.example.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.FamousTeacher;
import com.example.utils.TimeUtils;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/14.
 * 老师简介
 */
public class Teacherintro extends Activity implements View.OnClickListener {
    private ImageButton famous_finish;
    private FamousTeacher teacher;
    private TextView tNickName;
    private ImageView tLogo;
    private TextView plain_indrouce;
    private TextView plain_gonggao;
    private TextView plain_good;
    private TextView leavl;
    private TextView tv_joindate;
    private ImageView attion_iv;


//    object.addProperty("joinDate", teacher.getJoinDate());
//    object.addProperty("notice", teacher.getNotice());
//    object.addProperty("hoby", teacher.getHoby());
//    object.addProperty("isAttention", teacher.isAttention());、
//    添加上传参数：  userId


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_famous_plain);

        init();
    }

    private void init() {
        teacher = (FamousTeacher) getIntent().getSerializableExtra("teacher");
        famous_finish = (ImageButton) findViewById(R.id.famous_finish);
        famous_finish.setOnClickListener(this);
        tNickName = (TextView) findViewById(R.id.tNickName);
        tNickName.setText(teacher.getNickName());
        tLogo = (ImageView) findViewById(R.id.tLogo);
        Glide.with(this).load(teacher.getHeadFace()).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.drawable.rentou).into(tLogo);

        plain_indrouce = (TextView) findViewById(R.id.plain_indrouce);
        plain_gonggao = (TextView) findViewById(R.id.plain_gonggao);
        plain_good = (TextView) findViewById(R.id.plain_good);
        leavl = (TextView) findViewById(R.id.leavl);
        tv_joindate = (TextView) findViewById(R.id.tv_joindate);
        attion_iv = (ImageView) findViewById(R.id.attion_iv);
        leavl.setText(teacher.getLevel());
        tv_joindate.setText("加入时间：" + TimeUtils.parseStrDate(teacher.getJoinDate(), "yyyy-MM-dd"));
        plain_indrouce.setText(Html.fromHtml(teacher.getIntroduce()));
        plain_gonggao.setText(Html.fromHtml(teacher.getNotice()));
        plain_good.setText(Html.fromHtml(teacher.getHoby()));
        if (teacher.isAttention()) {
            attion_iv.setImageResource(R.drawable.yiguanzhu);
        } else {
            attion_iv.setImageResource(R.drawable.guanzhu);
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.famous_finish:
                this.finish();
                break;
            default:
                break;
        }
    }
}
