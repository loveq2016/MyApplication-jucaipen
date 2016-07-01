package com.example.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.ChatAdapter;
import com.example.androidnetwork.R;
import com.example.application.InitImageLoder;
import com.example.model.ChatMsg;
import com.example.utils.JsonUtil;
import com.example.utils.NetUtils;
import com.example.utils.StoreUtils;
import com.google.gson.JsonObject;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private LinearLayout et_lay;
    private static final String chatUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getlivechat";
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;
    private MessageReceiver mMessageReceiver;
    private  int liveId;
    private Map<String, Object> map=new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.chatroom, container, false);
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        isForeground=true;
        //final int opType, int toId, final String msg
        SendMessage(1,-1,null);
    }


    @Override
    public void onPause() {
        super.onPause();
        isForeground=false;
    }



    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        getActivity().registerReceiver(mMessageReceiver, filter);
    }





    private void init() {
        InitImageLoder loader= (InitImageLoder) getActivity().getApplication();
        liveId=loader.getLiveId();
        Toast.makeText(getActivity(), ""+liveId, Toast.LENGTH_SHORT).show();
        registerMessageReceiver();
        chat_edt = (EditText) view.findViewById(R.id.chat_edt);
        send_btn = (Button) view.findViewById(R.id.send_btn);
        send_btn.setOnClickListener(this);
        et_lay= (LinearLayout) view.findViewById(R.id.et_lay);
        lv_chat = (ListView) view.findViewById(R.id.lv_chat);
        lv_chat.setVisibility(View.VISIBLE);
        et_lay.setVisibility(View.VISIBLE);
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



    //上线 聊天
    private void SendMessage(final int opType, int toId, final String msg) {

        //   opType    1   上线       2    聊天      3   下线
        // {"fromId":1,"liveId:1","opType":2,"toId":0,msg:"hello"}

        final JsonObject object = new JsonObject();
        object.addProperty("fromId", StoreUtils.getUserInfo(getActivity()));
        object.addProperty("opType", opType);
        object.addProperty("liveId", liveId);
        object.addProperty("toId", toId);
        object.addProperty("msg", msg);
        map.put("msgObject", object);
        RequestParams params = NetUtils.sendHttpGet(chatUrl, map);
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    if(result.length()>0){
                        if(opType==2){
                            chat_edt.setText("");
                            ChatMsg chatMsg = new ChatMsg();
                            chatMsg.setSendId(StoreUtils.getUserInfo(getActivity()));
                            chatMsg.setSendName(StoreUtils.getUserDate(getActivity()));
                            chatMsg.setMsg(msg);
                            chatMsg.setShenHe(0);
                            //  chatMsg.setSendLeavel(sendLeavel);
                            //   chatMsg.setToName(toName);
                            list.add(chatMsg);
                            adapter.notifyDataSetChanged();
                        }
                    }

                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }


    public class MessageReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                list = JsonUtil.getMsg(getActivity(),messge,list);
            }
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            lv_chat.setSelection(lv_chat.getBottom());
        }
    }
}
