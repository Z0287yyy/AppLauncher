package chris.utils.AppLauncher.base;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;



import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import chris.utils.AppLauncher.R;
import chris.utils.AppLauncher.permission.PermissionBaseActivity;

public class SUBaseActivity extends PermissionBaseActivity {

    private static SUBaseActivity _currentActivity;

    public <T extends SUBaseActivity> T self() {
        return (T) this;
    }

    private static final Map<String, WeakReference<SUBaseActivity>> cachedActivities = new ConcurrentHashMap<>();

    public static SUBaseActivity getCurrentActivity() {
        return _currentActivity;
    }

    // progress
    private ProgressBar progressBar;
    private TextView textView;
    private AlertDialog progressDialog;

    private Button cancelButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        cachedActivities.put(this.getClass().getSimpleName() + ":" + this.hashCode(), new WeakReference<>(this));
    }

    private void initProgressUi() {
        View view = View.inflate(self(), R.layout.su_view_loading, null);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.clearAnimation();

        textView = view.findViewById(R.id.textView);
        cancelButton = view.findViewById(R.id.cancelButton);

        progressDialog = new AlertDialog.Builder(this).create();
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        progressDialog.setCancelable(false);
        progressDialog.setView(view);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    public void showProgress(View.OnClickListener onCancelClicked) {
        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        }

        if (progressDialog == null) {
            initProgressUi();
        }

        progressBar.animate();
        cancelButton.setVisibility(View.VISIBLE);
        cancelButton.setOnClickListener(onCancelClicked);
        progressDialog.show();
    }

    public void showProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            return;
        }

        if (progressDialog == null) {
            initProgressUi();
        }

        progressBar.animate();
        cancelButton.setVisibility(View.GONE);
        progressDialog.show();
    }

    public void setProgressTips(CharSequence tips) {
        if (progressDialog != null && progressDialog.isShowing()) {
            textView.setText(tips);
        }
    }

    public void hideProgress() {
        if (progressDialog != null) {
            progressBar.clearAnimation();
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        _currentActivity = this;
    }

    @Override
    public void finish() {
        super.finish();

        cachedActivities.remove(this.getClass().getSimpleName() + ":" + this.hashCode());
        hideProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public View.OnClickListener onBackClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public static void finishAll() {
        Iterator<String> it = cachedActivities.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();

            try {
                WeakReference<SUBaseActivity> weak = cachedActivities.get(key);
                if (weak != null && weak.get() != null) {
                    weak.get().finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            it.remove();
        }
    }
}
