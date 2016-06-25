package com.example.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.androidnetwork.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 *
 *  用户席位 上边适配器
 */
public class UserTopAdapter extends BaseAdapter{
    private Context context;
    private List<String> list;
    private  List<Integer> images;


    public UserTopAdapter(FragmentActivity activity, List<String> list, List<Integer> images) {
        this.context=activity;
        this.list=list;
        this.images=images;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){

            convertView= LayoutInflater.from(context).inflate(R.layout.topitems,null);
        }
        ImageView iv_top= (ImageView) convertView.findViewById(R.id.iv_top);
        TextView tv_top= (TextView) convertView.findViewById(R.id.tv_top);
        iv_top.setImageResource(R.drawable.approve);
        iv_top.setImageResource(images.get(position));
        tv_top.setText(list.get(position));


        return convertView;
    }
}
