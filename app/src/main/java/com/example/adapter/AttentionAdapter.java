package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Attention;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/6/3.
 * 我的关注适配器
 */
public class AttentionAdapter extends BaseAdapter {
    private Context context;

    private List<Attention> list;

    public AttentionAdapter(Context context, List<Attention> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<Attention> list) {
        this.list = list;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_famous_item, null);
        }

        ImageView att_logo = (ImageView) convertView.findViewById(R.id.att_logo);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_notice = (TextView) convertView.findViewById(R.id.tv_notice);
        TextView tv_fans = (TextView) convertView.findViewById(R.id.tv_fans);
        ImageView iv_guanzhu = (ImageView) convertView.findViewById(R.id.iv_guanzhu);

        Attention attention = list.get(position);
        Glide.with(context).load(attention.getTeacherImage())
                .placeholder(R.drawable.rentou)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(att_logo);
        tv_name.setText(attention.getNickName());
        tv_notice.setText(attention.getNotice());
        tv_fans.setText(attention.getFans() + ""+"人已关注 | "+attention.getLeavel());
        int isv = attention.getIsV();
        if (isv == 1) {
            iv_guanzhu.setImageResource(R.drawable.yiguanzhu);
        } else {
            iv_guanzhu.setImageResource(R.drawable.guanzhu);
        }


        return convertView;
    }
}
