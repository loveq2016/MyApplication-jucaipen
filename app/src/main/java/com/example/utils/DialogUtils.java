package com.example.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.androidnetwork.R;


/**
 * Created by Administrator on 2016/6/1.
 */
public class DialogUtils {
    public static Dialog Loading(Dialog dialog, int width) {
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        return dialog;
    }


    public static Dialog ShareDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context).inflate(R.layout.sharewindow, null);
        dialog.setContentView(view);

        Window dialogWindow = dialog.getWindow();
        WindowManager m = dialogWindow.getWindowManager();
        Display d = m.getDefaultDisplay();
        int width = d.getWidth();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        lp.width = width;
        return dialog;
    }

}
