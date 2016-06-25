package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.TeacherDate;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
public class TeacherAdapter extends BaseAdapter{
    private Context context;
    private List<TeacherDate> list;

    public TeacherAdapter(FragmentActivity activity, List<TeacherDate> list) {
        this.context=activity;
        this.list=list;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.ui_ideaitem,null);
        }
        TeacherDate teacherDate=list.get(position);
        TextView idea_title= (TextView) convertView.findViewById(R.id.idea_title);
        TextView ra_context= (TextView) convertView.findViewById(R.id.ra_context);
        TextView ra_time= (TextView) convertView.findViewById(R.id.ra_time);
        idea_title.setText(teacherDate.getTitle());
        ra_context.setText(Html.fromHtml(teacherDate.getDesc()));
        String time=TimeUtils.parseStrDate(teacherDate.getInsertDate(),"yyyy-MM-dd HH:ss");
        ra_time.setText(time+" | 阅读数："+teacherDate.getReadNum()+" ");


        return convertView;
    }
}
