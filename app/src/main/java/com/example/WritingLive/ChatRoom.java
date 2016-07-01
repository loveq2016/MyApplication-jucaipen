package com.example.WritingLive;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ChatAdapter;
import com.example.androidnetwork.R;
import com.example.model.ChatMsg;
import com.example.utils.NetUtils;
import com.example.utils.StoreUtils;
import com.example.utils.StringUntils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/5/27.
 * <p/>
 * 守护悄悄话
 */
public class ChatRoom extends Fragment implements View.OnClickListener {
    private View view;
    private ChatAdapter adapter;
    private ListView lv_chat;
    private List<ChatMsg> list = new ArrayList<>();
    private EditText chat_edt;
    private TextView tv_guinfo;
    private Button send_btn;
    private String guardUrl = "http://" + StringUntils.getHostName() + "/Jucaipen/jucaipen/getisgradian";
    private int teacherId;
    private LinearLayout et_lay;
    private Map<String, Object> map = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.chatroom, container, false);

        init();

        return view;
    }

    private void init() {
        teacherId = getArguments().getInt("teacherId", -1);
        GetGuard();

        chat_edt = (EditText) view.findViewById(R.id.chat_edt);
        send_btn = (Button) view.findViewById(R.id.send_btn);
        send_btn.setOnClickListener(this);
        tv_guinfo = (TextView) view.findViewById(R.id.tv_guinfo);
        et_lay = (LinearLayout) view.findViewById(R.id.et_lay);


        lv_chat = (ListView) view.findViewById(R.id.lv_chat);
        adapter = new ChatAdapter(getActivity(), list);
        lv_chat.setAdapter(adapter);
    }

    private void GetChatMsg() {
        map.clear();
        RequestParams params = NetUtils.sendHttpGet("", map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                String msg = chat_edt.getText().toString().trim();
                if (msg != null && msg.length() > 0) {
                    adapter.notifyDataSetChanged();
                    chat_edt.setText("");
                    lv_chat.setSelection(adapter.getCount() - 1);
                } else {
                    Toast.makeText(getActivity(), "输入不能为空", Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }


    private void GetGuard() {
        map.clear();
        map.put("teacherId", teacherId);
        map.put("userId", StoreUtils.getUserInfo(getActivity()));
        RequestParams params = NetUtils.sendHttpGet(guardUrl, map);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public boolean onCache(String result) {
                return false;
            }

            @Override
            public void onSuccess(String result) {
                if (result != null && result.length() > 0) {

                    try {
                        JSONObject object = new JSONObject(result);
                        int ret_code = object.getInt("ret_code");
                        String err_msg = object.getString("err_msg");
                        if (ret_code == 0) {

                            tv_guinfo.setVisibility(View.GONE);
                            lv_chat.setVisibility(View.VISIBLE);
                            et_lay.setVisibility(View.VISIBLE);
                            //获取悄悄话
                            GetChatMsg();
                        } else {
                            tv_guinfo.setVisibility(View.VISIBLE);
                            lv_chat.setVisibility(View.GONE);
                            et_lay.setVisibility(View.GONE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
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


}
