package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

public interface IEvaluationIndexPager {
    void getIndexPaper(Context context, String grade,String semester,String subject,String textbook,String ptype,String userId);
    interface View extends BaseView {
        void loginSuc(AssessmentIndexPaperResp entry);
        void loginEmpty();
        void loginFail(String msg);
    }
}
