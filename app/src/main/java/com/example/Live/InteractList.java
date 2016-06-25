package com.example.Live;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.ChatAdapter;
import com.example.androidnetwork.R;
import com.example.reciver.MyReceiver;
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

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/5/12.
 * 互动列表
 */
public class InteractList extends Fragment {
    private View view;
    private ListView chatListview;
    private ChatAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private Map<String, Object> map = new HashMap<>();
    private int liveId;
    private static final String chatUrl = "http://192.168.1.134:8080/Jucaipen/jucaipen/getlivechat";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.interactlist, container, false);
        liveId = getArguments().getInt("liveId");
        init();
        SendMessage();
        return view;
    }


   /* @Override
    public void onResume() {
        super.onResume();
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter recIntent=new IntentFilter();
        recIntent.addAction(JPushInterface.ACTION_MESSAGE_RECEIVED);

        MyReceiver receiver=new MyReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("111", "onReceive: "+"ree");
                if(JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())){
                    String receiverMsg=intent.getExtras().getString(JPushInterface.EXTRA_MESSAGE);
                    Toast.makeText(getActivity(), "接受的"+receiverMsg, Toast.LENGTH_SHORT).show();
                }
            }
        };

        broadcastManager.registerReceiver(receiver,recIntent);

    }*/


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        IntentFilter recIntent=new IntentFilter();
//        recIntent.addAction(JPushInterface.ACTION_MESSAGE_RECEIVED);
//
//        MyReceiver receiver=new MyReceiver(){
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.i("111", "onReceive: "+"ree");
//                if(JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())){
//                    String receiverMsg=intent.getExtras().getString(JPushInterface.EXTRA_MESSAGE);
//                    //[{"sendId":48,"sendName":"学习找牛股","msg":"份饭","shenHe":0,"sendLeavel":0,"toName":null}]
//
//                    Toast.makeText(getActivity(), "接受的"+receiverMsg, Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//
//        getActivity().registerReceiver(receiver,recIntent);
//
//
//    }

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
            IntentFilter recIntent=new IntentFilter();
            recIntent.addAction(JPushInterface.ACTION_MESSAGE_RECEIVED);

            MyReceiver receiver=new MyReceiver(){
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.i("111", "onReceive: "+"ree");
                    if(JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())){
                        String receiverMsg=intent.getExtras().getString(JPushInterface.EXTRA_MESSAGE);
                        //[{"sendId":48,"sendName":"学习找牛股","msg":"份饭","shenHe":0,"sendLeavel":0,"toName":null}]

                        JsonUtil.getaboutvideo()

                        Toast.makeText(getActivity(), "接受的"+receiverMsg, Toast.LENGTH_SHORT).show();
                    }
                }
            };

            broadcastManager.registerReceiver(receiver,recIntent);
        }

    }*/

    //上线 聊天
    private void SendMessage() {

        //   opType    1   上线       2    聊天      3   下线
        // {"fromId":1,"liveId:1","opType":2,"toId":0,msg:"hello"}
        //map.put("opType",msgObject);

        JsonObject object = new JsonObject();
        object.addProperty("fromId", StoreUtils.getUserInfo(getActivity()));
        object.addProperty("opType", 1);
        object.addProperty("liveId", liveId);
        object.addProperty("toId", 0);
        object.addProperty("msg", "");
        map.put("msgObject", object);
        RequestParams params = NetUtils.sendHttpGet(chatUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null) {


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
        adapter = new ChatAdapter(getActivity(), list);
        chatListview.setAdapter(adapter);
    }
}
