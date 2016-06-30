package com.example.Live;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Activity.Login;
import com.example.adapter.ChatAdapter;
import com.example.androidnetwork.R;
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
 * Created by Administrator on 2016/5/12.
 * 互动列表
 */
public class InteractList extends Fragment implements View.OnClickListener {
    private View view;
    private ListView chatListview;
    private ChatAdapter adapter;
    private Map<String, Object> map = new HashMap<>();
    private int liveId;
    private static final String chatUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getlivechat";
    private MessageReceiver mMessageReceiver;
    public static boolean isForeground = false;
    private List<ChatMsg> chatList=new ArrayList<>();
    private ImageView send;
    private EditText et_msg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.interactlist, container, false);
        liveId = getArguments().getInt("liveId");
        init();
        registerMessageReceiver();
        SendMessage(1,0,null);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        isForeground = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isForeground=false;
        SendMessage(3,-1,null);
        getActivity().unregisterReceiver(mMessageReceiver);
    }


    //上线 聊天
    private void SendMessage(final int opType, int toId, final String msg) {

        //   opType    1   上线       2    聊天      3   下线
        // {"fromId":1,"liveId:1","opType":2,"toId":0,msg:"hello"}
        //map.put("opType",msgObject);

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
                            et_msg.setText("");
                            ChatMsg chatMsg = new ChatMsg();
                            chatMsg.setSendId(StoreUtils.getUserInfo(getActivity()));
                            chatMsg.setSendName(StoreUtils.getUserDate(getActivity()));
                            chatMsg.setMsg(msg);
                            chatMsg.setShenHe(0);
                          //  chatMsg.setSendLeavel(sendLeavel);
                         //   chatMsg.setToName(toName);
                            chatList.add(chatMsg);
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

    private void init() {
        chatListview = (ListView) view.findViewById(R.id.chatListview);
        adapter = new ChatAdapter(getActivity(), chatList);
        chatListview.setAdapter(adapter);
        send= (ImageView) view.findViewById(R.id.send);
        send.setOnClickListener(this);
        et_msg= (EditText) view.findViewById(R.id.et_msg);
    }



    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        getActivity().registerReceiver(mMessageReceiver, filter);
    }




    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.send:
                //发送消息
                if(StoreUtils.getUserInfo(getActivity())<0){
                   Intent login=new Intent(getActivity(), Login.class);
                    getActivity().startActivity(login);
                    return;
                }
                String msg=et_msg.getText().toString().trim();
                if(msg.length()>0){
                    SendMessage(2,0,msg);
                }else {
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }


    public class MessageReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
//                String extras = intent.getStringExtra(KEY_EXTRAS);
//                StringBuilder showMsg = new StringBuilder();
//                showMsg.append(messge + "\n");
//                if (!ExampleUtil.isEmpty(extras)) {
//                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
//                }
                chatList = JsonUtil.getMsg(getActivity(),messge,chatList);
            }
            adapter.setList(chatList);
            adapter.notifyDataSetChanged();
            chatListview.setSelection(chatListview.getBottom());
        }
    }




}
