package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.QueryTopicEntity;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
import cn.fek12.evaluation.model.entity.TopicCountEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface IAutonomyEvaluation {
    void initTreeData(Context context,String grade,String semester,String subject,String textbook,  String userId,String type);
    void queryRecordsList(Context context,String userId);
    void queryTopicCount(Context context, String json);
    interface View extends BaseView {
        void loadTopicCountSuc(TopicCountEntity entity);
        void loadRecordsListSuc(RecordsEntitiy entity);
        void loadTreeSuc(TreeDataEntity entity);
        void loadTreeFail();
        void loadTreeEmpty();
        void loadRecordsListEmpty();
        void loadTopicCountEmpty();
    }
}
