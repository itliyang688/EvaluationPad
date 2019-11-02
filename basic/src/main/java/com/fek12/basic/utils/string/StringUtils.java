package com.fek12.basic.utils.string;

import android.text.TextUtils;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * @author mwqi
 * @Data 2014/6/8
 */

public class StringUtils {
    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        if (value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim()) &&
                !"\"null\"".equalsIgnoreCase(value.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 判断请求数据的格式
     *
     * @param date 请求下来的数据是否为json
     * @return true 为是json  false 为不是json
     */
    public static boolean isJson(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        } else if (date.startsWith("{") && date.endsWith("}")) {
            return true;
        } else if (date.startsWith("[") && date.endsWith("]")) {
            return true;
        }
        return false;
    }

    /**
     * 获取textView的文字
     *
     * @param textView
     * @return
     */
    public static String getText(TextView textView) {
        if (textView == null)
            return "";
        return textView.getText().toString().trim();
    }

    /**
     * 保留小数点后两位
     *
     * @param d
     * @return
     */
    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(d);
    }


}
