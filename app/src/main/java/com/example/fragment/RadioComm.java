package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.ChatAdapter;
import com.example.androidnetwork.R;
import com.example.model.ChatMsg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 * 演播室 评论
 */
public class RadioComm extends Fragment implements View.OnClickListener {
    private View view;
    private ChatAdapter adapter;
    private ListView lv_chat;
    private List<ChatMsg> list=new ArrayList<>();
    private EditText chat_edt;
    private Button send_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.chatroom, container, false);

        init();
        return view;
    }

    private void init() {
        chat_edt = (EditText) view.findViewById(R.id.chat_edt);
        send_btn = (Button) view.findViewById(R.id.send_btn);
        send_btn.setOnClickListener(this);


        lv_chat = (ListView) view.findViewById(R.id.lv_chat);
        adapter = new ChatAdapter(getActivity(), list);
        lv_chat.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                String msg = chat_edt.getText().toString().trim();
                if (msg != null && msg.length() > 0) {
                    //上线 发送消息

                    chat_edt.setText("");
                    // list.add(msg);
                    adapter.notifyDataSetChanged();
                    lv_chat.setSelection(adapter.getCount() - 1);
                } else {
                    Toast.makeText(getActivity(), "输入不能为空", Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
