package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface IAutonomyEvaluation {
    void initTreeData(Context context,String grade,String semester,String subject,String textbook,  String userId,String type);
    interface View extends BaseView {
        void loadTreeSuc(TreeDataEntity entity);
        void loadTreeFail();
        void loadTreeEmpty();
    }
}
