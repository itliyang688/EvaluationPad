package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

public interface IConquered {
    void promoteDetail(Context context, String paperResultId );
    interface View extends BaseView {
        void loadSuc(ConqueredEntity entry);
        void loadEmpty();
    }
}
