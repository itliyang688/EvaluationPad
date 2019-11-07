package cn.fek12.evaluation.utils;

import android.os.Build;
import android.widget.PopupWindow;

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

    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR);
    }
}
