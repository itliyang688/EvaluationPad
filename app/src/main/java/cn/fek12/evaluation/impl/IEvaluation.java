package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

public interface IEvaluation {
    void initEvaluation(Context context,String userId);
    interface View extends BaseView {
        void loginSuc(HomeEvaluationDeta entry);
        void loginFail(String msg);
        void showBaner();
    }
}
