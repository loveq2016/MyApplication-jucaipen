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
import com.example.model.Video;

import java.util.List;

/**
 * Created by jucaipen on 16/5/10.
 */
public class VideoPlayAdapter extends BaseAdapter {
    private Context context;
    private List<Video> list;

    public VideoPlayAdapter(FragmentActivity activity, List<Video> videoList) {
        this.context = activity;
        this.list = videoList;
    }

    public void setList(List<Video> list) {
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
        MyHolder holder = null;
        if (convertView == null) {
            holder=new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.tvitems, null);
            holder.select_iv = (ImageView) convertView.findViewById(R.id.select_iv);
            holder.tv_context = (TextView) convertView.findViewById(R.id.tv_context);
            holder.live_content = (TextView) convertView.findViewById(R.id.live_content);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }

        holder.select_iv.setScaleType(ImageView.ScaleType.FIT_XY);

        Video video = list.get(position);
        if (video.getImageUrl() != null) {
            Glide.with(context).load(video.getImageUrl()).into(holder.select_iv);
        }
        holder.tv_context.setText(video.getTitle());
        holder.live_content.setText(video.getDesc());


        return convertView;
    }

    class MyHolder {
        ImageView select_iv;
        TextView tv_context;
        TextView live_content;

    }
}
