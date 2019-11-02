package cn.fek12.evaluation.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import cn.fek12.evaluation.R;


/**
 * Created by liyang on 2017/6/15.
 */

public class DialogUtils {
    private static String mDialogTag = "dialog";
    private static Dialog loadingDialog;

    /**
     * 显示一个自定义的对话框(无背景层)
     * @param view
     */
    public static void showDialog(View view) {
        // 创建自定义样式dialog
        loadingDialog = new Dialog(view.getContext(), R.style.MyDialog);
        loadingDialog.getWindow().setDimAmount(0);
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setContentView(view);// 设置布局
        loadingDialog.show();
    }

    public static void removeDialog(Context context){
        if(loadingDialog != null){
            loadingDialog.dismiss();
        }
    }
}
