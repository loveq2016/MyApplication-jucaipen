package com.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidnetwork.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.example.model.ChargeOrder;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 * <p/>
 * 全部订单适配器
 */
public class AllAdapter extends XRecyclerView.Adapter<AllAdapter.MyHolder>{


    private  List<ChargeOrder> list;

    public AllAdapter(List<ChargeOrder> list) {
        this.list=list;
    }

    public void setList(List<ChargeOrder> list) {
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.allitem,null);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        TextView tv_oId= (TextView) holder.itemView.findViewById(R.id.tv_oId);
       TextView tv_oTime= (TextView) holder.itemView.findViewById(R.id.tv_oTime);
        TextView tv_serve= (TextView) holder.itemView.findViewById(R.id.tv_serve);
        TextView tv_oMoney= (TextView) holder.itemView.findViewById(R.id.tv_oMoney);
        ImageView iv_oState= (ImageView) holder.itemView.findViewById(R.id.iv_oState);
        ChargeOrder order = list.get(position);
        tv_oId.setText(order.getOrderCode());

        tv_oTime.setText(TimeUtils.parseStrDate(order.getInsertDate(),"yyyy-MM-dd"));
        tv_serve.setText("充值金额："+order.getChargeMoney());
        //支付方式 1网上银行（通联） 2支付宝 3微信支付4：余额支付 5网上银行(汇付宝)
         int type=order.getPayType();
        if(type==1){
            tv_oMoney.setText("付款方式：网上银行（通联）");
        }else if(type==2){
            tv_oMoney.setText("付款方式：支付宝");
        }else if(type==3){
            tv_oMoney.setText("付款方式：微信支付");
        }else if(type==4){
            tv_oMoney.setText("付款方式：余额支付");
        }else if(type==5){
            tv_oMoney.setText("付款方式：网上银行(汇付宝)");
        }

        //订单状态 1未支付  2已支付 3支付失败
       int state= order.getOrderState();
        if(state==1){
            //iv_oState
        }else if(state==2){

        }else if(state==3){

        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyHolder extends  XRecyclerView.ViewHolder{
        View itemView;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
        }
    }
}
