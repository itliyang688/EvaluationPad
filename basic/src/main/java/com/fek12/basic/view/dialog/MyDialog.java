package com.fek12.basic.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.fek12.basic.R;


/**
 * Created by ly on 2019/9/7.
 */

public class MyDialog implements Dialog.OnDismissListener {

    private Dialog dialog;
    private Context mContext;

    public MyDialog(Context mContext) {
        this.mContext = mContext;
        dialog = new Dialog(mContext, R.style.monitor_dialog);
        dialog.setOnDismissListener(this);
        showRefreshDialog();
    }

    /***
     * 刷新Dialog
     * @param
     * @return
     */
    public void showRefreshDialog() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.refresh_dialog, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
    }

    private void setScreenBgDarken(float f) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = f;
        lp.dimAmount = f;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    public void hide() {
        try {
            dialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show() {
        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        setScreenBgDarken(1f);
    }
}
