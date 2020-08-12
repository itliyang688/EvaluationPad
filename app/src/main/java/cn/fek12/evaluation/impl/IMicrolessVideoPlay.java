package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;

public interface IMicrolessVideoPlay {
    void addOrUpdateVideoPlayCount(Context context,String playLength,String userId,String videoId);
    void collection(Context context, String videoId, String status, String userId);
    void schedule(Context context,String playScheduleTime,String subjectCategoryId,String videoId,String userId);
    interface View extends BaseView {
        void loadCollectionSuc(CollectionEntity entry);
        void loadCollectionFail();
    }
}
