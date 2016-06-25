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
import com.example.model.OldLive;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 * 往期栏目适配器
 */
public class OldLiveAdapter extends BaseAdapter {
    private Context context;
    private List<OldLive> list;

    public OldLiveAdapter(Context context, List<OldLive> oldLives) {
        this.context = context;
        this.list = oldLives;
    }

    public void setList(List<OldLive> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.oldvodeo, null);
        }
        ImageView video_bg = (ImageView) convertView.findViewById(R.id.video_bg);
        TextView old_tv = (TextView) convertView.findViewById(R.id.old_tv);
        TextView old_title = (TextView) convertView.findViewById(R.id.old_title);
        TextView old_time = (TextView) convertView.findViewById(R.id.old_time);
        TextView old_num = (TextView) convertView.findViewById(R.id.old_num);

        OldLive video = list.get(position);

        Glide.with(context).load(video.getImageUrl())
                .placeholder(R.drawable.approve)
                .into(video_bg);
        //[{"id":427,"title":"投资互动《第二期5.19》","imageUrl":"http37.jpg","videoDate":0,"hits":17,"isSpecial":false}

        //old_tv.setText(video.getPlayCount() + "");
        old_title.setText(video.getTitle());
        old_time.setText(video.getVideoDate()+"");
        old_num.setText(video.getHits() + "");
        old_tv.setText(video.getHits()+"");


        return convertView;
    }
}
