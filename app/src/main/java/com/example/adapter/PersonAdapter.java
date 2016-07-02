package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Famous;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/9.
 * <p/>
 * 名家
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<Famous> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener;


    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public PersonAdapter(Context context, List<Famous> famousList) {
        this.context = context;
        this.list = famousList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_famous_item, null);
        MyHolder myHolder = new MyHolder(view);
        view.setOnClickListener(this);
        return myHolder;
    }

    public void setList(List<Famous> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Famous famous = list.get(position);
        holder.tv_name.setText(famous.getNinkName());
        holder.tv_notice.setText(famous.getNotice());
        holder.tv_fans.setText(famous.getFans() + "人关注" + "" + " | " + famous.getLevel());
        Glide.with(context).load(famous.getHeadFace())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(holder.logo);
        int isEnd = famous.getIsEnd();


        if (isEnd == 2) {
            // 直播状态 1未开始 2进行中 3已结束
            holder.iv_guanzhu.setImageResource(R.drawable.xzh_shexiang);

        } else {
            holder.iv_guanzhu.setImageResource(R.drawable.shexiang);
        }


        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView tv_name;
        TextView tv_notice;
        TextView tv_fans;
        ImageView iv_guanzhu;

        public MyHolder(View itemView) {
            super(itemView);
            this.logo = (ImageView) itemView.findViewById(R.id.att_logo);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_names);
            this.tv_notice = (TextView) itemView.findViewById(R.id.tv_notice);
            this.tv_fans = (TextView) itemView.findViewById(R.id.tv_fans);
            this.iv_guanzhu = (ImageView) itemView.findViewById(R.id.iv_guanzhu);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }
}
