package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.AWeekEntity;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.EarlierEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

public interface IPresentation {
    void queryAWeek(Context context, String grade, String semester, String subject, String textbook, String userId, String userType);
    void queryEarlier(Context context, String grade, String semester, String subject, String textbook, String userId, String userType, String currentPage, String pageSize);
    interface View extends BaseView {
        void loadAWeekSuc(AWeekEntity entry);
        void loadEarlierSuc(EarlierEntity entry);
        void loadAWeekFail(String msg);
        void loadEarlierFail(String msg);
        void loadAWeekEmpty();
        void loadDictionaryEmpty();
        void loadEarlierEmpty();
    }
}
