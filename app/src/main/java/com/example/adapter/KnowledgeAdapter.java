package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.Collect;

import java.util.List;


/**
 * Created by Administrator on 2016/6/2.
 * 收藏知识适配器
 */
public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private KnowledgeAdapter.OnclickitemLinnser onclickitemLinnser;
    private List<Collect> list;

    public KnowledgeAdapter(FragmentActivity activity, List<Collect> list) {
        this.context = activity;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.knowledgeitem, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    public void setOnclickitemLinnser(OnclickitemLinnser onclickitemLinnser) {
        this.onclickitemLinnser = onclickitemLinnser;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Collect collect=list.get(position);
        holder.knowledge_context.setText(collect.getTitle());

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (onclickitemLinnser != null) {
            onclickitemLinnser.onClick(v, (Integer) v.getTag());
        }

    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView knowledge_context;

        public MyHolder(View itemView) {
            super(itemView);
            this.knowledge_context = (TextView) itemView.findViewById(R.id.knowledge_context);
        }
    }

    public interface OnclickitemLinnser {
        void onClick(View v, int position);
    }
}
