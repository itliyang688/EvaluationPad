package com.fek12.basic.utils.measure;

import android.view.View;

/**
 * @author liyang
 * @Data 2017/12/5
 */

public class MeasureUtils {
    /**
     * 获取view的宽度
     *
     * @param view
     * @return
     */
    public static int getWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }

    /**
     * 获取view的高度
     *
     * @param view
     * @return
     */
    public static int getHeight(View view) {
        if (view == null) {
            return -1;
        }
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }
}
