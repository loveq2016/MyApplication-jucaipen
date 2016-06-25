package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/12.
 * 用户席位 底部适配器
 */
public class UserBottmAdapter extends BaseAdapter {
    private Context context;

    public UserBottmAdapter(FragmentActivity activity) {

        this.context = activity;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bottomitems, null);
        }

        ImageView lively_iv= (ImageView) convertView.findViewById(R.id.lively_iv);
        TextView lively_name= (TextView) convertView.findViewById(R.id.lively_name);

        return convertView;
    }
}
