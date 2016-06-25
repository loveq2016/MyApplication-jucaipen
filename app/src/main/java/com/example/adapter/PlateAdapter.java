package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.Plate;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 * <p/>
 * 浏览解盘适配器
 */
public class PlateAdapter extends BaseAdapter {
    private Context context;
    private List<Plate> list;

    public PlateAdapter(FragmentActivity activity, List<Plate> list) {
        this.context = activity;
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
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.txitem,null);
        }
        TextView tv_live_body= (TextView) convertView.findViewById(R.id.tv_live_body);

        Plate plate=list.get(position);
        tv_live_body.setText(plate.getBody());


        return convertView;
    }
}
