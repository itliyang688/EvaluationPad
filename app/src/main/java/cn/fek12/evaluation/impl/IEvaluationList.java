package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import java.util.List;

import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;

public interface IEvaluationList {
    void getPaperTypeList(Context context);
    void queryGradeDictionaryList(Context context);
    void querySubjectList(Context context,String grade);
    void queryTextBookList(Context context,String grade,String subject);
    void querySemesterList(Context context,String grade,String subject,String textbook);
    interface View extends BaseView {
        void loadGradeDictionarySuc(GradeDictionaryListEntity entry);
        void loadSubjectSuc(SubjectEntity entry);
        void loadTextBookSuc(TextbookEntity entry);
        void loadSemesterSuc(SemesterEntity entry);
        void loadPaperTypeSuc(PaperTypeListResp entry);
        void loadFail(String msg);
        void loadDictionaryEmpty();
        void loadPaperTypeEmpty();
    }
}
