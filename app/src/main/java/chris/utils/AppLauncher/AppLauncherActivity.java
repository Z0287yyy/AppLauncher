package chris.utils.AppLauncher;

import chris.utils.AppLauncher.permission.PermissionBaseActivity;
import chris.utils.AppLauncher.utils.base.UiUtils;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

public class AppLauncherActivity extends PermissionBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // ========== 核心：开启状态栏透明+布局延伸 ==========
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 1. 状态栏完全透明
            window.setStatusBarColor(Color.TRANSPARENT);
            // 2. 让布局延伸到状态栏区域（fitsSystemWindows才会生效）
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

		setContentView(R.layout.activity_shared_utils);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shared_utils, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public <T extends View> T findTViewById(int id) {
		return UiUtils.findTViewById(id, this);
	}
}
