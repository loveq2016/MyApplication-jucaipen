package com.example.AlreadyPay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.AnswerAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/19.
 * 已购买的 问答
 */
public class BuyAnswer extends Fragment {
    private View view;
    private ListView lv_answer;
    private AnswerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.buyanswer,container,false);

        init();

        return view;
    }

    private void init() {
        lv_answer= (ListView) view.findViewById(R.id.lv_answer);
        adapter=new AnswerAdapter(getActivity());
        lv_answer.setAdapter(adapter);
    }
}
