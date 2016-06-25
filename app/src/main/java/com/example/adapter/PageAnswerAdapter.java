package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.Interlocution;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 * 他的问答
 */
public class PageAnswerAdapter extends BaseAdapter {
    private Context context;
    private List<Interlocution> list;

    public PageAnswerAdapter(Context context, List<Interlocution> list) {
        this.context = context;
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pageanswer, null);
        }


        TextView page_time = (TextView) convertView.findViewById(R.id.page_time);
        TextView page_answer = (TextView) convertView.findViewById(R.id.page_answer);
        TextView answer_body = (TextView) convertView.findViewById(R.id.answer_body);
        TextView question_body = (TextView) convertView.findViewById(R.id.question_body);
        //{"page":1,"totlePage":1,"askId":173,"trueName":null,"insertDate":"2016-05-23 10:02:00.71"
        // ,"askBodys":"1111","headFace":null,"isPay":1,"replyCount":0,"answerBody":null},

        Interlocution interlocution=list.get(position);
        page_time.setText(TimeUtils.parseStrDate(interlocution.getInsertDate(),"yyyy-MM-dd"));
        page_answer.setText(interlocution.getTrueName());
        answer_body.setText(interlocution.getAskBodys());
        question_body.setText(interlocution.getAnswerBody());





        return convertView;
    }
}




























