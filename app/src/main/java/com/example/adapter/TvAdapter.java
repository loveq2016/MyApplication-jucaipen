package com.example.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidnetwork.R;
import com.example.model.TextVideo;
import com.example.utils.TimeUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 * <p/>
 * 文字直播适配器
 */
public class TvAdapter extends BaseAdapter {
    private Context context;
    private View view;
    private List<TextVideo> list;

    private Dialog dialog;

    public TvAdapter(FragmentActivity activity, List<TextVideo> list) {
        this.context = activity;
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
        MyHolder holder = null;
        if (convertView == null) {
            holder = new MyHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.txitem, null);

            holder.tv_live_body = (WebView) convertView.findViewById(R.id.tv_live_body);
            holder.tx_lock = (ImageView) convertView.findViewById(R.id.tx_lock);
            holder.tx_time = (TextView) convertView.findViewById(R.id.tx_time);
            convertView.setTag(holder);
        } else {
            holder = (MyHolder) convertView.getTag();
        }
        // ImageView iv_deblocking= (ImageView) convertView.findViewById(R.id.iv_deblocking);
        LinearLayout liner_tx = (LinearLayout) convertView.findViewById(R.id.liner_tx);
        final TextVideo video = list.get(position);

        String time = TimeUtils.getHH_mm(video.getInsertDate());
        holder.tx_time.setText(time);
        holder.tv_live_body.getSettings().setUseWideViewPort(true);
        holder.tv_live_body.getSettings().setLoadWithOverviewMode(true);
        holder.tv_live_body.getSettings().setMinimumFontSize(42);
        holder.tv_live_body.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        holder.tv_live_body.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        if(Build.VERSION.SDK_INT>=11){
            holder.tv_live_body.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        }else{
            holder.tv_live_body.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        }


        if(Build.VERSION.SDK_INT>=19){
            holder.tv_live_body.getSettings().setLoadsImagesAutomatically(true);
        }else {
            holder.tv_live_body.getSettings().setLoadsImagesAutomatically(false);
        }

        holder.tv_live_body.getSettings().setDomStorageEnabled(true);
        holder.tv_live_body.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!view.getSettings().getLoadsImagesAutomatically()){
                    view.getSettings().setLoadsImagesAutomatically(true);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.i("111", "onReceivedError: ");
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });


        holder.tv_live_body.setBackgroundColor(Color.parseColor("#f7f7f7"));

        int lock = video.getIsLock();
        if(lock!=2){
            holder.tv_live_body.loadDataWithBaseURL(null,video.getBodys(),"text/html","UTF-8",null);
            holder.tx_lock.setVisibility(View.GONE);
        }else {
            holder.tv_live_body.loadDataWithBaseURL(null,"\"推荐股票...私密内容，点击解锁\"","text/html","UTF-8",null);
        }


        view = LayoutInflater.from(context).inflate(R.layout.tvpopwindow, null);
        dialog = new Dialog(context);
        dialog.setContentView(view);

//        iv_deblocking.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (dialog!=null){
//                    dialog.show();
//                }
//            }
//        });

        ImageView iv_sure = (ImageView) view.findViewById(R.id.iv_sure);
        ImageView iv_dismiss = (ImageView) view.findViewById(R.id.iv_dismiss);
        iv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "解锁了", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        iv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        return convertView;
    }

    class MyHolder {
        WebView tv_live_body;
        ImageView tx_lock;
        TextView tx_time;
    }
}
