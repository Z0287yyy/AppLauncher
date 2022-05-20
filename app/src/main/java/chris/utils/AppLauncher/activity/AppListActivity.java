package chris.utils.AppLauncher.activity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import chris.utils.AppLauncher.AppLauncherActivity;
import chris.utils.AppLauncher.R;
import chris.utils.AppLauncher.entity.AppInfo;
import chris.utils.AppLauncher.utils.base.ObjectUtils;
import chris.utils.AppLauncher.utils.base.PreferenceUtils;
import chris.utils.AppLauncher.utils.base.StringUtils;
import chris.utils.AppLauncher.utils.base.UiUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AppListActivity extends AppLauncherActivity {
	
	private List<AppInfo> selAppInfos = new ArrayList<AppInfo>();

	private List<AppInfo> appInfos;
	private ListView lvApps;
	private ArrayAdapter<AppInfo> appInfoAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applist);
		
		setTitle(R.string.action_settings);
		
		String selAppPacksStr = PreferenceUtils.getInstance().getString("applist_infos");
		try {
			selAppInfos = new Gson().fromJson(selAppPacksStr, new TypeToken<List<AppInfo>>(){}.getType());
		} catch (Exception e) {
		}
		if (selAppInfos == null) {
			selAppInfos = new ArrayList<AppInfo>();
		}
		
		appInfos = AppInfo.getAppInfos(getApplicationContext());
		
		List<AppInfo> shouldRemovedAppInfos = new ArrayList<AppInfo>();
		for (AppInfo appInfo : selAppInfos) {
			if (!appInfos.contains(appInfo)) {
				shouldRemovedAppInfos.add(appInfo);
			}
		}
		selAppInfos.removeAll(shouldRemovedAppInfos);
		
		lvApps = findTViewById(R.id.lvApps);
		appInfoAdapter = new AppListAdapter<AppInfo>(this, R.layout.item_applist, R.id.tvAppName, appInfos);
		lvApps.setAdapter(appInfoAdapter);
		
		lvApps.setOnItemClickListener(onAppClicked);
		//lvApps.setOnItemSelectedListener(onAppSelected);
	}
	
	private AdapterView.OnItemClickListener onAppClicked = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			AppInfo appInfo = appInfos.get(position);
			boolean check = !selAppInfos.contains(appInfo);
			if (selAppInfos.contains(appInfo)) {
				selAppInfos.remove(appInfo);
			} else {
				selAppInfos.add(appInfo);
			}
			PreferenceUtils.getInstance().setString("applist_infos", new Gson().toJson(selAppInfos));
			
			try {
				ViewHolder holder = (ViewHolder) view.getTag();
				holder.tvAppName.setChecked(check);
			} catch (Exception e) {
			}
		}
	};
	
	private AdapterView.OnItemSelectedListener onAppSelected = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			//lvApps.setItemChecked(position, true);
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}

		
	};
	
	private class AppListAdapter<T> extends ArrayAdapter<AppInfo> {

		public AppListAdapter(Context context, int resource, int textViewResourceId, List<AppInfo> objects) {
			super(context, resource, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = super.getView(position, convertView, parent);
			
			ViewHolder holder;
			if (view.getTag() != null) {
				holder = (ViewHolder) view.getTag();
			} else {
				holder = new ViewHolder();
				holder.tvAppName = UiUtils.findTViewById(R.id.tvAppName, view);
				holder.tvAppPack = UiUtils.findTViewById(R.id.tvAppPack, view);
				view.setTag(holder);
			}
			
			AppInfo appInfo = getItem(position);
			
			holder.tvAppName.setChecked(selAppInfos.contains(appInfo));
			holder.tvAppPack.setText(appInfo.pack);
			
			return view;
		}
		
	}
	
	private class ViewHolder {
		CheckedTextView tvAppName;
		TextView tvAppPack;
	}
}
