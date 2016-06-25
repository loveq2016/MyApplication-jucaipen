package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.KnowledgeAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/6/2.
 * <p/>
 * 收藏知识
 */
public class Collectknowledge extends Fragment {
    private View view;
    private KnowledgeAdapter adapter;
    private ListView lv_knowledge;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.knowledge, container, false);
        init();
        return view;
    }

    private void init() {
        lv_knowledge = (ListView) view.findViewById(R.id.lv_knowledge);
        adapter = new KnowledgeAdapter(getActivity());
        lv_knowledge.setAdapter(adapter);

    }
}
