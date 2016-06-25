package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/30.
 * <p/>
 * 视频评论适配器
 */
public class VideoCommAdapter extends BaseAdapter {

    private Context context;
    private List<Comments> list;

    public VideoCommAdapter(FragmentActivity activity, List<Comments> list) {
        this.context = activity;
        this.list = list;
    }

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
    }
}
