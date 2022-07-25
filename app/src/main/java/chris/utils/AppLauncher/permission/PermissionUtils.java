package chris.utils.AppLauncher.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;


public class PermissionUtils {

    public static void showPermissionPage(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (!hasPermission(context, permission)) {
                return false;
            }
        }

        return true;
    }

    public static boolean hasPermission(Context context, String permission) {
        if (context == null) {
            return false;
        }

        if (permission.equals(Manifest.permission.ACCESS_MEDIA_LOCATION) && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return true;
        }

        if (permission.equals(Manifest.permission.QUERY_ALL_PACKAGES) && Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return true;
        }

        PackageManager pm = context.getPackageManager();

        if (pm.checkPermission(permission, context.getPackageName()) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }
}
