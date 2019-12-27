package cn.fek12.evaluation.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.ISpeciaVideoPlay;
import cn.fek12.evaluation.model.entity.CommonEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class SpeciaVideoPlayPresenter extends BasePresenter<ISpeciaVideoPlay.View> implements ISpeciaVideoPlay {
    private View infoView;
    public SpeciaVideoPlayPresenter(@NonNull View view) {
        this.infoView = view;
    }
    @Override
    public void schedule(Context context, String cacheKey,String structLayKey, String playScheduleTime, String type, String videoId, String userId,int typePage,String isEnd) {
        if(typePage == 0){
            ApiRetrofit.getInstance().getApiService().schedule(cacheKey,structLayKey,playScheduleTime,type,videoId,userId,isEnd)
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
                            Log.e("",msg);
                        }
                    });
        }else{
            ApiRetrofit.getInstance().getApiService().addCourseRecord(playScheduleTime,videoId,userId)
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
                        }
                    });
        }
    }

    @Override
    public void collection(Context context, String cacheKey, String type, String videoId,String isCollection, String userId) {
        ApiRetrofit.getInstance().getApiService().collection(cacheKey,type,videoId,isCollection,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CommonEntity>() {

                    @Override
                    public void onSuccess(CommonEntity entry) {
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
