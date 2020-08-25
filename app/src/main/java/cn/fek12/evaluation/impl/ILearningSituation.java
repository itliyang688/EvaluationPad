package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.PlanVideoEntity;
import cn.fek12.evaluation.model.entity.PromoteRecommedVideoEntity;

public interface ILearningSituation {
    void querWeekStudyPlanVideoList(Context context, String userId, String subjectId);
    void getPromoteRecommedVideo(Context context, String userId, String current,String size);
    void queryTaskPage(Context context, String userId, String current,String size);
    interface View extends BaseView {
        void loadPlanVideoSuc(PlanVideoEntity entry);
        void loadPlanVideoEmpty();

        void loadExaminationSuc(ExaminationEntity entry);
        void loadExaminationEmpty();

        void loadPromoteRecommedVideoSuc(PromoteRecommedVideoEntity entry);
        void loadPromoteRecommedVideoEmpty();
    }
}
