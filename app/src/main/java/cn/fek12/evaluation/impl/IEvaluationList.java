package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import java.util.List;

import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;

public interface IEvaluationList {
    void queryEvaluationList(Context context);
    void getPaperTypeList(Context context);
    interface View extends BaseView {
        void loadDictionarySuc(DictionaryListResp entry);
        void loadPaperTypeSuc(PaperTypeListResp entry);
        void loadFail(String msg);
    }
}
