package com.chris.applauncher;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.view.WindowManager;

public class AppLauncherApplication extends Application {
	private static AppLauncherApplication appContext;

	public void onCreate() {
		super.onCreate();
		
		initStrictMode();

		appContext = this;
		
	}

	public static AppLauncherApplication getApplication(AppLauncherApplication context) {
		return (AppLauncherApplication) context.getApplicationContext();
	}

	public static final AppLauncherApplication getAppContext() {
		return appContext;
	}

	public static Resources getResource() {
		return getAppContext().getResources();
	}

	public static String getResourceString(final int resourceId) {
		return getAppContext().getResources().getString(resourceId);
	}

	public static LocalBroadcastManager getBroadcaster() {
		return LocalBroadcastManager.getInstance(getAppContext());
	}

	public void onTerminate() {
		super.onTerminate();
	}

	public static final int convertToDIP(int pixels) {
		float density = getAppContext().getResources().getDisplayMetrics().density;
		return Math.round(pixels / density); // DIP
	}

	public static int getScreenWidth() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			return privateGetScreenWidth();
		} else {
			return privateGetScreenWidthOld();
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private static int privateGetScreenWidth() {
		WindowManager wm = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);
		Point outSize = new Point();
		wm.getDefaultDisplay().getSize(outSize);
		return outSize.x;
	}
	
	@Deprecated
	private static int privateGetScreenWidthOld() {
		WindowManager wm = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void initStrictMode() {
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
			return;
		}
		if (BuildConfig.DEBUG) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectNetwork()
				.penaltyLog()
				.penaltyLog()    //.penaltyDeath()
				.build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectAll()
				.penaltyLog()
				.build());
		}
	}
}
