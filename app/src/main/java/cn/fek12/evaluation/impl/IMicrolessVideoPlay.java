package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;

public interface IMicrolessVideoPlay {
    void schedule(Context context);
    void collection(Context context, String videoId, String status, String userId);
    interface View extends BaseView {
        void loadCollectionSuc(CollectionEntity entry);
        void loadCollectionFail();
    }
}
