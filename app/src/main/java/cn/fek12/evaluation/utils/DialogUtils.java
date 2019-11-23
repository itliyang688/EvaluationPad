package cn.fek12.evaluation.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.view.MonthPager;

import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;


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

    public interface OnSelectSubjectItmeListener {
        void onSelectItme(int pos);
    }
    /***
     *选择学科提示框
     */
    public static Dialog subjectSelectDialog(Context mContext, List<ChildSectionEntity> mList, final OnSelectSubjectItmeListener selectItmeListener) {

        final Dialog dialog = new Dialog(mContext, R.style.dialog_anim);
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_select_dialog, null);
        LinearLayout llContain = view.findViewById(R.id.llContain);
        for(int i = 0; i < mList.size(); i++){
            View viewItem = LayoutInflater.from(mContext).inflate(R.layout.subject_itme, null);
            TextView tvName = viewItem.findViewById(R.id.tvName);
            tvName.setText(mList.get(i).getName());
            int finalI = i;
            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(selectItmeListener != null){
                        //tvName.setBackgroundColor(mContext.getResources().getColor(R.color.color_FEAC2C));
                        tvName.setTextColor(mContext.getResources().getColor(R.color.white));
                        selectItmeListener.onSelectItme(finalI);
                        dialog.dismiss();
                        /*try {
                            Thread.sleep(1000);
                            dialog.dismiss();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            });
            llContain.addView(viewItem);

        }
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
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
