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
import com.example.model.QuestionBean;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/31.
 * <p/>
 * 问答详情适配器
 */
public class DetailsAdapter extends BaseAdapter {
    private List<QuestionBean> list;
    private Context context;

    public DetailsAdapter(Context context, List<QuestionBean> list) {
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
        MyHolder holder = null;
        if (convertView == null) {
            holder = new MyHolder();
            //回答
            convertView = LayoutInflater.from(context).inflate(R.layout.detailaitem, null);
            holder.deta_head = (ImageView) convertView.findViewById(R.id.deta_head);
            holder.deta_name = (TextView) convertView.findViewById(R.id.deta_name);
            holder.answer_time = (TextView) convertView.findViewById(R.id.answer_time);
            holder.answer_problem = (TextView) convertView.findViewById(R.id.answer_problem);

            QuestionBean questionBean = list.get(position);

            Glide.with(context).load(questionBean.getTeacherFace())
                    .placeholder(R.drawable.rentou)
                    .bitmapTransform(new CropCircleTransformation(context))
                    .into(holder.deta_head);
            holder.deta_name.setText(questionBean.getTeacherNickName());
            holder.answer_time.setText(questionBean.getAnswerDate());
            holder.answer_problem .setText(questionBean.getAnswerBody());

           /* else {
                //提问
                convertView = LayoutInflater.from(context).inflate(R.layout.ui_answer, null);
            }*/

            convertView.setTag(holder);

        } else {
            holder = (MyHolder) convertView.getTag();
        }


        return convertView;
    }

    class MyHolder {
        ImageView deta_head;
        TextView deta_name;
        TextView answer_time;
        TextView answer_problem;

    }
}
