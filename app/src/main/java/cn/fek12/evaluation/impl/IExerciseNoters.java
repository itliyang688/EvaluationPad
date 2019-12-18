package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.YearMonthEntity;

public interface IExerciseNoters {
    void getYears(Context context, String userId);
    interface View extends BaseView {
        void loadSuc(YearMonthEntity entity);
        void loadEmpty();
    }
}
