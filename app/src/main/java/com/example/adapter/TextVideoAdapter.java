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
import com.example.model.TextVideo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/11.
 * <p/>
 * 视频直播 适配器
 */
public class TextVideoAdapter extends BaseAdapter {
    private Context context;
    private List<TextVideo> list;

    public TextVideoAdapter(FragmentActivity activity, List<TextVideo> list) {
        this.context = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<TextVideo> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_live_item, null);
            holder.live_iv = (ImageView) convertView.findViewById(R.id.live_iv);
            holder.live_title = (TextView) convertView.findViewById(R.id.live_title);
            holder.live_teachername = (TextView) convertView.findViewById(R.id.live_teachername);
            holder.live_attnum = (TextView) convertView.findViewById(R.id.live_attnum);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }


        TextVideo video = list.get(position);
        Glide.with(context).load(video.getImages())
                .placeholder(R.drawable.approve)
                .into(holder.live_iv);
        holder.live_title.setText(video.getTitle());
        holder.live_teachername.setText("讲师：" + video.getTeacherName());
        holder.live_attnum.setText(video.getAttentNum() + "" + "人已参与");
        return convertView;
    }

    class MyHolder {
        ImageView live_iv;
        TextView live_title;
        TextView live_teachername;
        TextView live_attnum;

    }

}
