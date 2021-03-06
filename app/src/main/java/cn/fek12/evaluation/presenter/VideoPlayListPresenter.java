package cn.fek12.evaluation.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IConquered;
import cn.fek12.evaluation.impl.IVideoPlayList;
import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class VideoPlayListPresenter extends BasePresenter<IVideoPlayList.View> implements IVideoPlayList {
    private View infoView;
    public VideoPlayListPresenter(@NonNull View view) {
        this.infoView = view;
    }

    @Override
    public void videoList(Context context, String subjectCategoryId, String userId) {
        ApiRetrofit.getInstance().getApiService().videoList(subjectCategoryId,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<RelevantVideoListEntity>() {

                    @Override
                    public void onSuccess(RelevantVideoListEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadSuc(entry);
                        }else{
                            infoView.loadEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadEmpty();
                    }
                });
    }

    @Override
    public void schedule(Context context, String playScheduleTime, String subjectCategoryId,String videoId, String userId) {
        ApiRetrofit.getInstance().getApiService().addCourseRecord(playScheduleTime,subjectCategoryId,videoId,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CommonEntity>() {

                    @Override
                    public void onSuccess(CommonEntity entry) {
                        if(entry.getState().equals("0")){
                        }else{
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        Log.e("onError",msg);
                    }
                });
    }

    @Override
    public void collection(Context context, String videoId, String status, String userId) {
        ApiRetrofit.getInstance().getApiService().addOrCancelVideoCollection(videoId,status,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CollectionEntity>() {

                    @Override
                    public void onSuccess(CollectionEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadCollectionSuc(entry);
                        }else{
                            infoView.loadCollectionFail();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadCollectionFail();
                    }
                });
    }
}
