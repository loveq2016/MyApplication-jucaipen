package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Special;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 * <p/>
 * 选集
 */
public class TodayAdapter extends XRecyclerView.Adapter<TodayAdapter.MyHolder> implements View.OnClickListener {

    private List<Special> list;
    private TodayAdapterOnItemOnClick onItemOnClick;
    private Context context;


    public TodayAdapter(FragmentActivity activity, List<Special> specialList) {
        this.list = specialList;
        this.context = activity;

    }

    public void setList(List<Special> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvitems, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_context);
        Special special = list.get(position);
        Glide.with(context).load(special.getImageUrl()).placeholder(R.drawable.approve).into(holder.select_iv);
        holder.live_content.setText(special.getDesc());
        holder.tv_context.setText(special.getTitle());
        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemOnClick(TodayAdapterOnItemOnClick onItemOnClick) {
        this.onItemOnClick = onItemOnClick;
    }

    @Override
    public void onClick(View v) {
        if (onItemOnClick!=null){
            onItemOnClick.onClick(v, (Integer) v.getTag());
        }
    }

    class MyHolder extends XRecyclerView.ViewHolder {

        ImageView select_iv;
        TextView tv_context;
        TextView live_content;

        public MyHolder(View itemView) {
            super(itemView);
            this.select_iv = (ImageView) itemView.findViewById(R.id.select_iv);
            this.tv_context = (TextView) itemView.findViewById(R.id.tv_context);
            this.live_content = (TextView) itemView.findViewById(R.id.live_content);
        }
    }

    public interface TodayAdapterOnItemOnClick {

        void onClick(View v, int position);
    }
}
