package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.ChatMsg;
import com.example.utils.TimeUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/12.
 */
public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<ChatMsg> list;

    public void setList(List<ChatMsg> list) {
        this.list = list;
    }

    public ChatAdapter(FragmentActivity activity, List<ChatMsg> list) {
        this.context = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getViewTypeCount() {
        return 2;
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


       // int type= getItemViewType(position);
        //if (type == 5) {
            //返回我的
            //convertView = LayoutInflater.from(context).inflate(R.layout.ui_my_chat_item, null);

       // } else {
            //用户的聊天记录
        MyHolder holder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_other_chat_item, null);
            holder=new MyHolder();
            holder.chat_body = (WebView) convertView.findViewById(R.id.chat_body);
            holder.chat_image= (ImageView) convertView.findViewById(R.id.chat_image);
            holder.chat_time= (TextView) convertView.findViewById(R.id.chat_time);
            holder.chat_name= (TextView) convertView.findViewById(R.id.chat_name);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }
       // }


        holder.chat_body.loadData(list.get(position).getMsg(),"text/html; charset=UTF-8",null);
        Glide.with(context).load(list.get(position).getFromFace()).bitmapTransform(new CropCircleTransformation(context))
                .placeholder(R.drawable.rentou)
                .into(holder.chat_image);
        holder.chat_name.setText(list.get(position).getSendName());
        holder.chat_time.setText(TimeUtils.parseStrDate(list.get(position).getSendDate(),"yyyy-MM-dd HH:mm:ss"));

        return  convertView;


    }

    class  MyHolder{
        WebView chat_body;
        ImageView chat_image;
        TextView chat_time;
        TextView chat_name;


    }
}
