package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Activity.HotCareful;
import com.example.androidnetwork.R;
import com.example.model.Comments;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/14.
 * <p/>
 * 评论适配器
 */
public class CommentAdapter extends BaseAdapter {
    private List<Comments> list;
    private Context context;

    public CommentAdapter(List<Comments> comms, HotCareful careful) {
        this.list = comms;
        this.context = careful;
    }

    public void setList(List<Comments> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.commitems, null);
        }
        ImageView comm_person = (ImageView) view.findViewById(R.id.comm_person);
        TextView comm_name = (TextView) view.findViewById(R.id.comm_name);
        comm_name.setText(list.get(position).getUserName());
        TextView comm_time = (TextView) view.findViewById(R.id.comm_time);
        comm_time.setText(list.get(position).getInsertDate());
        TextView comm_content = (TextView) view.findViewById(R.id.comm_content);
        comm_content.setText(list.get(position).getBody());
        TextView comm_dianzan = (TextView) view.findViewById(R.id.comm_dianzan);
        comm_dianzan.setText(list.get(position).getGoods() + "");
        TextView comm_plun = (TextView) view.findViewById(R.id.comm_plun);
        comm_plun.setText(list.get(position).getReptCount() + "");
        Glide.with(context).load(list.get(position).getHeadFace())
                .bitmapTransform(new CropCircleTransformation(context))
                .into(comm_person);
       // comm_content.loadDataWithBaseURL(null,list.get(position).getBody(),"text/html","utf-8",null);

        return view;
    }
      /*  private  Context context;
    List<Comments> comms;

    public CommentAdapter(List<Comments> comms, Context context) {
        this.context=context;
        this.comms = comms;
    }

    public void setComms(List<Comments> comms) {
        this.comms = comms;
    }

    @Override
    public ViewHolderGroup onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate( R.layout.commitems, null);
        ViewHolderGroup holderGroup = new ViewHolderGroup(view);
        return holderGroup;
    }

    @Override
    public void onBindViewHolder(ViewHolderGroup holder, int position) {
        Toast.makeText(context, ""+comms.size(), Toast.LENGTH_SHORT).show();
        ImageView comm_person = (ImageView) holder.itemView.findViewById(R.id.comm_person);
        TextView comm_name = (TextView) holder.itemView.findViewById(R.id.comm_name);
        comm_name.setText(comms.get(position).getUserName());
        TextView comm_time = (TextView) holder.itemView.findViewById(R.id.comm_time);
        comm_time.setText(comms.get(position).getInsertDate());
        TextView comm_content = (TextView) holder.itemView.findViewById(R.id.comm_content);
        comm_content.setText(comms.get(position).getBody());
        TextView comm_dianzan = (TextView) holder.itemView.findViewById(R.id.comm_dianzan);
        comm_dianzan.setText(comms.get(position).getGoods() + "");
        TextView comm_plun = (TextView) holder.itemView.findViewById(R.id.comm_plun);
        comm_plun.setText(comms.get(position).getReptCount() + "");
        Glide.with(context).load(comms.get(position).getHeadFace())
                .bitmapTransform(new CropCircleTransformation(holder.itemView.getContext()))
                .into(comm_person);


    }

    @Override
    public int getItemCount() {
        return comms.size();

    }


    class ViewHolderGroup extends XRecyclerView.ViewHolder {
        View itemView;
        public ViewHolderGroup(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }*/


}
