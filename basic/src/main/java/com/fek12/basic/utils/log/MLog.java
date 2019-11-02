package com.fek12.basic.utils.log;

import android.util.Log;

import com.fek12.basic.utils.baseUtils.BaseAppUtils;


public class MLog {
    private static int MAX_LOG_SIZE = 1000;
    private static boolean DEBUG = BaseAppUtils.isDebug();

    public static void i(String tag, String text) {
        if (DEBUG) {
            for (int i = 0; i <= text.length() / MAX_LOG_SIZE; i++) {
                int start = i * MAX_LOG_SIZE;
                int end = (i + 1) * MAX_LOG_SIZE;
                end = end > text.length() ? text.length() : end;
                Log.i(tag, text.substring(start, end));
            }
        }
    }

    public static void e(String tag, String text) {
        if (DEBUG) {
            Log.e(tag, text);
        }
    }

    public static void d(String tag, String text) {
        if (DEBUG) {
            Log.d(tag, text);
        }
    }

    public static void w(String tag, String text) {
        if (DEBUG) {
            for (int i = 0; i <= text.length() / MAX_LOG_SIZE; i++) {
                int start = i * MAX_LOG_SIZE;
                int end = (i + 1) * MAX_LOG_SIZE;
                end = end > text.length() ? text.length() : end;
                Log.w(tag, text.substring(start, end));
            }
        }

    }
}
