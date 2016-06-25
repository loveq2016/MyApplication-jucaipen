package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Studio;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 * <p/>
 * 人气榜适配器
 */
public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MyHolder> {

    private List<Studio> list;
    private Context context;

    public MoodAdapter(List<Studio> studios, FragmentActivity activity) {
        this.list = studios;
        this.context = activity;

    }

    @Override
    public MoodAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood, null);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoodAdapter.MyHolder holder, int position) {
        TextView person_name = (TextView) holder.itemView.findViewById(R.id.person_name);
        ImageView person_image = (ImageView) holder.itemView.findViewById(R.id.person_image);
        TextView person_num = (TextView) holder.itemView.findViewById(R.id.person_num);
        Studio studio = list.get(position);
        person_name.setText(studio.getTitle());
        Glide.with(context).load(studio.getImageUrl())
                .placeholder(R.drawable.approve)
                .into(person_image);

        person_num.setText("总人气：" + studio.getRenQi() + "");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
