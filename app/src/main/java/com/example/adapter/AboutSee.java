package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Activity.HotCareful;
import com.example.androidnetwork.R;
import com.example.model.Related;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 *
 * 相关阅读适配器
 */
public class AboutSee extends BaseAdapter{
    private Context context;
    private List<Related> list;

    public AboutSee(HotCareful hotCareful, List<Related> relatedList) {
        this.context=hotCareful;
        this.list=relatedList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<Related> list) {
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

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.hotitems,null);
        }
        Related related=list.get(position);

        ImageView hotimageview = (ImageView) convertView.findViewById(R.id.hotimageview);
        TextView hot_title = (TextView) convertView.findViewById(R.id.hot_title);
        TextView hotcontext = (TextView) convertView.findViewById(R.id.hotcontext);
        TextView tv_hit = (TextView) convertView.findViewById(R.id.tv_hit);

        if (related.getImage()!=null){
            Glide.with(context).load(related.getImage()).into(hotimageview);
        }
        hot_title.setText(related.getTitle());
        hotcontext.setText(related.getFrom()+"  "+ TimeUtils.parseStrDate(related.getInsertDate(), "yyyy-MM-dd HH:mm"));
        tv_hit.setText(related.getComms() + "" + "评论");


        return convertView;
    }
}
