package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 *
 * 选集
 */
public class TodayAdapter extends XRecyclerView.Adapter<TodayAdapter.MyHolder>{


    private List<String> todays;

    public TodayAdapter(List<String> todays) {
        this.todays=todays;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.tvitems,null);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TextView tv= (TextView) holder.itemView.findViewById(R.id.tv_context);
        tv.setText("name");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class  MyHolder extends  XRecyclerView.ViewHolder{
        private View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }

}
