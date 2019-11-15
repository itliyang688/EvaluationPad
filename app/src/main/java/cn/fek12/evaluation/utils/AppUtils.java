package cn.fek12.evaluation.utils;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fek12.basic.utils.baseUtils.BaseAppUtils;

import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.utils
 * @ClassName: AppUtils
 * @Description:
 * @CreateDate: 2019/11/2 17:41
 */
public class AppUtils {

    public static void fitPopupWindowOverStatusBar(PopupWindow mPopupWindow, boolean needFullScreen) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                Field mLayoutInScreen = PopupWindow.class.getDeclaredField("mLayoutInScreen");
                mLayoutInScreen.setAccessible(needFullScreen);
                mLayoutInScreen.set(mPopupWindow, needFullScreen);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void titleTop(ViewGroup mRootView,Context context){
        ViewGroup.LayoutParams params = mRootView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        int top = BaseAppUtils.getStatusBarHeight(context);//获取状态栏高度
        //设置margin
        marginParams.setMargins(0, top, 0, 0);
        mRootView.setLayoutParams(marginParams);
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR);
    }
}
