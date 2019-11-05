package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface ITreeView {
    void initTreeData(Context context,String pageType, String grade,String semester,String subject,String textbook,String userId);
    interface View extends BaseView {
        void loadSuc(TreeDataEntity entry);
        void loadFail(String msg);
        void loadEmpty();
    }
}
