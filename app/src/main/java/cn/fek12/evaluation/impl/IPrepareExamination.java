package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface IPrepareExamination {
    void initTreeData(Context context, String subId);
    void querySubjectList(Context context, String grade);
    void queryPaperList(Context context, String coursePackType, String knowledgePointId, String userId, String current, String size);
    interface View extends BaseView {
        void loadTreeSuc(TreeDataEntity entry);
        void loadTreeEmpty();

        void loadSubjectSuc(SubjectModel entry);
        void loadVideoListSuc(MicrolessonVideoEntity entry);
        void loadVideoListEmpty();
        void loadFail(String msg);
        void loadDictionaryEmpty();

    }
}
