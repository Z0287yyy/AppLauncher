package com.chris.applauncher.utils.base;


import com.chris.applauncher.AppLauncherApplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UiUtils {

	@SuppressWarnings("unchecked")
	public static <T extends View> T findTViewById(int id, Activity activity) {
		return (T) activity.findViewById(id);
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T findTViewById(int id, View view) {
		return (T) view.findViewById(id);
	}

	public static final int pixToDp(int pixels) {
		float density = AppLauncherApplication.getAppContext().getResources().getDisplayMetrics().density;
		return Math.round(pixels / density); // DIP
	}

	public static final int dpToPix(int dp) {
		float density = AppLauncherApplication.getAppContext().getResources().getDisplayMetrics().density;
		return Math.round(dp * density);
	}

	public static final void showKeyboard() {
		InputMethodManager imm = (InputMethodManager) AppLauncherApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	public static final void hideKeyboard(View view) {
		if (view != null) {
			InputMethodManager imm = (InputMethodManager) AppLauncherApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public static final void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) AppLauncherApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	public static void disableKeyboard(Activity activity) {
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	}

	public static void enableKeyboard(Activity activity) {
		activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
	}

	public static void adjustTextScale(TextView t, boolean isMore) {
		adjustTextScale(t, 0, 0, isMore);
	}

	public static void adjustTextScale(TextView t, float providedWidth, float providedHeight, boolean isMore) {
		// sometimes width and height are undefined (0 here), so if something
		// was provided, take it ;-)
		if (providedWidth == 0f)
			providedWidth = ((float) (t.getWidth() - t.getPaddingLeft() - t.getPaddingRight()));
		if (providedHeight == 0f)
			providedHeight = ((float) (t.getHeight() - t.getPaddingTop() - t.getPaddingBottom()));

		int line = t.getLineCount();

		float scaleY = providedHeight / (line * (t.getLineHeight()));

		if (isMore) {
			if (scaleY < 1.0f) {
				t.setTextSize(TypedValue.COMPLEX_UNIT_PX, t.getTextSize() * scaleY);
			}
		} else {
			if (scaleY > 1.0f) {
				t.setTextSize(TypedValue.COMPLEX_UNIT_PX, t.getTextSize() * scaleY);
			}
		}
	}

	public static void scaleListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0); // 计算子项View 的宽高
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + listView.getPaddingTop() + listView.getPaddingBottom();
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}

	public static int[] getLocationInWindow(View view) {
		int[] start_location = new int[2];
		view.getLocationInWindow(start_location);
		return start_location;
	}

	public static Point getPointInWindow(View view) {
		int[] start_location = new int[2];
		view.getLocationInWindow(start_location);
		Point point = new Point(start_location[0], start_location[1]);
		return point;
	}
	
	//////////////////////////////
	//// block of view inited ////
	//////////////////////////////
	public static interface ViewLoadHandler {
		public void onViewLoaded(View view);
	}
	
	public static void listener(final View v, final ViewLoadHandler loadHandler) {
		addOnGlobalLayoutListener(v, new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				removeOnGlobalLayoutListener(v, this);
				if (loadHandler != null) {
					loadHandler.onViewLoaded(v);
				}
			}
		});
	}
	
	public static void addOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
		v.getViewTreeObserver().addOnGlobalLayoutListener(listener);
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public static void removeOnGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
		if (Build.VERSION.SDK_INT < 16) {
			v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
		} else {
			v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
		}
	}

}
