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
 * Created by Administrator on 2016/5/16.
 * <p/>
 * 相关视频
 */
public class OldAdapter extends BaseAdapter {
    private Context context;
    private List<Video> list;

    public OldAdapter(FragmentActivity activity, List<Video> videoList) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.oldvideo, null);
        }
        ImageView video_bg = (ImageView) convertView.findViewById(R.id.video_bg);
        TextView old_context = (TextView) convertView.findViewById(R.id.old_context);
        TextView tv_indruce = (TextView) convertView.findViewById(R.id.tv_indruce);
        TextView video_month = (TextView) convertView.findViewById(R.id.video_month);
        TextView video_date = (TextView) convertView.findViewById(R.id.video_date);

        Video video = list.get(position);
        String imageUrl = video.getImageUrl();
        Glide.with(context).load(imageUrl)
                .placeholder(R.drawable.approve)
                .into(video_bg);
        old_context.setText(video.getTitle());
        tv_indruce.setText(video.getDesc());
       // video_month.setText(video.getVideoDate());
        video_date.setText(video.getPlayCount()+"");


        return convertView;
    }
}
