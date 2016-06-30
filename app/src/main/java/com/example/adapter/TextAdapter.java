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
import com.example.model.TextVideo;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/6/8.
 * <p/>
 * 文字直播适配器
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<TextVideo> list;
    private TextAdapter.OnItemLinnser onItemLinnser;

    public TextAdapter(FragmentActivity activity, List<TextVideo> list) {
        this.context = activity;
        this.list = list;

    }

    public void setList(List<TextVideo> list) {
        this.list = list;
    }


    public void setOnItemLinnser(OnItemLinnser onItemLinnser) {
        this.onItemLinnser = onItemLinnser;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.livetextitem, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        TextVideo video = list.get(position);
        if (video.getTeacherFace() != null) {
            Glide.with(context).load(video.getTeacherFace())
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.tv_videoFace);
        }
        holder.tv_videoName.setText(video.getTeacherName());
        holder.tv_VideoNum.setText("  " + video.getAttentNum() + "");
        holder.tv_VideoTitle.setText(video.getTitle());
        holder.tv_videoBody.setText(video.getTitle());
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemLinnser!=null){
            onItemLinnser.OnClick(v, (Integer) v.getTag());
        }
    }


    class MyHolder extends RecyclerView.ViewHolder {
        ImageView tv_videoFace;
        TextView tv_videoName;
        TextView tv_VideoNum;
        TextView tv_VideoTitle;
        TextView tv_videoBody;

        public MyHolder(View itemView) {
            super(itemView);

            this.tv_videoFace = (ImageView) itemView.findViewById(R.id.tv_videoFace);
            this.tv_videoName = (TextView) itemView.findViewById(R.id.tv_videoName);
            this.tv_VideoNum = (TextView) itemView.findViewById(R.id.tv_VideoNum);
            this.tv_VideoTitle = (TextView) itemView.findViewById(R.id.tv_VideoTitle);
            this.tv_videoBody = (TextView) itemView.findViewById(R.id.tv_videoBody);
        }
    }

    public interface OnItemLinnser {
        void OnClick(View v, int Position);
    }
}
