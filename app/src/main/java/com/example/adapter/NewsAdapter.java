package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.androidnetwork.R;
import com.example.model.Rebate;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2016/5/12.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder> {
    private Context context;
    private List<Rebate> list;

    public NewsAdapter(Context context, List<Rebate> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.ui_bd_item, null);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ImageView rk_photo = (ImageView) holder.view.findViewById(R.id.rk_photo);
        ImageView rk_touxiang = (ImageView) holder.view.findViewById(R.id.rk_touxiang);
        TextView rk_name = (TextView) holder.view.findViewById(R.id.rk_name);
        TextView tv_rebate = (TextView) holder.view.findViewById(R.id.tv_rebate);
        Rebate rebate = list.get(position);



        Glide.with(context).load(rebate.getUserFace())
                .placeholder(R.drawable.rentou)
                .bitmapTransform(new CropCircleTransformation(context))
                .into( rk_touxiang);
        rk_name.setText(rebate.getNinkName());
       tv_rebate.setText("贡献值："+rebate.getContributeJucaiBills() + "");
//        if((int)holder.itemView.getTag()==1){
//            rk_photo.setImageResource(R.drawable.one);
//        }else {
//            rk_photo.setVisibility(View.GONE);
//        }

        if(list.indexOf(rebate)==0){
            rk_photo.setImageResource(R.drawable.one);
        }


//        if (position == 0) {
//            rk_photo.setImageResource(R.drawable.one);
//        } else if (position == 1) {
//            rk_photo.setImageResource(R.drawable.two);
//        } else if (position== 2) {
//             rk_photo.setImageResource(R.drawable.three);
//        } else {
//               rk_photo.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }




    //    public NewsAdapter(FragmentActivity activity, List<Rebate> list) {
//        this.context = activity;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Myholder myholder;
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(R.layout.ui_bd_item, null);
//            myholder=new Myholder();
//            myholder.rk_photo = (ImageView) convertView.findViewById(R.id.rk_photo);
//            myholder.rk_touxiang = (ImageView) convertView.findViewById(R.id.rk_touxiang);
//            myholder.rk_name = (TextView) convertView.findViewById(R.id.rk_name);
//            myholder.tv_rebate = (TextView) convertView.findViewById(R.id.tv_rebate);
//            convertView.setTag(myholder);
//
//        }else {
//            myholder= (Myholder) convertView.getTag();
//        }
//        myholder.rk_photo.setTag(position);
//        Rebate rebate = list.get(position);
//        Glide.with(context).load(rebate.getUserFace())
//                .placeholder(R.drawable.rentou)
//                .bitmapTransform(new CropCircleTransformation(context))
//                .into( myholder.rk_touxiang);
//        myholder. rk_name.setText(rebate.getNinkName());
//        myholder.tv_rebate.setText("贡献值："+rebate.getContributeJucaiBills() + "");
//
//
//        if ((int)myholder.rk_photo.getTag() == 0) {
//            myholder. rk_photo.setImageResource(R.drawable.one);
//        } else if ((int)myholder.rk_photo.getTag() == 1) {
//            myholder. rk_photo.setImageResource(R.drawable.two);
//        } else if ((int)myholder.rk_photo.getTag() == 2) {
//            myholder. rk_photo.setImageResource(R.drawable.three);
//        } else {
//            myholder. rk_photo.setVisibility(View.GONE);
//        }
//        return convertView;
//    }
//
    class  MyHolder extends  RecyclerView.ViewHolder{
        View view;

    public MyHolder(View view) {
        super(view);
        this.view=view;
    }
}
}
