package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.City;

import java.util.List;

/**
 * Created by Administrator on 2016/6/13.
 * 城市列表适配器
 */
public class ProviceAdapter extends BaseAdapter {
    private Context context;
    private List<City> list;

    public ProviceAdapter(Context context, List<City> list) {
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cityitem, null);
        }
        TextView tv_city= (TextView) convertView.findViewById(R.id.tv_city);
        tv_city.setText(list.get(position).getName());
        return convertView;
    }
}
