package cn.fek12.evaluation.model.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import cn.fek12.evaluation.application.MyApplication;

/**
 * @author xc
 * @Data 2017/12/1
 * @describe 使用时使用   PrefUtilsData
 */

public abstract class PrefUtilsData {

    public static String getUserId() {
        return SP.getString(PrefConstants.USERID, "");
    }

    public static void setUserId(String userId) {
        SP.putString(PrefConstants.USERID, userId);
    }

    public static boolean getIsReportRefresh() {
        return SP.getBoolean(PrefConstants.REPORTREFRESH, false);
    }

    public static void setIsReportRefresh(boolean isRefresh) {
        SP.putBoolean(PrefConstants.REPORTREFRESH, isRefresh);
    }

    public static boolean getIsPromoteRefresh() {
        return SP.getBoolean(PrefConstants.PROMOTEREFRESH, false);
    }

    public static void setIsPromoteRefresh(boolean isRefresh) {
        SP.putBoolean(PrefConstants.PROMOTEREFRESH, isRefresh);
    }

    public static boolean getIsEvaluationRefresh() {
        return SP.getBoolean(PrefConstants.EVALUATIONREFRESH, false);
    }

    public static void setIsEvaluationRefresh(boolean isRefresh) {
        SP.putBoolean(PrefConstants.EVALUATIONREFRESH, isRefresh);
    }

    public static boolean getIsCollectionRefresh() {
        return SP.getBoolean(PrefConstants.COLLECTION, false);
    }

    public static void setIsCollectionRefresh(boolean isRefresh) {
        SP.putBoolean(PrefConstants.COLLECTION, isRefresh);
    }

    public static boolean getIsPlayCountRefresh() {
        return SP.getBoolean(PrefConstants.PLAYCOUNTREFRESH, false);
    }

    public static void setIsPlayCountRefresh(boolean isRefresh) {
        SP.putBoolean(PrefConstants.PLAYCOUNTREFRESH, isRefresh);
    }

    public static String getToken() {
        return SP.getString(PrefConstants.TOKEN, "");
    }

    public static void setToken(String token) {
        SP.putString(PrefConstants.TOKEN, token);
    }

    public static int getRemindNum() {
        return SP.getInt(PrefConstants.REMINDNUM, 0);
    }

    public static void setRemindNum(int num) {
        SP.putInt(PrefConstants.REMINDNUM, num);
    }

    public static String getPer_level() {
        return SP.getString(PrefConstants.PER_LEVEL, "");
    }

    public static void setPer_level(String userId) {
        SP.putString(PrefConstants.PER_LEVEL, userId);
    }

    public static String getBannerCache() {
        return SP.getString(PrefConstants.BANNERCACHE, "");
    }

    public static void setBannerCache(String bannerCache) {
        SP.putString(PrefConstants.BANNERCACHE, bannerCache);
    }

    public static void setAnalysisCache(String analysis) {
        SP.putString(PrefConstants.ANALYSIS, analysis);
    }

    public static String getAnalysisCache() {
        return SP.getString(PrefConstants.ANALYSIS, "");
    }

    public static void setPaperResultIdCache(String paperResultId) {
        SP.putString(PrefConstants.PAPERRESULTID, paperResultId);
    }

    public static String getPaperResultIdCache() {
        return SP.getString(PrefConstants.PAPERRESULTID, "");
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
                sUserSharedPreferences = MyApplication.getApp().getSharedPreferences(PrefConstants.PREF_NAME, Context.MODE_PRIVATE);
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
            sUserSharedPreferences.edit().clear().commit();
        }
    }


}
