package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.example.model.Know;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
public class KnowAdapter extends XRecyclerView.Adapter<KnowAdapter.MyHolder> {

   private  onItemClickListener onItemClickListener;
    private List<Know> list;
    public KnowAdapter(List<Know> list) {
        this.list=list;

    }


    public void setOnItemClickListener(KnowAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.knowitem,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        Know know=list.get(position);


        holder.itemView.setTag(position+"");
        TextView know_tv= (TextView) holder.itemView.findViewById(R.id.know_tv);
        know_tv.setText(know.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(v, (String) v.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends XRecyclerView.ViewHolder {

        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }
    }



    public  interface  onItemClickListener{

        void onClick(View view,String position);
    }
}
