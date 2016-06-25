package com.example.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/5/21.
 *  礼品适配器
 */
public class PresentAdapter extends BaseAdapter{
    private Context context;
    private PopupWindow window;
    private GridView v;

    public PresentAdapter(FragmentActivity activity, GridView my_grd) {
        this.context=activity;
        this.v=my_grd;
    }

    @Override
    public int getCount() {
        return 30;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.presentitem,null);
        }

        Button btn_present= (Button) convertView.findViewById(R.id.btn_present);
        final View view=LayoutInflater.from(context).inflate(R.layout.presentwindow,null);
        btn_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window==null){
                    window=new PopupWindow(context);
                    window.setTouchable(true);
                    window.setOutsideTouchable(true);
                    window.setContentView(view);
                }
                window.showAtLocation(v, Gravity.CENTER, 0, 0);

            }
        });
        return convertView;
    }
}
