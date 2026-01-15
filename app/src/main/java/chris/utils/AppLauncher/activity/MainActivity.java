package chris.utils.AppLauncher.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import chris.utils.AppLauncher.AppLauncherActivity;
import chris.utils.AppLauncher.R;
import chris.utils.AppLauncher.entity.AppInfo;
import chris.utils.AppLauncher.utils.base.MainLooperUtils;
import chris.utils.AppLauncher.utils.base.PreferenceUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppLauncherActivity {

    private static final int DEFAULT_WAIT_START_APPS_SEC = 10;
    private static final int ACTION_WAIT_START_APPS_SEC = 1;
    private static final int START_APPS_INTERVAL_SEC = 10;

	private Button btnShowList;
	private Button btnCancelAndExit;
	private Button btnBeginLaunch;

    private TextView tvTips;
    private TextView tvStartInfo;

	private volatile boolean startAppCanceled;

    private final Handler mainHandler = new Handler(Looper.getMainLooper());
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setTitle(R.string.app_name);

        tvTips = findTViewById(R.id.tvTips);
        tvStartInfo = findTViewById(R.id.tvStartInfo);

		btnShowList = findTViewById(R.id.btnShowList);
		btnShowList.setOnClickListener(onShowListClicked);
		
		btnCancelAndExit = findTViewById(R.id.btnCancelAndExit);
		btnCancelAndExit.setOnClickListener(onCancelAndExitClicked);
		
		btnBeginLaunch = findTViewById(R.id.btnBeginLaunch);
		btnBeginLaunch.setOnClickListener(onBeginLaunchAppsClicked);
	}

    @Override
    protected void onResume() {
        super.onResume();

        mainHandler.removeCallbacksAndMessages(null);
        mainHandler.postDelayed(startSelectedApps, DEFAULT_WAIT_START_APPS_SEC * 1000L);

        String tips = String.format(getString(R.string.tips), DEFAULT_WAIT_START_APPS_SEC);
        tvTips.setText(tips);

        loopCheck(DEFAULT_WAIT_START_APPS_SEC);

        refreshStartAppsInfo();
    }

    private void loopCheck(int countDown) {

        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String tips = String.format(getString(R.string.tips), countDown - 1);
                tvTips.setText(tips);

                if (countDown > 0) {
                    loopCheck(countDown - 1);
                }
            }
        }, 1000L);
    }

    private void refreshStartAppsInfo() {
        StringBuilder sb = new StringBuilder();
        try {
            List<AppInfo> selAppInfos = getSelAppInfos();

            for (AppInfo app: selAppInfos) {
                if (sb.length() > 0) {
                    sb.append("; \r\n");
                }
                sb.append(app.name + " (" + app.pack + ")");
            }
            if (sb.length() > 0) {
                sb.append(". ");
            }
        } catch (Exception e) {
        }

        tvStartInfo.setText(sb.toString());
    }


    private List<AppInfo> getSelAppInfos() {
        List<AppInfo> selAppInfos = new ArrayList<>();
        String selAppPacksStr = PreferenceUtils.getInstance().getString("applist_infos");
        try {
            selAppInfos = new Gson().fromJson(selAppPacksStr, new TypeToken<List<AppInfo>>(){}.getType());
        } catch (Exception e) {
        }
        return selAppInfos;
    }
	
	private final Runnable startSelectedApps = new Runnable() {
		@Override
		public void run() {
			if (startAppCanceled) {
				return;
			}

            List<AppInfo> selAppInfos = getSelAppInfos();

			if (selAppInfos != null) {
                List<Intent> intents = new ArrayList<>();

                PackageManager packageManager = getPackageManager();

				for (int i = 0; i < selAppInfos.size(); i ++) {
                    AppInfo appInfo = selAppInfos.get(i);

                    try {
                        PackageInfo pi = packageManager.getPackageInfo(appInfo.pack, 0);
                        if (pi == null || pi.packageName == null || pi.packageName.isEmpty()) {
                            continue;
                        }
                    } catch (Exception e) {
                        continue;
                    }

                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent = packageManager.getLaunchIntentForPackage(appInfo.pack);

                    intents.add(intent);
				}

                Intent[] intentsArr = new Intent[intents.size()];
                startActivities(intents.toArray(intentsArr));

                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, selAppInfos.size() * START_APPS_INTERVAL_SEC * 1000L);
			}
		}
	};
	
	
	
	private final View.OnClickListener onShowListClicked = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
            mainHandler.removeCallbacksAndMessages(null);
            tvTips.setText(null);
            tvStartInfo.setText(null);
			
			startActivity(new Intent(getBaseContext(), AppListActivity.class));
		}
		
	};
	
	private final View.OnClickListener onCancelAndExitClicked = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
            mainHandler.removeCallbacksAndMessages(null);

            tvTips.setText(null);
            tvStartInfo.setText(null);

			finish();
		}
		
	};
	
	private final View.OnClickListener onBeginLaunchAppsClicked = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startAppCanceled = false;

            String tips = String.format(getString(R.string.tips), ACTION_WAIT_START_APPS_SEC);
            tvTips.setText(tips);

            refreshStartAppsInfo();

            mainHandler.removeCallbacksAndMessages(null);
            mainHandler.postDelayed(startSelectedApps, ACTION_WAIT_START_APPS_SEC * 1000L);
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
