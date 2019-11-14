package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.EvaluationListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface IAutonomyEvaluationList {
    void initTreeData(Context context, String pageType, String grade, String semester, String subject, String textbook, String userId);
    void querySubjectList(Context context, String grade);
    void queryTextBookList(Context context, String grade, String subject);
    void querySemesterList(Context context, String grade, String subject, String textbook);
    void queryPaperList(Context context, String grade, String subject, String textbook, String semester,  String userId, String currentPage);
    interface View extends BaseView {
        void loadTreeSuc(TreeDataEntity entry);
        void loadPaperListSuc(EvaluationListEntity entry);
        void loadSubjectSuc(SubjectEntity entry);
        void loadTextBookSuc(TextbookEntity entry);
        void loadSemesterSuc(SemesterEntity entry);
        void loadFail(String msg);
        void loadTreeEmpty();
        void loadDictionaryEmpty();
        void loadPaperTypeEmpty();
    }
}
