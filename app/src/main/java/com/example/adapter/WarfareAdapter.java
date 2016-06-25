package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Warfare;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 * <p/>
 * 战法适配器
 */
public class WarfareAdapter extends BaseAdapter {
    private Context context;
    private List<Warfare> list;

    public WarfareAdapter(FragmentActivity activity, List<Warfare> list) {
        this.context = activity;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.warfitem, null);
        }


        ImageView war_iv = (ImageView) convertView.findViewById(R.id.war_iv);
        TextView war_title = (TextView) convertView.findViewById(R.id.war_title);
        TextView war_body = (TextView) convertView.findViewById(R.id.war_body);
        TextView war_num = (TextView) convertView.findViewById(R.id.war_num);


        Warfare warfare = list.get(position);
        Glide.with(context).load(warfare.getImage())
                .placeholder(R.drawable.approve)
                .into(war_iv);
        war_title.setText(warfare.getTitle());
        war_body.setText(warfare.getDesc());
        war_num.setText(warfare.getOrderNum() + "");


        return convertView;
    }
}
