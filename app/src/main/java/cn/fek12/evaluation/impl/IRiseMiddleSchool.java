package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;

public interface IRiseMiddleSchool {
    void initTreeData(Context context, String pageType, String grade, String semester, String subject, String textbook, String userId);
    void queryVideoList(Context context,String coursePackType, String knowledgePointId,String userId,String current,String size);
    interface View extends BaseView {
        void loadTreeSuc(TreeDataEntity entry);
        void loadTreeEmpty();
        void loadVideoSuc(MicrolessonVideoEntity entry);
        void loadFail(String msg);
        void loadVideoEmpty();
    }
}
