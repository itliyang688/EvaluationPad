package com.fek12.basic.utils.toast;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.fek12.basic.R;

import java.lang.ref.WeakReference;

public class GlobalToast {
	// an weak handle of the real toast object, we check it every time before we
	// pop up a new toast
	// to cancel the old one
	private static WeakReference<Toast> sToastRef = null;
	private static Handler sHandler = new Handler(Looper.getMainLooper());
	private static long sUiThreadId = Looper.getMainLooper().getThread()
			.getId();

	//wangruifeng 2014-04-25 modified 传的值类型由String改为CharSequence
	public static void showToast(final Context context, final CharSequence string,
			final int duration) {
		if (Thread.currentThread().getId() == sUiThreadId) {
			showToastOnUiThread(context, string, duration);
		} else {
			sHandler.post(new Runnable() {

				@Override
				public void run() {
					showToastOnUiThread(context, string, duration);
				}
			});
		}
	}

	/**
	 * show a toast for about 3.5 seconds which display the given string
	 * @param context
	 * @param string
	 */
	public static void showLongToast( Context context, final CharSequence string) {
		if(context != null){
			if (context instanceof Activity) {
				showToast(((Activity)context).getApplication(), string, Toast.LENGTH_LONG);
			} else {
				showToast(context, string, Toast.LENGTH_LONG);
			}
		}
	}
	
	//
	public static void dismissToast(){
		if (Thread.currentThread().getId() == sUiThreadId) {
			dismissToastOnUiThread();
		} else {
			sHandler.post(new Runnable() {

				@Override
				public void run() {
					dismissToastOnUiThread();
				}
			});
		}
	}

	// 只能在Ui线程执行
	synchronized private static void showToastOnUiThread(Context context, CharSequence string, int duration) {
		Toast oldToast = null;

		if (sToastRef != null && (oldToast = sToastRef.get()) != null) {
			((TextView) oldToast.getView()).setText(string);
			oldToast.setDuration(duration);
			oldToast.show();
			return;
		}

		TextView textView = (TextView) LayoutInflater.from(context).inflate(
				R.layout.ktvdarenlib_global_toast, null);
		textView.setText(string);
		int padding = (int) (24 * (float)(context.getResources().getDisplayMetrics().density / 1.5f));
		textView.setPadding(padding, padding, padding, padding);
		Toast toast = new Toast(context);
		toast.setDuration(duration);
		toast.setView(textView);
		toast.setGravity(Gravity.CENTER, 0, 0);
		sToastRef = new WeakReference<Toast>(toast);
		toast.show();

	}
	
	// 只能在Ui线程执行
	synchronized private static void dismissToastOnUiThread() {
		Toast oldToast = null;

		if (sToastRef != null && (oldToast = sToastRef.get()) != null) {
			oldToast.cancel();
			return;
		}
	}
	
}