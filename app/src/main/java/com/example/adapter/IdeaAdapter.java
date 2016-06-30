package com.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Opinion;
import com.example.utils.StringUtil;
import com.example.utils.TimeUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/10.
 */
public class IdeaAdapter extends XRecyclerView.Adapter<IdeaAdapter.MyHolder> implements View.OnClickListener {
    private List<Opinion> list;
    private Context context;
    private OnRecyclerViewItemClickListener itemClickListener;


    public IdeaAdapter(Context context, List<Opinion> opinionList) {
        this.context = context;
        this.list = opinionList;
    }

    public void setList(List<Opinion> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpoint, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        Opinion opinion = list.get(position);
        if (opinion.getHeadface() != null) {
            Glide.with(context).load(opinion.getHeadface())
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.person_iv);
        }
        holder.point_name.setText(opinion.getNickname());
        holder.point_touzi.setText(opinion.getLevel());
        holder.point_title.setText(opinion.getTitle());
        holder.point_bodys.setText(StringUtil.clearHTMLCode(opinion.getBodys()));

        holder.point_plun.setText(opinion.getHits() + "");
        holder.tv_dianzan.setText(opinion.getGoods() + "");
        holder.tv_idDate.setText(TimeUtils.parseStrDate(opinion.getInsertdate(), "yyyy-MM-dd HH:mm"));
        holder.tv_dianzan.setText("100");
        holder.itemView.setTag(position);
        holder.tv_dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "zan" + position, Toast.LENGTH_SHORT).show();
                holder.tv_dianzan.setChecked(true);
            }
        });
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            itemClickListener.onClick(v, (Integer) v.getTag());
        }

    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView person_iv;
        TextView point_name;
        TextView point_touzi;
        TextView point_title;
        TextView point_bodys;
        TextView tv_idDate;
        RadioButton tv_dianzan;
        RadioButton point_plun;

        public MyHolder(View itemView) {
            super(itemView);
            this.person_iv = (ImageView) itemView.findViewById(R.id.person_iv);
            this.point_name = (TextView) itemView.findViewById(R.id.point_name);
            this.point_touzi = (TextView) itemView.findViewById(R.id.point_touzi);
            this.point_title = (TextView) itemView.findViewById(R.id.point_title);
            this.point_bodys = (TextView) itemView.findViewById(R.id.point_bodys);
            this.point_plun = (RadioButton) itemView.findViewById(R.id.point_plun);
            this.tv_idDate = (TextView) itemView.findViewById(R.id.tv_idDate);
            this.tv_dianzan = (RadioButton) itemView.findViewById(R.id.tv_dianzan);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, int date);
    }
}
