package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.TrainListEntity;

public interface ITrainExercisePage {
    void getPracticeList(Context context, String userId,  String date);
    interface View extends BaseView {
        void loadSuc(TrainListEntity entity);
        void loadEmpty();
    }
}
