package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.TaskNumEntity;
import cn.fek12.evaluation.model.entity.UpdateApkEntity;

public interface IRemediaWeb {
    void uauth(Context context, String userId);
    interface View extends BaseView {
        void loadSuc(String token);
        void loadFail(String msg);
    }
}
