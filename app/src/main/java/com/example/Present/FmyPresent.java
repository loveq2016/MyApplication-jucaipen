package com.example.Present;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.adapter.PresentAdapter;
import com.example.androidnetwork.R;

/**
 * Created by Administrator on 2016/5/20.
 * <p/>
 * 我的礼品
 */
public class FmyPresent extends Fragment implements View.OnClickListener {
    private View view;
    private GridView my_grd;
    private PresentAdapter adapter;
    private Button btn_present;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fmypresent, container, false);

        init();

        return view;
    }

    private void init() {
        my_grd= (GridView) view.findViewById(R.id.my_grd);
        btn_present= (Button) view.findViewById(R.id.btn_present);
        btn_present.setOnClickListener(this);
        adapter=new PresentAdapter(getActivity(),my_grd);
        my_grd.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        switch (v.getId()){
            case R.id.btn_present:
                intent.setClass(getActivity(),GiftShop.class);
                startActivity(intent);
                break;
        }
    }
}
