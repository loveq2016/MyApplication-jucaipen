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

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/6/8.
 * <p/>
 * 文字直播适配器
 */
public class TextAdapter extends BaseAdapter {
    private Context context;
    private List<TextVideo> list;

    public TextAdapter(FragmentActivity activity, List<TextVideo> list) {
        this.context = activity;
        this.list = list;

    }

    public void setList(List<TextVideo> list) {
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
            holder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.livetextitem, null);
            holder.tv_videoFace = (ImageView) convertView.findViewById(R.id.tv_videoFace);
            holder.tv_videoName = (TextView) convertView.findViewById(R.id.tv_videoName);
            holder.tv_VideoNum = (TextView) convertView.findViewById(R.id.tv_VideoNum);
            holder.tv_VideoTitle = (TextView) convertView.findViewById(R.id.tv_VideoTitle);
            holder.tv_videoBody = (TextView) convertView.findViewById(R.id.tv_videoBody);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }



        TextVideo video = list.get(position);
        if (video.getTeacherFace() != null) {
            Glide.with(context).load(video.getTeacherFace())
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.tv_videoFace);
        }
        holder.tv_videoName.setText(video.getTeacherName());
        holder.tv_VideoNum.setText("  " + video.getAttentNum() + "");
        holder.tv_VideoTitle.setText(video.getTitle());
        holder.tv_videoBody.setText(video.getTitle());

        return convertView;
    }

    class MyHolder {
        ImageView tv_videoFace;
        TextView tv_videoName;
        TextView tv_VideoNum;
        TextView tv_VideoTitle;
        TextView tv_videoBody;

    }
}
