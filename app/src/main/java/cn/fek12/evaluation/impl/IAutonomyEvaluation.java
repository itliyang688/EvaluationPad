package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CheckPaperNameEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
import cn.fek12.evaluation.model.entity.TopicCountEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface IAutonomyEvaluation {
    void initTreeData(Context context,String grade,String semester,String subject,String textbook,  String userId,String type);
    void queryRecordsList(Context context,String userId);
    void queryTopicCount(Context context, String json);
    void saveStudentPaper(Context context, String json);
    void checkPaperName(Context context, String paperName,String userId);
    interface View extends BaseView {
        void loadTopicCountSuc(TopicCountEntity entity);
        void loadRecordsListSuc(RecordsEntitiy entity);
        void loadPaperSuc(CommonEntity entity);
        void loadCheckPaperNameSuc(CheckPaperNameEntity entity);
        void loadTreeSuc(TreeDataEntity entity);
        void loadTreeFail();
        void loadTreeEmpty();
        void loadRecordsListEmpty();
        void loadPaperEmpty();
        void loadCheckPaperNameEmpty();
        void loadTopicCountEmpty();
    }
}
