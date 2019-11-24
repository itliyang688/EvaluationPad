package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CurriculumEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

public interface ICurriculemRecord {
    void courseRecord(Context context,String beginDate, String endDate,String subject,String userId,String currentPage);
    interface View extends BaseView {
        void loginSuc(CurriculumEntity entry);
        void loginEmpty();
    }
}
