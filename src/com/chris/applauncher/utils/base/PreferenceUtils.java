package com.chris.applauncher.utils.base;

import com.chris.applauncher.AppLauncherApplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author melody
 * @version 创建时间：2014年9月29日 下午2:47:16 类说明
 */
public class PreferenceUtils {
	/**
	 * 保存Preference的name
	 */
	public static final String PREFERENCE_VERSION = "1.0";
	public static final String PREFERENCE_NAME = "shared_key_applist" + PREFERENCE_VERSION;
	private static SharedPreferences mSharedPreferences;
	private static PreferenceUtils mPreferenceUtils;
	private static SharedPreferences.Editor editor;

	private PreferenceUtils() {
		mSharedPreferences = AppLauncherApplication.getAppContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 单例模式，获取instance实例
	 * 
	 * @param cxt
	 * @return
	 */
	public static PreferenceUtils getInstance() {
		if (mPreferenceUtils == null) {
			mPreferenceUtils = new PreferenceUtils();
		}
		editor = mSharedPreferences.edit();
		return mPreferenceUtils;
	}

	public void setString(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	public String getString(String key) {
		return mSharedPreferences.getString(key, "");
	}
}
