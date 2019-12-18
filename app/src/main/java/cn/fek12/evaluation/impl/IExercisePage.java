package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.PracticeListEntity;
import cn.fek12.evaluation.model.entity.YearMonthEntity;

public interface IExercisePage {
    void getPracticeList(Context context, String userId,String structId,String date);
    interface View extends BaseView {
        void loadSuc(PracticeListEntity entity);
        void loadEmpty();
    }
}
