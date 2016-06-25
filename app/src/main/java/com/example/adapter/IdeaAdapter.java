package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Opinion;
import com.example.utils.TimeUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by jucaipen on 16/5/10.
 */
public class IdeaAdapter extends XRecyclerView.Adapter<IdeaAdapter.MyHolder> {
    private List<Opinion> list;
    private Context context;


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
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

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
        holder.point_bodys.setText(Html.fromHtml(opinion.getBodys()));
        holder.point_plun.setText(opinion.getHits() + "");
        holder.tv_dianzan.setText(opinion.getGoods() + "");
        holder.tv_idDate.setText(TimeUtils.parseStrDate(opinion.getInsertdate(),"yyyy-MM-dd HH:mm"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /*public IdeaAdapter(Context context, List<Opinion> opinionList) {
        this.context = context;
        this.list = opinionList;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<Opinion> list) {
        this.list = list;
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
        MyHolder holder = null;

        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.viewpoint, null);
            holder.person_iv = (ImageView) convertView.findViewById(R.id.person_iv);
            holder.point_name = (TextView) convertView.findViewById(R.id.point_name);
            holder.point_touzi = (TextView) convertView.findViewById(R.id.point_touzi);
            holder.point_title = (TextView) convertView.findViewById(R.id.point_title);
            holder.point_bodys = (TextView) convertView.findViewById(R.id.idea_body);
            holder.point_plun = (TextView) convertView.findViewById(R.id.point_plun);
            holder.tv_dianzan = (TextView) convertView.findViewById(R.id.tv_dianzan);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }

        Opinion opinion = list.get(position);
        if (opinion.getHeadface() != null) {
            Glide.with(convertView.getContext()).load(opinion.getHeadface())
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.person_iv);
        }
        holder.point_name.setText(opinion.getNickname());
        holder.point_touzi.setText(opinion.getLevel());
        holder.point_title.setText(opinion.getTitle());
        holder.point_bodys.setText(Html.fromHtml(opinion.getBodys()));
        holder.point_plun.setText(opinion.getHits() + "");
        holder.tv_dianzan.setText(opinion.getGoods() + "");
        return convertView;
    }
*/

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView person_iv;
        TextView point_name;
        TextView point_touzi;
        TextView point_title;
        TextView point_bodys;
        TextView point_plun;
        TextView tv_dianzan;
        TextView tv_idDate;

        public MyHolder(View itemView) {
            super(itemView);
            this.person_iv = (ImageView) itemView.findViewById(R.id.person_iv);
            this.point_name = (TextView) itemView.findViewById(R.id.point_name);
            this.point_touzi = (TextView) itemView.findViewById(R.id.point_touzi);
            this.point_title = (TextView) itemView.findViewById(R.id.point_title);
            this.point_bodys = (TextView) itemView.findViewById(R.id.point_bodys);
            this.point_plun = (TextView) itemView.findViewById(R.id.point_plun);
            this.tv_dianzan = (TextView) itemView.findViewById(R.id.tv_dianzan);
            this.tv_idDate= (TextView) itemView.findViewById(R.id.tv_idDate);        }
    }
}
