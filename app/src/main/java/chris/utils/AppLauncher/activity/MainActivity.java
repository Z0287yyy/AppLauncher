package chris.utils.AppLauncher.activity;

import java.util.ArrayList;
import java.util.List;

import chris.utils.AppLauncher.AppLauncherActivity;
import chris.utils.AppLauncher.R;
import chris.utils.AppLauncher.entity.AppInfo;
import chris.utils.AppLauncher.utils.base.PreferenceUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppLauncherActivity {

	private Button btnShowList;
	private Button btnCancelAndExit;
	private Button btnBeginLaunch;
	
	private boolean startAppCanceled;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setTitle(R.string.app_name);
		
		btnShowList = findTViewById(R.id.btnShowList);
		btnShowList.setOnClickListener(onShowListClicked);
		
		btnCancelAndExit = findTViewById(R.id.btnCancelAndExit);
		btnCancelAndExit.setOnClickListener(onCancelAndExitClicked);
		
		btnBeginLaunch = findTViewById(R.id.btnBeginLaunch);
		btnBeginLaunch.setOnClickListener(onBeginLaunchAppsClicked);
		
		btnShowList.postDelayed(startSelectedApps, 5000l);
	}
	
	private Runnable startSelectedApps = new Runnable() {
		
		private List<AppInfo> selAppInfos = new ArrayList<AppInfo>();
		@Override
		public void run() {
			if (startAppCanceled) {
				return;
			}
			
			String selAppPacksStr = PreferenceUtils.getInstance().getString("applist_infos");
			try {
				selAppInfos = new Gson().fromJson(selAppPacksStr, new TypeToken<List<AppInfo>>(){}.getType());
			} catch (Exception e) {
			}

			if (selAppInfos != null) {
				for (AppInfo appInfo : selAppInfos) {
					try {
						PackageManager packageManager = getPackageManager();
						Intent intent = new Intent();
						intent = packageManager.getLaunchIntentForPackage(appInfo.pack);
						startActivity(intent);
					} catch (Exception e) {
					}
					try {
						Thread.sleep(1000l);
					} catch (Exception e) {
					}
				}
			}

			try {
				Thread.sleep(1000l);
			} catch (Exception e) {
			}
			
			finish();
		}
	};
	
	
	
	private View.OnClickListener onShowListClicked = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			startAppCanceled = true;
			
			startActivity(new Intent(getBaseContext(), AppListActivity.class));
		}
		
	};
	
	private View.OnClickListener onCancelAndExitClicked = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startAppCanceled = true;
			
			finish();
		}
		
	};
	
	private View.OnClickListener onBeginLaunchAppsClicked = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startAppCanceled = false;
			
			btnShowList.postDelayed(startSelectedApps, 100l);
		}
		
	};
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			onShowListClicked.onClick(null);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
