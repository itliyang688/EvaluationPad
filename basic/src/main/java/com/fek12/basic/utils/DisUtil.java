package com.fek12.basic.utils;

import android.content.Context;
import android.view.WindowManager;

import com.fek12.basic.base.BaseActivity;

public class DisUtil {

	public static int getScreenWidth(BaseActivity context)
	{
		WindowManager wm = context.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();//屏幕的宽度
		return width;
	}
	public static int getScreenHeight(BaseActivity context)
	{
		WindowManager wm = context.getWindowManager();
		int height = wm.getDefaultDisplay().getHeight();//屏幕的宽度
		return height;
	}
	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dp(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	public static int dp2px(Context ctx, float dp) {
		float density = ctx.getResources().getDisplayMetrics().density;// 获取设备密度
		int px = (int) (dp * density + 0.5f);// 4.3, 4.9, 加0.5是为了四舍五入
		return px;
	}

}
