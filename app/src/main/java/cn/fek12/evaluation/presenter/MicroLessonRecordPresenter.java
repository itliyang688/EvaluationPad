package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;
import com.fek12.basic.utils.toast.ToastUtils;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IMicroLessonRecord;
import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CollectionListEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class MicroLessonRecordPresenter extends BasePresenter<IMicroLessonRecord.View> implements IMicroLessonRecord {
    private View infoView;
    public MicroLessonRecordPresenter(@NonNull View view) {
        this.infoView = view;
    }

    @Override
    public void collectionList(Context context, String userId,String subject) {
        ApiRetrofit.getInstance().getApiService().collectionList(userId,subject)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CollectionListEntity>() {

                    @Override
                    public void onSuccess(CollectionListEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadCollectionSuc(entry);
                        }else{
                            infoView.loadCollectionEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadCollectionEmpty();
                    }
                });
    }

    @Override
    public void microLessonList(Context context, String userId, String subject) {
        ApiRetrofit.getInstance().getApiService().hisPlayList(userId,subject)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CollectionListEntity>() {

                    @Override
                    public void onSuccess(CollectionListEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadCollectionSuc(entry);
                        }else{
                            infoView.loadCollectionEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadCollectionEmpty();
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
                            infoView.loadCollectionSuc();
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
