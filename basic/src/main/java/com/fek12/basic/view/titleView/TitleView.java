package com.fek12.basic.view.titleView;

import android.content.Context;

/**
 * @author by : liyang
 * @Date :2017/11/15 15:56
 */

public class TitleView {

    public static BaseTitleView getTitle(Context context) {
        return new BaseTitleView(context);
    }

}
