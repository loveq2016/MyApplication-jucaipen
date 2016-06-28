package com.example.adapter;

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
 * 今日直播 适配器
 */
public class TodayLive extends RecyclerView.Adapter<TodayLive.MyHolder> {


    private final List<Studio> studios;

    public TodayLive(List<Studio> studios) {
        this.studios=studios;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todylive, null);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TextView tv_context = (TextView) holder.itemView.findViewById(R.id.tv_context);
        ImageView video_bg = (ImageView) holder.itemView.findViewById(R.id.video_bg);
        TextView video_month=(TextView)holder.itemView.findViewById(R.id.video_month);

        video_bg.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(holder.itemView.getContext()).load(studios.get(position).getImageUrl())
                .placeholder(R.drawable.approve)
                .into(video_bg);
        tv_context.setText(studios.get(position).getTitle());
        video_month.setText(studios.get(position).getStartDate());

    }

    @Override
    public int getItemCount() {
        return studios.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }


}
