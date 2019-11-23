package cn.fek12.evaluation.impl;

import android.content.Context;

import com.fek12.basic.base.BaseView;

import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

public interface IMicroLessonPage {
    void queryAllVideo(Context context, String grade, String semester, String subject, String textbook, String userId);
    void querySpecialVideo(Context context, String grade, String semester, String subject, String textbook, String userId);
    void querySynchroVideo(Context context, String grade, String semester, String subject, String textbook, String userId);
    interface View extends BaseView {
        void loadVideoSuc(MicroLessonEnetity entry);
        void loadVideoEmpty();
    }
}
