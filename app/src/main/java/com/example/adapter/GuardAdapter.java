package com.example.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Attention;
import com.example.utils.TimeUtils;
import com.example.Activity.MyGuard;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/6/2.
 * 守护适配器
 */
public class GuardAdapter extends BaseAdapter {
    private Context context;
    private List<Attention> list;

    public GuardAdapter(MyGuard myGuard, List<Attention> list) {
        this.context = myGuard;
        this.list = list;
    }

    public void setList(List<Attention> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.guarditem, null);
        }
        ImageView guard_head = (ImageView) convertView.findViewById(R.id.guard_head);
        TextView guard_rank = (TextView) convertView.findViewById(R.id.guard_rank);
        TextView tv_guardtime = (TextView) convertView.findViewById(R.id.tv_guardtime);
        TextView guard_body = (TextView) convertView.findViewById(R.id.guard_body);
        Attention attention = list.get(position);
        Glide.with(context).load(attention.getTeacherImage())
                .bitmapTransform(new CropCircleTransformation(context))
                .placeholder(R.drawable.rentou)
                .into(guard_head);

        //[{"page":1,"totlePage":1,"id":25,"startDate":"2016-06-16 17:59:07.07",
        // "endDate":"2016-06-17 17:59:07.07","teacherImage":"http://img.j47.jpg",
        // "nickName":"远清看市","leavel":"CCTV产品讲师","isV":1,
        // "introduce":"<spne-h\">现任CCTV在线教学课堂《证券实战教学》栏目公开课讲师。</span>"}]
        guard_rank.setText(attention.getNickName() + " | " + attention.getLeavel());
        String startTime = TimeUtils.parseStrDate(attention.getEndDate(), "yyyy-MM-dd");
        tv_guardtime.setText(startTime);

        guard_body.setText(Html.fromHtml(attention.getIntroduce()));


        return convertView;
    }
}
