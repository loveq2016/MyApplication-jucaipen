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
import com.example.model.Press;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by jucaipen on 16/5/9.
 * <p/>
 * 今日热点
 */
public class HotAdapter extends BaseAdapter {
    private Context context;
    private List<Press> list;

    public HotAdapter(Context context, List<Press> pressList) {
        this.context = context;
        this.list = pressList;
    }


    public void setList(List<Press> list) {
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

        MyHolder holder = null;
        if (convertView == null) {
            holder=new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.hotitems, null);

            holder.hotimageview = (ImageView) convertView.findViewById(R.id.hotimageview);
            holder.hot_title = (TextView) convertView.findViewById(R.id.hot_title);
            holder.hotcontext = (TextView) convertView.findViewById(R.id.hotcontext);
            holder.tv_hit = (TextView) convertView.findViewById(R.id.tv_hit);
            convertView.setTag(holder);
        }else {
            holder= (MyHolder) convertView.getTag();
        }


        Press press = list.get(position);
        holder.hot_title.setText(press.getTitle());
        holder.hotcontext.setText(press.getFrom() + "  " + TimeUtils.parseStrDate(press.getInsertDate(), "yyyy-MM-dd HH:mm"));

        holder.tv_hit.setText(press.getComms() + "" + "评论");

        holder.hotimageview.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(press.getImageThumb())
                .placeholder(R.drawable.bg)
                .into(holder.hotimageview);

        return convertView;
    }

    class MyHolder {
        ImageView hotimageview;
        TextView hot_title;
        TextView hotcontext;
        TextView tv_hit;
    }
}
