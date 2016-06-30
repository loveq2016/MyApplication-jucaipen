package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
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
public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyHodler> implements View.OnClickListener {
    private Context context;
    private List<TeacherDate> list;
    private XRecyclerViewOnItemLlick xRecyclerViewOnItemLlick;

    public TeacherAdapter(FragmentActivity activity, List<TeacherDate> list) {
        this.context = activity;
        this.list = list;
    }

    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ui_ideaitem, null);
        MyHodler hodler = new MyHodler(view);
        view.setOnClickListener(this);
        return hodler;
    }

    @Override
    public void onBindViewHolder(MyHodler holder, int position) {

        TeacherDate teacherDate = list.get(position);
        holder.idea_title.setText(teacherDate.getTitle());
        holder.ra_context.setText(Html.fromHtml(teacherDate.getDesc()));
        String time = TimeUtils.parseStrDate(teacherDate.getInsertDate(), "yyyy-MM-dd HH:ss");
        holder.ra_time.setText(time + " | 阅读数：" + teacherDate.getReadNum() + " ");
        holder.itemView.setTag(position);

    }

    public void setxRecyclerViewOnItemLlick(XRecyclerViewOnItemLlick xRecyclerViewOnItemLlick) {
        this.xRecyclerViewOnItemLlick = xRecyclerViewOnItemLlick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (xRecyclerViewOnItemLlick!=null){
            xRecyclerViewOnItemLlick.onClick(v, (Integer) v.getTag());
        }
    }


    class MyHodler extends RecyclerView.ViewHolder {
        TextView idea_title;
        TextView ra_context;
        TextView ra_time;

        public MyHodler(View itemView) {
            super(itemView);
            this.idea_title = (TextView) itemView.findViewById(R.id.idea_title);
            this.ra_context = (TextView) itemView.findViewById(R.id.ra_context);
            this.ra_time = (TextView) itemView.findViewById(R.id.ra_time);
        }
    }
    public interface  XRecyclerViewOnItemLlick{
        void onClick(View v, int position);
    }
}
