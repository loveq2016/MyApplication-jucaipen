package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Friend;
import com.example.utils.TimeUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by Administrator on 2016/5/23.
 *
 * 我的邀请适配器
 */
public class InviteAdapter extends BaseAdapter{
    private Context context;
    private List<Friend> list;
    public InviteAdapter(Context context, List<Friend> list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setList(List<Friend> list) {
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

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.inviteitem,null);
        }

        ImageView iv_invite= (ImageView) convertView.findViewById(R.id.iv_invite);
        TextView invite_title= (TextView) convertView.findViewById(R.id.invite_title);
        TextView invite_time= (TextView) convertView.findViewById(R.id.invite_time);
        TextView invite_phone= (TextView) convertView.findViewById(R.id.invite_phone);
        Friend friend=list.get(position);
        Glide.with(context).load(friend.getRecommderImage())
                .placeholder(R.drawable.rentou)
                .bitmapTransform(new CropCircleTransformation(context))
                .into(iv_invite);
        invite_title.setText(friend.getRecommder());
        invite_time.setText(TimeUtils.parseStrDate(friend.getRecommderDate(),"yyyy-MM-dd"));
        invite_phone.setText(friend.getRecommderPhone());

        return convertView;
    }
}
