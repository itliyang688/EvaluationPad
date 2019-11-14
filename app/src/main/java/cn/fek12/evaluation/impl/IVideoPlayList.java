package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;

public interface IVideoPlayList {
    void videoList(Context context, String subjectCategoryId,String userId);
    interface View extends BaseView {
        void loadSuc(RelevantVideoListEntity entry);
        void loadEmpty();
    }
}
