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
import com.example.model.Interlocution;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/10.
 * <p/>
 * 问答适配器
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<Interlocution> list;
    private OnRecyclerViewItemClickListener viewItemClickListener;

    public QuestionAdapter(FragmentActivity activity, List<Interlocution> list) {
        this.context = activity;
        this.list = list;
    }

    public void setList(List<Interlocution> list) {
        this.list = list;
    }

    public void setViewItemClickListener(OnRecyclerViewItemClickListener viewItemClickListener) {
        this.viewItemClickListener = viewItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_ask_item, null);
        MyHolder myHolder = new MyHolder(view);
        view.setOnClickListener(this);

        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Interlocution interlocution = list.get(position);
        if (interlocution.getHeadFace() != null) {
            Glide.with(context).load(interlocution.getHeadFace())
                    .bitmapTransform(new CropCircleTransformation(context))
                    .placeholder(R.drawable.rentou)
                    .into(holder.for_picture);
        }

        if (interlocution.getIsPay() == 1) {
            holder.iv_ispay.setImageResource(R.drawable.word_lock_clear);
        }
        holder.for_name.setText(interlocution.getTrueName());
        holder.for_time.setText(interlocution.getInsertDate());
        holder.for_body.setText(interlocution.getAskBodys());
        holder.for_comment.setText(" " + interlocution.getIsReply() + "");
        holder.itemView.setTag(position);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (viewItemClickListener != null) {
            viewItemClickListener.setonClick(v, (Integer) v.getTag());
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView for_picture;
        TextView for_name;
        TextView for_time;
        TextView for_body;
        TextView for_comment;
        ImageView iv_ispay;

        public MyHolder(View itemView) {
            super(itemView);
            this.for_picture = (ImageView) itemView.findViewById(R.id.for_picture);
            this.for_name = (TextView) itemView.findViewById(R.id.for_name);
            this.for_time = (TextView) itemView.findViewById(R.id.for_time);
            this.for_body = (TextView) itemView.findViewById(R.id.for_body);
            this.for_comment = (TextView) itemView.findViewById(R.id.for_comment);
            this.iv_ispay = (ImageView) itemView.findViewById(R.id.iv_ispay);
        }
    }

    public interface OnRecyclerViewItemClickListener {

        void setonClick(View v, Integer position);
    }
}
