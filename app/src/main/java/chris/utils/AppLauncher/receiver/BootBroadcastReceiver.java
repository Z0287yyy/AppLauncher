package chris.utils.AppLauncher.receiver;


import chris.utils.AppLauncher.activity.MainActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class BootBroadcastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, MainActivity.class);
		service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
		context.startActivity(service);
	}

}