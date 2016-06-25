package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.androidnetwork.R;
import com.example.model.QuestionBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 *
 * 问答详情适配器
 */
public class DetailsAdapter extends BaseAdapter{
    private final List<QuestionBean> questions;
    private Context context;

    public DetailsAdapter(Context context, List<QuestionBean> questions) {
        this.context=context;
        this.questions=questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Object getItem(int position) {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int isAnswer=questions.get(position).getType();
        if(isAnswer==1){
            //回答
            convertView= LayoutInflater.from(context).inflate(R.layout.detailaitem,null);
        }else {
            //提问
            convertView= LayoutInflater.from(context).inflate(R.layout.ui_answer,null);
        }
        return convertView;
    }
}
