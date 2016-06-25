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
import com.example.model.Famous;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/9.
 * <p/>
 * 名家
 */
public class PersonAdapter extends BaseAdapter {
    private Context context;
    private List<Famous> list;

    public PersonAdapter(Context context, List<Famous> famousList) {
        this.context = context;
        this.list = famousList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<Famous> list) {
        this.list = list;
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
        MyHolder holder = null;

        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_famous_item, null);
            holder.logo = (ImageView) convertView.findViewById(R.id.att_logo);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_notice = (TextView) convertView.findViewById(R.id.tv_notice);
            holder.tv_fans = (TextView) convertView.findViewById(R.id.tv_fans);
            holder.iv_guanzhu = (ImageView) convertView.findViewById(R.id.iv_guanzhu);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }


        final MyHolder finalHolder = holder;
        holder.iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalHolder.iv_guanzhu.setImageResource(R.drawable.yiguanzhu);
            }
        });
        Famous famous = list.get(position);
        holder.tv_name.setText(famous.getNinkName());
        holder.tv_notice.setText(famous.getNotice());
        holder.tv_fans.setText(famous.getFans() + "人关注" + "" + " | " + famous.getLevel());
        Glide.with(context).load(famous.getHeadFace())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.logo);


        return convertView;
    }

    class MyHolder {
        ImageView logo;
        TextView tv_name;
        TextView tv_notice;
        TextView tv_fans;
        ImageView iv_guanzhu;

    }
}
