package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.MyRecord.Mymoney;
import com.example.androidnetwork.R;
import com.example.model.Moneny;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3.
 *
 * 我的聚财币适配器
 */
public class MoneyAdapter extends BaseAdapter{
    private Context context;
    private List<Moneny> list;

    public MoneyAdapter(Mymoney mymoney, List<Moneny> list) {
        this.context=mymoney;
        this.list=list;
    }

    public void setList(List<Moneny> list) {
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

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.jifenitem,null);
        }
        TextView money_time= (TextView) convertView.findViewById(R.id.money_time);
        TextView money_body= (TextView) convertView.findViewById(R.id.money_body);
        TextView money_money= (TextView) convertView.findViewById(R.id.money_money);

        Moneny moneny=list.get(position);
        money_time.setText(moneny.getInsertDate());
        money_body.setText(moneny.getRemark());
        money_money.setText(moneny.getDetailMoney()+"");


        return convertView;
    }
}
