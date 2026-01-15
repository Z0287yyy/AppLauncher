package chris.utils.AppLauncher.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import chris.utils.AppLauncher.R;
import chris.utils.AppLauncher.base.SUBaseActivity;
import chris.utils.AppLauncher.entity.AppInfo;
import chris.utils.AppLauncher.utils.base.AlertUtils;
import chris.utils.AppLauncher.utils.base.MainLooperUtils;
import chris.utils.AppLauncher.utils.base.PreferenceUtils;
import chris.utils.AppLauncher.utils.base.UiUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AppListActivity extends SUBaseActivity {
	
	private List<AppInfo> selAppInfos = new ArrayList<AppInfo>();

	private final List<AppInfo> allAppInfos = new ArrayList<>();
	private final List<AppInfo> curAppInfos = new ArrayList<>();

	private EditText etName;
	private ListView lvApps;
	private ArrayAdapter<AppInfo> appInfoAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_applist);

		etName = findViewById(R.id.etName);
		etName.addTextChangedListener(textWatcher);
		etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					UiUtils.hideKeyboard(v);
				}
				return false;
			}
		});

		setTitle(R.string.action_settings);
		
		String selAppPacksStr = PreferenceUtils.getInstance().getString("applist_infos");
		try {
			selAppInfos = new Gson().fromJson(selAppPacksStr, new TypeToken<List<AppInfo>>(){}.getType());
		} catch (Exception e) {
		}
		if (selAppInfos == null) {
			selAppInfos = new ArrayList<AppInfo>();
		}

		lvApps = findViewById(R.id.lvApps);
		appInfoAdapter = new AppListAdapter<AppInfo>(this, R.layout.item_applist, R.id.tvAppName, curAppInfos);
		lvApps.setAdapter(appInfoAdapter);
		
		lvApps.setOnItemClickListener(onAppClicked);
		//lvApps.setOnItemSelectedListener(onAppSelected);

		getAppList();
	}

	private void getAppList() {
		new Thread() {
			@Override
			public void run() {
				List<AppInfo> fetched = queryAllLauncherApps(self(), false /* includeDisabled */);

				allAppInfos.clear();
				allAppInfos.addAll(fetched);

				// 和你的旧选择列表对齐：移除已卸载项
				List<AppInfo> shouldRemovedAppInfos = new ArrayList<>();
				for (AppInfo appInfo : selAppInfos) {
					if (!allAppInfos.contains(appInfo)) {
						shouldRemovedAppInfos.add(appInfo);
					}
				}
				selAppInfos.removeAll(shouldRemovedAppInfos);

                Collections.sort(allAppInfos, new Comparator<AppInfo>() {
                    @Override
                    public int compare(AppInfo a1, AppInfo a2) {
                        int i1 = 1;
                        int i2 = 1;
                        if (selAppInfos.contains(a1)) {
                            i1 = 0;
                        }
                        if (selAppInfos.contains(a2)) {
                            i2 = 0;
                        }
                        return i1 - i2;
                    }
                });

				MainLooperUtils.doInMainLooper(new MainLooperUtils.Action() {
					@Override
					public void doAction() {
						refreshAppListUi();
					}
				});
			}
		}.start();
	}

	private List<AppInfo> queryAllLauncherApps(Context context, boolean includeDisabled) {
		List<AppInfo> result = new ArrayList<>();
		try {
			android.content.pm.PackageManager pm = context.getPackageManager();

			android.content.Intent queryIntent = new android.content.Intent(android.content.Intent.ACTION_MAIN);
			queryIntent.addCategory(android.content.Intent.CATEGORY_LAUNCHER);

			java.util.List<android.content.pm.ResolveInfo> infos;
			if (android.os.Build.VERSION.SDK_INT >= 33) {
				long flags = android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;
				if (includeDisabled) {
					flags |= android.content.pm.PackageManager.MATCH_DISABLED_COMPONENTS;
				}
				infos = pm.queryIntentActivities(queryIntent,
						android.content.pm.PackageManager.ResolveInfoFlags.of(flags));
			} else {
				int flags = android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;
				if (includeDisabled) {
					flags |= android.content.pm.PackageManager.MATCH_DISABLED_COMPONENTS;
				}
				//noinspection deprecation
				infos = pm.queryIntentActivities(queryIntent, flags);
			}

			// 去重（同一个包可能有多个 Launcher Activity，这里按 包名+类名 唯一）
			java.util.LinkedHashMap<String, android.content.pm.ResolveInfo> unique = new java.util.LinkedHashMap<>();
			if (infos != null) {
				for (android.content.pm.ResolveInfo ri : infos) {
					if (ri.activityInfo == null) continue;
					String key = ri.activityInfo.packageName + "/" + ri.activityInfo.name;
					unique.put(key, ri);
				}
			}

			for (android.content.pm.ResolveInfo ri : unique.values()) {
				String label = safeLoadLabel(ri, pm);
				String pkg   = ri.activityInfo.packageName;
				String cls   = ri.activityInfo.name;

				// ✅ 这里把 ResolveInfo 映射到你的 AppInfo
				AppInfo ai = new AppInfo();
				ai.name = label;    // 你的适配器里引用了 appInfo.name
				ai.pack = pkg;      // 你的适配器里引用了 appInfo.pack
				// 如需保存具体启动 Activity，可在 AppInfo 里加字段：activityName/cls
				// ai.activityName = cls;

				result.add(ai);
			}

			// 排序：按名称
			java.util.Collections.sort(result, new java.util.Comparator<AppInfo>() {
				@Override public int compare(AppInfo a, AppInfo b) {
					String la = a.name != null ? a.name : "";
					String lb = b.name != null ? b.name : "";
					return la.compareToIgnoreCase(lb);
				}
			});

		} catch (Throwable t) {
			t.printStackTrace();
		}
		return result;
	}

	private static String safeLoadLabel(android.content.pm.ResolveInfo ri, android.content.pm.PackageManager pm) {
		try {
			CharSequence cs = ri.loadLabel(pm);
			if (cs != null) return cs.toString();
		} catch (Throwable ignored) {}
		return (ri.activityInfo != null ? ri.activityInfo.packageName : "");
	}

	private void refreshAppListUi() {
		curAppInfos.clear();

		String searchKey = etName.getText().toString().toLowerCase().trim();

		if (searchKey.length() > 0) {
			List<AppInfo> appInfos = new ArrayList<>();
			for (AppInfo appInfo : allAppInfos) {
				if (appInfo.name.toLowerCase(Locale.ROOT).contains(etName.getText().toString().toLowerCase(Locale.ROOT))) {
					appInfos.add(appInfo);
				}
			}

			curAppInfos.addAll(appInfos);
		} else {
			curAppInfos.addAll(allAppInfos);
		}

		appInfoAdapter.notifyDataSetChanged();
	}

	private AdapterView.OnItemClickListener onAppClicked = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			AppInfo appInfo = curAppInfos.get(position);
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


	private TextWatcher textWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			refreshAppListUi();
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
