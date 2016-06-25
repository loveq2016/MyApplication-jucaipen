package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.androidnetwork.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public ChatAdapter(FragmentActivity activity, List<String> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_other_chat_item, null);
        }
        TextView chat_body = (TextView) convertView.findViewById(R.id.chat_body);
        chat_body.setText(list.get(position));

        // int type= getItemViewType(position);
        if (position == 5) {
            //返回我的
            convertView = LayoutInflater.from(context).inflate(R.layout.ui_my_chat_item, null);

            return convertView;
        } else {
            //用户的聊天记录
            return convertView;
        }


    }
}
