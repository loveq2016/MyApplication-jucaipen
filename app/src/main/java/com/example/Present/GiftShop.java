package com.example.Present;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/23.
 * 礼品商店
 */
public class GiftShop extends FragmentActivity implements View.OnClickListener {

    private RadioGroup shop_ground;
    private RadioButton shop_all;
    private RadioButton shop_video;
    private RadioButton shop_expend;
    private ShopAll shopAll;
    private ShopvVideo shopvVideo;
    private ShopRecomm shopRecomm;
    private FragmentManager fm;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giftshop);

        init();
    }

    private void init() {

        //shop_ground = (RadioGroup) findViewById(R.id.shop_ground);
        shop_all = (RadioButton) findViewById(R.id.shop_all);
        shop_all.setOnClickListener(this);
        shop_video = (RadioButton) findViewById(R.id.shop_video);
        shop_video.setOnClickListener(this);
        shop_expend = (RadioButton) findViewById(R.id.shop_expend);
        shop_expend.setOnClickListener(this);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.shop_frame, new ShopAll());
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop_all:
                transaction = fm.beginTransaction();
                shopAll = new ShopAll();
                transaction.replace(R.id.shop_frame, shopAll).commit();
                break;
            case R.id.shop_video:
                transaction = fm.beginTransaction();
                shopvVideo = new ShopvVideo();
                transaction.replace(R.id.shop_frame, shopvVideo).commit();
                break;
            case R.id.shop_expend:
                transaction = fm.beginTransaction();
                shopRecomm = new ShopRecomm();
                transaction.replace(R.id.shop_frame, shopRecomm).commit();
                break;
        }

    }


//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//        switch (checkedId) {
//            case R.id.shop_all:
//                transaction = fm.beginTransaction();
//                if (shopAll == null) {
//                    shopAll = new ShopAll();
//                }
//                transaction.replace(R.id.shop_frame, shopAll).commit();
//                break;
//            case R.id.shop_video:
//                transaction = fm.beginTransaction();
//                if (shopvVideo == null) {
//                    shopvVideo = new ShopvVideo();
//                }
//                transaction.replace(R.id.shop_frame, shopvVideo).commit();
//                break;
//            case R.id.shop_expend:
//                transaction = fm.beginTransaction();
//                if (shopRecomm == null) {
//                    shopRecomm = new ShopRecomm();
//                }
//                transaction.replace(R.id.shop_frame, shopRecomm).commit();
//                break;
//        }
//
//    }

}
