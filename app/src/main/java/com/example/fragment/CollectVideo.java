package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.LiveAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/6/2.
 * 收藏视频
 */
public class CollectVideo extends Fragment {
    private View view;
    private ListView lv_video;
    private LiveAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.collectvideo,container,false);
        init();

        return view;
    }

    private void init() {
        lv_video= (ListView) view.findViewById(R.id.lv_video);
        adapter=new LiveAdapter(getActivity());
        lv_video.setAdapter(adapter);


    }
}
