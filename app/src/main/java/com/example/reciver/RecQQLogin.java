package com.example.reciver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author Administrator
 *
 *
 *  ����QQ��¼���
 */
public class RecQQLogin extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		if(arg1.getAction().equals("com.receiverQQ")){

		}
		if(arg1.getAction().equals("com.receiverWeiXin")){

		}
		

	}

}
