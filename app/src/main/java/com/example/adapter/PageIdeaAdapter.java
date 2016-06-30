package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.Opinion;
import com.example.utils.StringUtil;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 * <p/>
 * 观点适配器
 */
public class PageIdeaAdapter extends RecyclerView.Adapter<PageIdeaAdapter.MyHolder> implements View.OnClickListener {
    private Context context;
    private List<Opinion> opinionList;
    private RecyclerViewAdapterOnClick adapterOnClick;

    public PageIdeaAdapter(Context context, List<Opinion> opinionList) {
        this.context = context;
        this.opinionList = opinionList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pageidea, null);
        MyHolder holder = new MyHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    public void setAdapterOnClick(RecyclerViewAdapterOnClick adapterOnClick) {
        this.adapterOnClick = adapterOnClick;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Opinion opinion = opinionList.get(position);
        holder.for_time.setText(TimeUtils.parseStrDate(opinion.getInsertdate(), "yyyy-MM-dd"));
        holder.for_title.setText(" " + opinion.getTitle());
        holder.for_body.setText( StringUtil.clearHTMLCode(opinion.getDesc()));


        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return opinionList.size();
    }

    @Override
    public void onClick(View v) {
        if (adapterOnClick!=null){
            adapterOnClick.onClick(v, (Integer) v.getTag());
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView for_time;
        TextView for_title;
        TextView for_body;

        public MyHolder(View itemView) {
            super(itemView);
            this.for_time = (TextView) itemView.findViewById(R.id.for_time);
            this.for_title = (TextView) itemView.findViewById(R.id.for_title);
            this.for_body = (TextView) itemView.findViewById(R.id.for_body);
        }
    }

    public interface RecyclerViewAdapterOnClick {
        void onClick(View v, int position);
    }
}

