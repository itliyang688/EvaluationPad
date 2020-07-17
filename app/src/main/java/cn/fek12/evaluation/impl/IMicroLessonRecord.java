package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CollectionListEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;

public interface IMicroLessonRecord {
    void collectionList(Context context, String userId,String subject);
    void microLessonList(Context context, String userId,String subject);
    void collection(Context context, String videoId,String isCollection, String userId);
    interface View extends BaseView {
        void loadCollectionSuc(CollectionListEntity entry);
        void loadCollectionEmpty();
        void loadCollectionSuc();
        void loadCollectionFail();
    }
}
