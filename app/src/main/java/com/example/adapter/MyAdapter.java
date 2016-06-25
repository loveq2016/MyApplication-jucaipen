package com.example.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

/**
 * Created by jucaipen on 16/5/9.
 */
public class MyAdapter extends PagerAdapter {

    private List<View> list;

    public MyAdapter(List<View> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = list.get(position);
        container.removeView(v);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = list.get(position);

        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }


//        if(v.getParent()!=null){
//            container.removeView(v);
//        }
        container.addView(v);
        return v;
    }
}
