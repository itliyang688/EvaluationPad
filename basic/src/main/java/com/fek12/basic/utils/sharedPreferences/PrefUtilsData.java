package com.fek12.basic.utils.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.fek12.basic.application.BaseApplication;

/**
 * @author liyang
 * @Data 2019/9/9
 * @describe 使用时使用   PrefUtilsData
 */

public abstract class PrefUtilsData {


    public static String getCookiess() {
        return SP.getString(PrefConstants.COOKIESS, "");
    }

    public static void setCookiess(String sportData) {
        SP.putString(PrefConstants.COOKIESS, sportData);
    }


    /**
     * 适用于退出登录清除用户数据
     */
    public static void userClear() {
        SP.userClear();
    }

    protected static class SP {
        //非用户的数据,用户退出不清空
        private static SharedPreferences sUserSharedPreferences;

        private static SharedPreferences getSharedPreferences() {
            if (sUserSharedPreferences == null) {
                sUserSharedPreferences = BaseApplication.getApp().getSharedPreferences(PrefConstants.PREF_USER_DATA_TABLE, Context.MODE_PRIVATE);
            }
            return sUserSharedPreferences;
        }

        public static void putBoolean(String key, boolean value) {
            getSharedPreferences().edit().putBoolean(key, value).apply();
        }

        public static boolean getBoolean(String key, boolean defValue) {
            return getSharedPreferences().getBoolean(key, defValue);
        }

        public static void putInt(String key, int value) {
            getSharedPreferences().edit().putInt(key, value).apply();
        }

        public static int getInt(String key, int defValue) {
            return getSharedPreferences().getInt(key, defValue);
        }

        public static void putFloat(String key, float value) {
            getSharedPreferences().edit().putFloat(key, value).apply();
        }

        public static float getFloat(String key, float defValue) {
            return getSharedPreferences().getFloat(key, defValue);
        }

        public static void putString(String key, String value) {
            getSharedPreferences().edit().putString(key, value).apply();
        }

        public static String getString(String key, String defValue) {
            return getSharedPreferences().getString(key, defValue);
        }

        public static void userClear() {
            sUserSharedPreferences.edit().clear().apply();
        }
    }


}
