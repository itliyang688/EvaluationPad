package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.AuthEntity;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.TaskNumEntity;
import cn.fek12.evaluation.model.entity.UpdateApkEntity;

public interface IMain {
    void uauth(Context context, String userId);
    void chechUpdate(Context context, String versionCode);
    void queryTaskNumByUserId(Context context, String userId);
    interface View extends BaseView {
        void loadSuc(String token);
        void loadFail(String msg);
        void checkUpdateSuc(UpdateApkEntity entity);
        void checkUpdateFail();

        void loadTaskNumSuc(TaskNumEntity entity);
        void loadTaskNumEmpty();
    }
}
