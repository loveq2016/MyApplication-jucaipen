package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.example.model.RebateIntegeralDetail;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 * <p/>
 * 我的返利适配器
 */
public class RebateAdapter extends BaseAdapter {
    private List<RebateIntegeralDetail> list;
    private Context context;

    public RebateAdapter(Context context, List<RebateIntegeralDetail> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.myrebate, null);
        }
        TextView user_type = (TextView) convertView.findViewById(R.id.user_type);
        TextView comment_time = (TextView) convertView.findViewById(R.id.comment_time);
        TextView comm_title = (TextView) convertView.findViewById(R.id.comm_title);
        TextView comm_jifen= (TextView) convertView.findViewById(R.id.comm_jifen);

        RebateIntegeralDetail detail=list.get(position);
        user_type.setText(detail.getType());
        comment_time.setText(detail.getInsertDate());
        comm_title.setText(detail.getRemark());
        comm_jifen.setText("返利积分："+detail.getIntegralNum()+"");



        return convertView;
    }
}
