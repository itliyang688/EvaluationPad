package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;

public interface IVideoPlayList {
    void videoList(Context context, String subjectCategoryId,String userId);
    void schedule(Context context,String playScheduleTime,String subjectCategoryId,String videoId,String userId);
    void collection(Context context, String videoId, String status, String userId);
    interface View extends BaseView {
        void loadSuc(RelevantVideoListEntity entry);
        //void loadScheduleSuc(CommonEntity entry);
        void loadEmpty();
        //void loadScheduleEmpty();
        void loadCollectionSuc(CollectionEntity entry);
        void loadCollectionFail();
    }
}
