package com.example.utils;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.model.User;

public class StoreUtils {
	private static SharedPreferences userInfo;
	private static SharedPreferences loginInfo;
	private static SharedPreferences firstInfo;
	private static SharedPreferences versionUpdate;
	private static SharedPreferences userData;
	private static SharedPreferences userInfos;
	private static SharedPreferences title;

	/**
	 * @param context
	 * @param id
	 *            �洢�û�id
	 */
	public static void saveData(Context context, int id) {
		userInfo = context.getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		Editor et = userInfo.edit();
		et.putInt("userId", id);
		et.commit();
	}

	// �����û���¼����Ϣ
	public static void saveUserDate(Context context, String login_name) {
		userData = context.getSharedPreferences("userData",
				Context.MODE_PRIVATE);
		Editor editor = userData.edit();
		editor.putString("login_name", login_name);
		/*editor.putString("BuyProductId", BuyProductIds);
		editor.putString("IsManager", IsManagers);
		editor.putString("ReginDate", ReginDates);
		editor.putString("IsServer", isServers);
		editor.putInt("userType", type);*/
		editor.commit();
	}

	// ����û���¼����Ϣ
	public static void cleanUserDate(Context context) {
		userData = context.getSharedPreferences("userData",
				Context.MODE_PRIVATE);
		Editor editor = userData.edit();
		editor.clear();
		editor.commit();
	}

	// ��ȡ�û���¼����Ϣ
	public static String getUserDate(Context context) {
		Map<String, String> map = new HashMap<String, String>();
		userData = context.getSharedPreferences("userData",
				Context.MODE_PRIVATE);
	//	String BuyProductId = userData.getString("BuyProductId", null);
		//String IsManager = userData.getString("IsManager", null);
	//	int userType=userData.getInt("userType", -1);
	//	String ReginDate = userData.getString("ReginDate", null);
	//	String IsServer = userData.getString("IsServer", null);
		String login_name = userData.getString("login_name", null);
	/*	map.put("BuyProductId", BuyProductId);
		map.put("userType", userType+"");
		map.put("IsManager", IsManager);
		map.put("ReginDate", ReginDate);
		map.put("login_name", login_name);
		map.put("IsServer", IsServer);*/
		return login_name;

	}

	/**
	 * @param context
	 * @return ��ȡ�û�id
	 */
	public static int getUserInfo(Context context) {
		userInfo = context.getSharedPreferences("userInfo",
				Context.MODE_PRIVATE);
		int uId = userInfo.getInt("userId", 0);
		return uId;
	}

	/**
	 * @param context
	 * @param id
	 *            �ж��û��Ƿ��һ������app
	 */
	public static void saveFirstInstall(Context context, boolean isFirst) {
		firstInfo = context.getSharedPreferences("first", Context.MODE_PRIVATE);
		Editor et = firstInfo.edit();
		et.putBoolean("isFirst", isFirst);
		et.commit();
	}

	/**
	 * @param context
	 * @return �ж��û��Ƿ��һ������app
	 */
	public static boolean getFirstInstall(Context context) {
		firstInfo = context.getSharedPreferences("first", Context.MODE_PRIVATE);
		return firstInfo.getBoolean("isFirst", true);
	}

	/**
	 * @param context
	 * @param isLogin
	 *            �û��Ƿ��½
	 */
	public static void saveLoginState(Context context, boolean isLogin) {
		loginInfo = context.getSharedPreferences("loginInfo",
				Context.MODE_PRIVATE);
		Editor editor = loginInfo.edit();
		editor.putBoolean("isLogin", isLogin);
		editor.commit();
	}

	/**
	 * @param context
	 * @return �û��Ƿ��½
	 */
	public static boolean getLoginInfo(Context context) {
		loginInfo = context.getSharedPreferences("loginInfo",
				Context.MODE_PRIVATE);
		return loginInfo.getBoolean("isLogin", false);
	}

	/**
	 * @param context
	 * @param isUpdate
	 *            �Ƿ����APP (0 �Ժ���˵ 1 ȡ����
	 */
	public static void saveUpdateInfo(Context context, int isUpdate) {
		versionUpdate = context.getSharedPreferences("updateInfo",
				Context.MODE_PRIVATE);
		Editor editor = versionUpdate.edit();
		editor.putInt("isUpdate", isUpdate);
		editor.commit();
	}

	/**
	 * @param context
	 *            �Ƿ����APP
	 */
	public static int getUpdateInfo(Context context) {
		versionUpdate = context.getSharedPreferences("updateInfo",
				Context.MODE_PRIVATE);
		return versionUpdate.getInt("isUpdate", -1);

	}

	/**
	 * �����û���ͥסַ���Ա�
	 * 
	 * @param user
	 */
	public static void saveUserInfos(Context context, User user) {
		userInfos = context.getSharedPreferences("informations",
				Context.MODE_PRIVATE);
		Editor et = userInfos.edit();
		et.putInt("localProvinceId", user.getLocalProvinceId());
		et.putInt("localCityId", user.getLocalCityId());
		et.putInt("localCountyId", user.getLocalAreaId());
		et.commit();
	}

	/**
	 * �����û���ͥסַ���Ա�
	 * 
	 * @param
	 */
	public static User getUserInfos(Context context) {
		userInfos = context.getSharedPreferences("informations",
				Context.MODE_PRIVATE);
		int localProvinceId = userInfos.getInt("localProvinceId", -1);
		int localCityId = userInfos.getInt("localCityId", -1);
		int localCountyId = userInfos.getInt("localCountyId", -1);

		User user = new User();
		user.setLocalProvinceId(localProvinceId);
		user.setLocalCityId(localCityId);
		user.setLocalAreaId(localCountyId);
		return user;
	}




}
