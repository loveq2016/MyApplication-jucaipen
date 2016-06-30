package com.example.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.Activity.Hothunt;
import com.example.FragmentItems.FragmentChoinces;
import com.example.FragmentItems.FragmentFamousPerson;
import com.example.FragmentItems.FragmentIdea;
import com.example.FragmentItems.FragmentLive;
import com.example.FragmentItems.FragmentQuestion;
import com.example.FragmentItems.FragmentSchool;
import com.example.FragmentItems.FragmentVideo;
import com.example.adapter.ViewPagerAdapter;
import com.example.androidnetwork.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jucaipen on 16/5/5.
 */
public class FragmentMain extends Fragment implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private List<Fragment> fragments;
    private View view;
    private RadioGroup radioGroup;
    private ViewPager mainpager;
    private ViewPagerAdapter adapter;
    private FragmentChoinces fragmentChoinces;
    private FragmentFamousPerson famousPerson;
    private FragmentIdea fragmentIdea;
    private FragmentQuestion fragmentQuestion;
    private FragmentVideo fragmentVideo;
    private FragmentLive fragmentLive;
    private FragmentSchool fragmentSchool;
    private ImageView iv_hunt;

    private HorizontalScrollView horizontal;
    private String[] strs;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_main, container, false);
        init();

        return view;
    }


    private void initFragment() {
        fragments.add(fragmentChoinces);
        fragments.add(famousPerson);
        fragments.add(fragmentIdea);
        fragments.add(fragmentQuestion);
        fragments.add(fragmentVideo);
        fragments.add(fragmentLive);
        fragments.add(fragmentSchool);
        adapter.notifyDataSetChanged();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init() {
        strs = new String[7];
        strs[0] = "精选";
        strs[1] = "名家";
        strs[2] = "观点";
        strs[3] = "问答";
        strs[4] = "视频";
        strs[5] = "直播";
        strs[6] = "学院";

        fragmentChoinces = new FragmentChoinces();
        famousPerson = new FragmentFamousPerson();
        fragmentIdea = new FragmentIdea();
        fragmentQuestion = new FragmentQuestion();
        fragmentVideo = new FragmentVideo();
        fragmentLive = new FragmentLive();
        fragmentSchool = new FragmentSchool();


        mainpager = (ViewPager) view.findViewById(R.id.mainpager);
        mainpager.setOnPageChangeListener(this);
        radioGroup = (RadioGroup) view.findViewById(R.id.grop);
        iv_hunt = (ImageView) view.findViewById(R.id.iv_hunt);
        iv_hunt.setOnClickListener(this);
        fragments = new ArrayList<>();

        adapter = new ViewPagerAdapter(getChildFragmentManager(), fragments);
        mainpager.setAdapter(adapter);

        horizontal = (HorizontalScrollView) view.findViewById(R.id.horizontal);

        for (int i = 0; i < strs.length; i++) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(strs[i]);
            radioButton.setTextColor(Color.RED);
            radioButton.setTextSize(14);

            radioButton.setId(i);
            LayoutParams lm = new LayoutParams(218, LayoutParams.WRAP_CONTENT);
            radioButton.setLayoutParams(lm);
            radioButton.setGravity(Gravity.CENTER);
            radioButton.setBackground(getResources().getDrawable(R.drawable.radiobutton_selecter));
            radioButton.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
            radioGroup.addView(radioButton);
        }
        initFragment();
        radioGroup.setOnCheckedChangeListener(this);
        RadioButton rb = (RadioButton) radioGroup.getChildAt(0);
        rb.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton rb = (RadioButton) group.getChildAt(i);
            if (i == checkedId) {
                rb.setTextColor(Color.RED);
            } else {
                rb.setTextColor(Color.BLACK);
            }
        }

        mainpager.setCurrentItem(checkedId);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        RadioButton button = (RadioButton) radioGroup.getChildAt(i);
        if (button != null) {
            button.setChecked(true);
            int x = (int) button.getX();
            int y = (int) button.getY();
            horizontal.smoothScrollTo(x, y);
        }

    }

    @Override
    public void onPageScrollStateChanged(int i) {


    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.iv_hunt:
                intent.setClass(getActivity(), Hothunt.class);
                startActivity(intent);
                break;
        }
    }
}
