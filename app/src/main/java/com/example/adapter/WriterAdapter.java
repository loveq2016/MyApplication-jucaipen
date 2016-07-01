package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.School;
import com.example.utils.StringUtil;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 * <p/>
 * 文字直播适配器
 */
public class WriterAdapter extends BaseAdapter {
    private Context context;
    private List<School> list;

    public WriterAdapter(Context context, List<School> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.writeritem, null);
        }
        TextView writer_time = (TextView) convertView.findViewById(R.id.writer_time);
        TextView writer_body = (TextView) convertView.findViewById(R.id.writer_body);
        ImageView iv_liveLock= (ImageView) convertView.findViewById(R.id.tv_liveLock);

        School school = list.get(position);
        String time = TimeUtils.parseStrDate(school.getStartDate(), "yyyy-MM-dd");
        writer_time.setText(time);
        writer_body.setText(StringUtil.clearHTMLCode(school.getTitle()));
        int free=school.getIsFree();
        ////是否收费（1否，2是）
       /* if(free==1){
            iv_liveLock.setVisibility(View.GONE);
        }else {
            iv_liveLock.setVisibility(View.VISIBLE);
        }*/


        return convertView;
    }
}
