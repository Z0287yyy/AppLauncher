package chris.utils.AppLauncher.activity;

import java.util.ArrayList;
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
		checkAndRequestPermissions(new PermissionCallback() {
			@Override
			public void didAllPermissionGranted() {
				new Thread() {
					@Override
					public void run() {
						allAppInfos.addAll(AppInfo.getAppInfos(self(), false));

						List<AppInfo> shouldRemovedAppInfos = new ArrayList<AppInfo>();
						for (AppInfo appInfo : selAppInfos) {
							if (!allAppInfos.contains(appInfo)) {
								shouldRemovedAppInfos.add(appInfo);
							}
						}
						selAppInfos.removeAll(shouldRemovedAppInfos);

						MainLooperUtils.doInMainLooper(new MainLooperUtils.Action() {
							@Override
							public void doAction() {
								refreshAppListUi();
							}
						});
					}
				}.start();
			}

			@Override
			public void didGetError(String error) {
				AlertUtils.alert(self(), "", getString(R.string.need_permission), false, new AlertUtils.Callback() {
					@Override
					public void onItemClicked(DialogInterface dialog) {
						finish();
					}
				});
			}
		}, Manifest.permission.QUERY_ALL_PACKAGES);

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
