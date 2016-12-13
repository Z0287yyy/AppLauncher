package com.chris.applauncher.utils.base;



import com.chris.applauncher.AppLauncherApplication;
import com.chris.applauncher.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ToastUtil {
	public static void show(Context context,String s1,String s2){
		LayoutInflater mInflater = LayoutInflater.from(context);
		View convertView = mInflater.inflate(R.layout.su_toast, null);
		Toast toast = new Toast(context);
		toast.setDuration(Toast.LENGTH_LONG);
//		ImageView image = (ImageView) convertView.findViewById(R.id.toast_image);
		TextView contentText = (TextView) convertView.findViewById(R.id.toast_title);
//		image.setBackgroundResource(R.drawable.toast_info);
		contentText.setText(s1);
		toast.setView(convertView);  
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();  

	}
	
	public static void show(int msgId) {
		String s = AppLauncherApplication.getAppContext().getResources().getString(msgId);
		ToastUtil.show(s);
	}
	
	public static void show(String msg) {
		ToastUtil.show(AppLauncherApplication.getAppContext(), msg, null);
	}
}