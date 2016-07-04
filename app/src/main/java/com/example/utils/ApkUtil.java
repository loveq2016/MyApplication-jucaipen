package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;

public class ApkUtil {
	private static PackageManager pm;
	private static PackageInfo info;
	private static ApkUtil au;
	private static List<Activity> activities = new ArrayList<Activity>();
	private static TelephonyManager tm;

	private ApkUtil() {
		
	}

	/**
	 * @return ���ص�ǰʵ�����
	 */
	public static ApkUtil getInstance() {
		if (au == null) {
			au = new ApkUtil();
		}
		return au;
	}

	/**
	 * ��ʼ������
	 */
	public void initParams(Context mContext) {
		pm = mContext.getPackageManager();
		try {
			info = pm.getPackageInfo(mContext.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			tm = (TelephonyManager) mContext
					.getSystemService(Context.TELEPHONY_SERVICE);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param activity
	 *            ��ʼ��activity
	 */
	public static void initActivity(Activity activity) {
		activities.add(activity);
		
	}

	/**
	 * �ر�activity
	 */
	public static void finishActivity() {
		for (Activity a : activities) {
			a.finish();
		}
	}

	/**
	 * @return ��ȡ��ǰapk�汾��
	 */
	public int getCurrentVersionCode() {
		return info.versionCode;
	}

	/**
	 * @return ��ȡ��ǰ�汾���
	 */
	public static String getCurrentVersionName() {
		return info.versionName;
	}

	public static String getDevId() {
		return tm.getDeviceId();
	}

}
