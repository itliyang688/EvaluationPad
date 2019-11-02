package cn.fek12.evaluation.utils;

import android.content.Context;
import android.view.View;

import cn.fek12.evaluation.R;


/**
 * Created by drake on 2017/6/23.
 */

public class LoadViewUtils {
    public static View getLoadingView(Context context)
    {

        View view = View.inflate(context, R.layout.load, null);
        return view;
    }
}
