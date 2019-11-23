package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;

public interface ISpeciaVideoPlay {
    void schedule(Context context, String cacheKey,String structLayKey, String playScheduleTime, String type, String videoId, String userId);
    void collection(Context context, String cacheKey, String type, String videoId,String isCollection, String userId);
    interface View extends BaseView {
        //void loadScheduleSuc(CommonEntity entry);
        //void loadScheduleEmpty();
        void loadCollectionSuc(CommonEntity entry);
        void loadCollectionFail();
    }
}
