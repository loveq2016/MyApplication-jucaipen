package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Collect;

import java.util.List;


/**
 * Created by Administrator on 2016/6/2.
 * <p/>
 * 收藏视频适配器
 */
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<Collect> list;
    private LiveAdapter.OnRecyclerViewItemClickListener recyclerViewItemClickListener;

    public LiveAdapter(FragmentActivity activity, List<Collect> list) {
        this.context = activity;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.liveitem, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    public void setList(List<Collect> list) {
        this.list = list;
    }

    public void setRecyclerViewItemClickListener(OnRecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Collect collect = list.get(position);
        holder.video_title.setText(collect.getTitle());
        holder.video_body.setText(collect.getInsertDate());
        Glide.with(context).load(collect.getImages())
                .placeholder(R.drawable.approve)
                .into(holder.live_head);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (recyclerViewItemClickListener != null) {
            recyclerViewItemClickListener.onClick(v, (Integer) v.getTag());
        }

    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView live_head;
        TextView video_title;
        TextView video_body;

        public MyHolder(View itemView) {
            super(itemView);
            this.live_head = (ImageView) itemView.findViewById(R.id.live_head);
            this.video_title = (TextView) itemView.findViewById(R.id.video_title);
            this.video_body = (TextView) itemView.findViewById(R.id.video_body);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View v, int postion);
    }
}
