package chris.utils.AppLauncher.permission;

import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class PermissionBaseActivity extends AppCompatActivity {

    protected static final int PermissionRequestCode = 333001;

    private PermissionCallback permissionCallback;

    public static interface PermissionCallback {
        public void didAllPermissionGranted();
        public void didGetError(String error);
    }


    public void checkAndRequestPermissions(PermissionCallback callback, String... permissions) {
        permissionCallback = callback;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        List<String> deniedPermissions = new ArrayList<>();
        for (String permission: permissions) {
            if (!hasPermission(permission)) {
                deniedPermissions.add(permission);
            }
        }

        if (permissionCallback != null) {
            if (deniedPermissions.isEmpty()) {
                permissionCallback.didAllPermissionGranted();
                return;
            }
        }

        String[] dps = new String[deniedPermissions.size()];
        dps = deniedPermissions.toArray(dps);

        requestPermissions(dps, PermissionRequestCode);
    }


    public boolean hasPermission(String permission) {
        return PermissionUtils.hasPermission(this, permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != PermissionRequestCode) {
            return;
        }

        List<String> deniedPermissions = new ArrayList<>();
        for (int index = 0; index < permissions.length; index ++) {
            if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[index]);
            }
        }

        if (permissionCallback != null) {
            if (deniedPermissions.isEmpty()) {
                permissionCallback.didAllPermissionGranted();
            } else {
                permissionCallback.didGetError("Not all permissions are allowed.");
            }
        }
    }
}
