package com.chris.applauncher.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppInfo implements Serializable {
	
	private static final long serialVersionUID = -5144803856877954203L;
	
	public String name;
	public String pack;

	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pack == null) ? 0 : pack.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppInfo other = (AppInfo) obj;
		if (pack == null) {
			if (other.pack != null)
				return false;
		} else if (!pack.equals(other.pack))
			return false;
		return true;
	}



	public static List<AppInfo> getAppInfos(Context context) {
		List<AppInfo> appInfos = new ArrayList<AppInfo>();
		try {
			PackageManager packageManager = null;
			packageManager = context.getPackageManager();
			List<PackageInfo> mAllPackages = new ArrayList<PackageInfo>();
			mAllPackages = packageManager.getInstalledPackages(0);
			for (int i = 0; i < mAllPackages.size(); i++) {
				PackageInfo packageInfo = mAllPackages.get(i);
				
				AppInfo info = new AppInfo();
				try {
					info.name = packageInfo.applicationInfo.loadLabel(packageManager).toString();
				} catch (Exception ex) {
				}
				info.pack = packageInfo.applicationInfo.packageName;
				
				appInfos.add(info);
			}
		} catch (Exception e) {
		}
		return appInfos;
	}
}
