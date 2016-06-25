package com.example.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.Opinion;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 * <p/>
 * 观点适配器
 */
public class PageIdeaAdapter extends BaseAdapter {
    private Context context;
    private List<Opinion> opinionList;

    public PageIdeaAdapter(Context context, List<Opinion> opinionList) {
        this.context = context;
        this.opinionList = opinionList;

    }

    @Override
    public int getCount() {
        return opinionList.size();
    }

    @Override
    public Object getItem(int position) {
        return opinionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pageidea, null);
        }


        //  ImageView for_picture= (ImageView) convertView.findViewById(R.id.for_picture);
        // TextView for_name= (TextView) convertView.findViewById(R.id.for_name);
        TextView for_time = (TextView) convertView.findViewById(R.id.for_time);
        // TextView for_type= (TextView) convertView.findViewById(R.id.for_type);
        TextView for_title = (TextView) convertView.findViewById(R.id.for_title);
        TextView for_body = (TextView) convertView.findViewById(R.id.for_body);


        Opinion opinion = opinionList.get(position);
//        Glide.with(context).load(opinion.getHeadface())
//                .bitmapTransform(new CropCircleTransformation(context))
//                .placeholder(R.drawable.rentou)
//                .into(for_picture);
//        for_name.setText(opinion.getNickname());
        for_time.setText(TimeUtils.parseStrDate(opinion.getInsertdate(), "yyyy-MM-dd"));
//        for_type.setText(opinion.getLevel());
        for_title.setText(" "+opinion.getTitle());
        for_body.setText(Html.fromHtml(opinion.getDesc()));


        return convertView;
    }
}






























