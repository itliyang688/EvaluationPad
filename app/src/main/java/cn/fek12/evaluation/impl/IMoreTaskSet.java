package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;

public interface IMoreTaskSet {
    void queryTaskPage(Context context, String userId, String subjectId, String taskType, String startDate, String endDate, String status, String current, String size);

    void getStuSubjectByUserId(Context context, String userId);

    interface View extends BaseView {
        void loadExaminationSuc(ExaminationEntity entry);

        void loadExaminationEmpty();

        void loadSubjectSuc(SubjectModel entry);

        void loadSubjectEmpty();
    }
}
