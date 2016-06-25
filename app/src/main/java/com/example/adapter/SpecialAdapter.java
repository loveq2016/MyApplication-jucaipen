package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/4.
 *
 * 专辑适配器
 */
public class SpecialAdapter extends BaseAdapter{
    private Context context;
    public SpecialAdapter(FragmentActivity activity) {
        this.context=activity;
    }

    @Override
    public int getCount() {
        return 15;
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.specialitem,null);
        }
        return convertView;
    }
}
