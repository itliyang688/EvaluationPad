package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IPresentation;
import cn.fek12.evaluation.model.entity.AWeekEntity;
import cn.fek12.evaluation.model.entity.EarlierEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class PresentationPresenter extends BasePresenter<IPresentation.View> implements IPresentation{
    private View infoView;
    public PresentationPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void queryAWeek(Context context, String grade, String semester, String subject, String textbook, String userId, String userType) {
        ApiRetrofit.getInstance().getApiService().queryAWeek(grade,semester,subject,textbook,userId,userType)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<AWeekEntity>() {

                    @Override
                    public void onSuccess(AWeekEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadAWeekSuc(entry);
                        }else{
                            infoView.loadAWeekEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadAWeekEmpty();
                    }
                });
    }

    @Override
    public void queryEarlier(Context context, String grade, String semester, String subject, String textbook, String userId, String userType, String currentPage, String pageSize) {
        ApiRetrofit.getInstance().getApiService().queryEarlier(grade,semester,subject,textbook,userId,userType,currentPage,pageSize)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<EarlierEntity>() {

                    @Override
                    public void onSuccess(EarlierEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadEarlierSuc(entry);
                        }else{
                            infoView.loadEarlierEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadEarlierEmpty();
                    }
                });
    }
}
