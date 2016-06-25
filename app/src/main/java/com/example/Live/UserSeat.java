package com.example.Live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.UserTopAdapter;
import com.example.adapter.UserBottmAdapter;
import com.example.androidnetwork.R;
import com.example.view.TestListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 * 用户席位
 */
public class UserSeat extends Fragment {
    private TestListView user_lv1;
    private TestListView user_lv2;

    private View view;
    private UserTopAdapter topAdapter;
    private UserBottmAdapter bottmAdapter;
    private List<String> list = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.ui_xw_fragment, container, false);
        init();
        return view;
    }

    private void init() {

        list.clear();
        images.clear();
        images.add(R.drawable.gushidaren);
        list.add("股市神人");
        images.add(R.drawable.gushiniuren);
        list.add("股市牛人");
        images.add(R.drawable.gushidashen);
        list.add("股市达人");
        images.add(R.drawable.caogengumin);
        list.add("草根股民");
        user_lv1 = (TestListView) view.findViewById(R.id.user_lv1);
        user_lv2 = (TestListView) view.findViewById(R.id.user_lv2);
        topAdapter = new UserTopAdapter(getActivity(), list, images);

        bottmAdapter = new UserBottmAdapter(getActivity());
        user_lv1.setAdapter(topAdapter);
        user_lv2.setAdapter(bottmAdapter);

    }
}



















