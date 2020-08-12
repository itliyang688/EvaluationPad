package cn.fek12.evaluation.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IMicrolessVideoPlay;
import cn.fek12.evaluation.impl.ISpeciaVideoPlay;
import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class MicrolessonVideoPlayPresenter extends BasePresenter<IMicrolessVideoPlay.View> implements IMicrolessVideoPlay {
    private View infoView;
    public MicrolessonVideoPlayPresenter(@NonNull View view) {
        this.infoView = view;
    }
    @Override
    public void addOrUpdateVideoPlayCount(Context context,String playLength,String userId,String videoId) {
        ApiRetrofit.getInstance().getApiService().addOrUpdateVideoPlayCount(playLength,userId,videoId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CollectionEntity>() {

                    @Override
                    public void onSuccess(CollectionEntity entry) {

                        if (entry != null){
                            PrefUtilsData.setIsPlayCountRefresh(true);
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        Log.d("onError", "onError: "+msg);
                    }
                });
    }

    @Override
    public void collection(Context context, String videoId,String isCollection, String userId) {
        ApiRetrofit.getInstance().getApiService().addOrCancelVideoCollection(videoId,isCollection,userId)
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

}
