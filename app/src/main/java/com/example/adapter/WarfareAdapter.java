package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Warfare;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21.
 * <p/>
 * 战法适配器
 */
public class WarfareAdapter extends RecyclerView.Adapter<WarfareAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<Warfare> list;
    private WarfareAdapterOnItemClickLinsstenr onItemClickLinsstenr;

    public WarfareAdapter(Context context, List<Warfare> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.warfitem, null);
        MyHolder myHolder = new MyHolder(view);
        view.setOnClickListener(this);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Warfare warfare = list.get(position);
        Glide.with(context).load(warfare.getImage())
                .placeholder(R.drawable.approve)
                .into(holder.war_iv);
        holder.war_title.setText(warfare.getTitle());
        holder.war_body.setText(warfare.getDesc());
        holder.war_num.setText(warfare.getOrderNum() + "");
        holder.itemView.setTag(position);

    }

    public void setOnItemClickLinsstenr(WarfareAdapterOnItemClickLinsstenr onItemClickLinsstenr) {
        this.onItemClickLinsstenr = onItemClickLinsstenr;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {

        if (onItemClickLinsstenr!=null){
            onItemClickLinsstenr.onClick(v, (Integer) v.getTag());
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView war_iv;
        TextView war_title;
        TextView war_body;
        TextView war_num;

        public MyHolder(View itemView) {
            super(itemView);
            this.war_iv = (ImageView) itemView.findViewById(R.id.war_iv);
            this.war_title = (TextView) itemView.findViewById(R.id.war_title);
            this.war_body = (TextView) itemView.findViewById(R.id.war_body);
            this.war_num = (TextView) itemView.findViewById(R.id.war_num);
        }
    }

    public interface WarfareAdapterOnItemClickLinsstenr {

        void onClick(View v, int position);
    }
}
