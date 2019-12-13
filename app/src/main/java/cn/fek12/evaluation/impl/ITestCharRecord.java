package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.model.entity.TestRecordEntity;

public interface ITestCharRecord {
    void requestData(Context context,String subject, String userId);

    interface View extends BaseView {
        void loadSuc(TestRecordEntity entity);
        void loadEmpty();
    }
}
