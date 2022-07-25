package chris.utils.AppLauncher.utils.base;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import chris.utils.AppLauncher.R;


public class AlertUtils {

	public static class AlertItem {
		public final String item;
		public final Callback callback;

		public AlertItem(String item, Callback callback) {
			this.item = item;
			this.callback = callback;
		}

		public static AlertItem newInstance(String item, Callback callback) {
			return new AlertItem(item, callback);
		}
	}

	public static interface Callback {
		public void onItemClicked(DialogInterface dialog);
	}
	
	public static void alert(Context context, CharSequence content) {
		final AlertDialog dialog = new AlertDialog.Builder(context).setTitle(content).setCancelable(false).setNegativeButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).create();
		dialog.show();
	}

	public static void alert(Context context, CharSequence title, CharSequence message) {
		alert(context, title, message, false);
	}

	public static void alert(Context context, CharSequence title, CharSequence message, boolean cancelable) {
		alert(context, title, message, cancelable, null);
	}

	public static void alert(Context context, CharSequence title, CharSequence message, boolean cancelable, final Callback callback) {
		alert(context, title, message, cancelable, context.getString(R.string.OK), callback);
	}

	public static void alert(Context context, CharSequence title, CharSequence message, boolean cancelable, CharSequence buttonName, final Callback callback) {
		alert(context, title, message, cancelable, false, null, AlertItem.newInstance(context.getString(R.string.OK), callback), null);
	}

	public static void alert(Context context, CharSequence title, CharSequence message, boolean cancelable, boolean systemAlert, AlertItem itemNeutral, AlertItem itemNegative, AlertItem itemPositive) {
		try {
			AlertDialog.Builder bulder = new AlertDialog.Builder(context);
			bulder.setTitle(title);
			bulder.setCancelable(cancelable);
			bulder.setMessage(message);

			if (itemNeutral != null) {
				bulder.setNeutralButton(itemNeutral.item, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (itemNeutral.callback != null) {
							try {
								itemNeutral.callback.onItemClicked(dialog);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
			}

			if (itemNegative != null) {
				bulder.setNegativeButton(itemNegative.item, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (itemNegative.callback != null) {
							try {
								itemNegative.callback.onItemClicked(dialog);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
			}

			if (itemPositive != null) {
				bulder.setPositiveButton(itemPositive.item, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (itemPositive.callback != null) {
							try {
								itemPositive.callback.onItemClicked(dialog);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
			}

			final Dialog dialog = bulder.create();
			if (systemAlert) {
				dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
			}
			dialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
