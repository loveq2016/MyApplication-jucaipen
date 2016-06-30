package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Activity.CommentActivity;
import com.example.androidnetwork.R;
import com.example.model.Comments;
import com.example.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/30.
 * <p/>
 * 视频评论适配器
 */
public class VideoCommAdapter extends RecyclerView.Adapter<VideoCommAdapter.MyHolder> implements View.OnClickListener {

    private Context context;
    private List<Comments> list=new ArrayList<>();
    private VideoCommAdapterOnItemClick onItemClick;

    public VideoCommAdapter(FragmentActivity activity, List<Comments> list) {
        this.context = activity;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.commitems, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    public void setList(List<Comments> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Comments comments = list.get(position);
        String headface = comments.getHeadFace();
        if (headface != null && headface.length() > 0) {
            Glide.with(context).load(headface)
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.comm_person);

        }
        holder.comm_name.setText(comments.getUserName());
        holder.comm_time.setText(TimeUtils.parseStrDate(comments.getInsertDate(),"yyyy-MM-dd HH:ss"));
        holder.comm_content.setText(comments.getBody());
        holder.comm_plun.setText("  " + comments.getReptCount() + "");
        holder.comm_dianzan.setText("  " + comments.getGoods() + "");
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClick(VideoCommAdapterOnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    public void onClick(View v) {
        if (onItemClick != null) {
            onItemClick.onClick(v, (Integer) v.getTag());
        }

    }
/*
    public void setList(List<Comments> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.commitems, null);
        }
        ImageView comm_person = (ImageView) convertView.findViewById(R.id.comm_person);
        TextView comm_name = (TextView) convertView.findViewById(R.id.comm_name);
        TextView comm_time = (TextView) convertView.findViewById(R.id.comm_time);
        TextView comm_content = (TextView) convertView.findViewById(R.id.comm_content);
        TextView comm_plun = (TextView) convertView.findViewById(R.id.comm_plun);
        TextView comm_dianzan = (TextView) convertView.findViewById(R.id.comm_dianzan);

        Comments comments = list.get(position);
        String headface = comments.getHeadFace();
        if (headface != null && headface.length() > 0) {
            Glide.with(context).load(headface)
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(comm_person);

        }
        comm_name.setText(comments.getUserName());
        comm_time.setText(comments.getInsertDate());
        comm_content.setText(comments.getBody());
        comm_plun.setText("  "+comments.getReptCount() + "");
        comm_dianzan.setText("  "+comments.getGoods() + "");


        comm_plun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context,CommentActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }*/


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView comm_person;
        TextView comm_name;
        TextView comm_time;
        TextView comm_content;
        TextView comm_plun;
        TextView comm_dianzan;

        public MyHolder(View itemView) {
            super(itemView);
            this.comm_person = (ImageView) itemView.findViewById(R.id.comm_person);
            this.comm_name = (TextView) itemView.findViewById(R.id.comm_name);
            this.comm_time = (TextView) itemView.findViewById(R.id.comm_time);
            this.comm_content = (TextView) itemView.findViewById(R.id.comm_content);
            this.comm_plun = (TextView) itemView.findViewById(R.id.comm_plun);
            this.comm_dianzan = (TextView) itemView.findViewById(R.id.comm_dianzan);
        }
    }

    public interface VideoCommAdapterOnItemClick {

        void onClick(View v, int position);
    }
}


