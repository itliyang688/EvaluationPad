package cn.fek12.evaluation.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.fek12.evaluation.R;


/**
 * Created by liyang on 2017/6/15.
 */

public class DialogUtils {
    private static String mDialogTag = "dialog";
    private static Dialog loadingDialog;

    /***
     *答题提醒对话框
     */
    public static Dialog showAnswerRemind(Context mContext,final View.OnClickListener cl) {
        final Dialog dialog = new Dialog(mContext, R.style.dialog_anim);
        View view = LayoutInflater.from(mContext).inflate(R.layout.answer_remind_dialog, null);
        dialog.setContentView(view);
        view.findViewById(R.id.tvAnswer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cl.onClick(v);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

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
