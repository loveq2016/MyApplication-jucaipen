package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class PageAnswerAdapter extends RecyclerView.Adapter<PageAnswerAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<Interlocution> list;
    private PageAnswerAdapterOnItemClick answerAdapterOnItemClick;

    public PageAnswerAdapter(Context context, List<Interlocution> list) {
        this.context = context;
        this.list = list;

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pageanswer, null);
        MyHolder holder = new MyHolder(view);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Interlocution interlocution = list.get(position);
        holder.itemView.setOnClickListener(this);

        TextView page_time = (TextView) holder.itemView.findViewById(R.id.page_time);
        TextView page_answer = (TextView) holder.itemView.findViewById(R.id.page_answer);
        TextView answer_body = (TextView) holder.itemView.findViewById(R.id.answer_body);
        TextView question_body = (TextView) holder.itemView.findViewById(R.id.question_body);

        page_time.setText(TimeUtils.parseStrDate(interlocution.getInsertDate(), "yyyy-MM-dd"));
        page_answer.setText(interlocution.getTrueName() + " : ");
        answer_body.setText(interlocution.getAskBodys());
        question_body.setText(interlocution.getAnswerBody());
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (answerAdapterOnItemClick != null) {
            answerAdapterOnItemClick.onClick(v, (Integer) v.getTag());
        }

    }

    public void setAnswerAdapterOnItemClick(PageAnswerAdapterOnItemClick answerAdapterOnItemClick) {
        this.answerAdapterOnItemClick = answerAdapterOnItemClick;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }
    }

    public interface PageAnswerAdapterOnItemClick {

        void onClick(View v, int position);
    }
}




























