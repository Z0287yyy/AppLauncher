package chris.utils.AppLauncher.utils.base;

import android.os.Handler;
import android.os.Looper;

public class MainLooperUtils {

    public static interface Action {
        public void doAction();
    }

    public static void doInMainLooper(final Action action) {
        if (action == null) {
            return;
        }
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            action.doAction();
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                action.doAction();
            }
        }, 0);

    }

    public static void doInMainLooper(final long delay, final Action action) {
        if (action == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                action.doAction();
            }
        }, delay);

    }

}
