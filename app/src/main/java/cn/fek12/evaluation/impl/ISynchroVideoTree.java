package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;

public interface ISynchroVideoTree {
    void queryGradeDictionaryList(Context context,String secType);
    void initTreeData(Context context, String pageType, String grade, String semester, String subject, String textbook, String userId);
    void querySubjectList(Context context, String grade);
    void queryTextBookList(Context context, String grade, String subject);
    void querySemesterList(Context context, String grade, String subject, String textbook);
    void queryPaperList(Context context, String gradeId, String secId, String subId, String versionId, String knowledgeId, String userId, String current, String size);
    interface View extends BaseView {
        void loadTreeSuc(TreeDataEntity entry);
        void loadVideoTreeListSuc(MicrolessonVideoEntity entry);
        void loadGradeDictionarySuc(GradeDictionaryListEntity entry);
        void loadSubjectSuc(SubjectEntity entry);
        void loadTextBookSuc(TextbookEntity entry);
        void loadSemesterSuc(SemesterEntity entry);
        void loadFail(String msg);
        void loadTreeEmpty();
        void loadDictionaryEmpty();
        void loadVideoTreeListEmpty();
    }
}
