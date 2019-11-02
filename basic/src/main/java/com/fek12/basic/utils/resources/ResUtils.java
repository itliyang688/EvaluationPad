package com.fek12.basic.utils.resources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.fek12.basic.application.BaseApplication;

/**
 * @author liyang
 * @Data 2017/11/30
 * 获取res资源的工具类
 */
public class ResUtils {

    public static String getString(@StringRes int resId) {
        return BaseApplication.getApp().getResources().getString(resId);
    }

    public static int getDimen(@DimenRes int resId) {
        return BaseApplication.getApp().getResources().getDimensionPixelOffset(resId);
    }

    public static int getColor(@ColorRes int resId) {
        return BaseApplication.getApp().getResources().getColor(resId);
    }

    public static Drawable getDrawable(@DrawableRes int resId) {
        return BaseApplication.getApp().getResources().getDrawable(resId);
    }

    public static Bitmap getBitmap(@DrawableRes int resId) {
        return BitmapFactory.decodeResource(BaseApplication.getApp().getResources(), resId);
    }
}
