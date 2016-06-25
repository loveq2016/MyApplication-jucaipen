package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Marker;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/6/3.
 *
 * 我的邀请   邀请码的适配器
 */
public class InviteCodeAdaper extends BaseAdapter{
    List<Marker> list;

    public InviteCodeAdaper(List<Marker> list) {
        this.list=list;
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
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.invitecode,null);
        }
        ImageView iv_marker= (ImageView) convertView.findViewById(R.id.iv_marker);
        Glide.with(parent.getContext()).load(list.get(position).getFace())
                .bitmapTransform(new CropCircleTransformation(parent.getContext()))
                .into(iv_marker);
        return convertView;
    }
}
