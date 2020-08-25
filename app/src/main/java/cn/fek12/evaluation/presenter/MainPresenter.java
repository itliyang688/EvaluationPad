package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.ICommonVideo;
import cn.fek12.evaluation.impl.IMain;
import cn.fek12.evaluation.model.entity.AuthEntity;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.TaskNumEntity;
import cn.fek12.evaluation.model.entity.UpdateApkEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class MainPresenter extends BasePresenter<IMain.View> implements IMain {
    private View infoView;
    public MainPresenter(@NonNull View view) {
        this.infoView = view;
    }

    @Override
    public void uauth(Context context, String userId) {
        ApiRetrofit.getInstance().getApiService().uauth(userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<AuthEntity>() {
                    @Override
                    public void onSuccess(AuthEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadSuc(entry.getData());
                        }else{
                            infoView.loadFail("");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }

    @Override
    public void chechUpdate(Context context, String versionCode) {
        ApiRetrofit.getInstance().getApiService().checkPadVersionByCode(versionCode)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<UpdateApkEntity>() {
                    @Override
                    public void onSuccess(UpdateApkEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.checkUpdateSuc(entry);
                        }else{
                            infoView.checkUpdateFail();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.checkUpdateFail();
                    }
                });
    }

    @Override
    public void queryTaskNumByUserId(Context context, String userId) {
        ApiRetrofit.getInstance().getApiService().getUntreatedTaskNumByUserId(userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TaskNumEntity>() {
                    @Override
                    public void onSuccess(TaskNumEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadTaskNumSuc(entry);
                        }else{
                            infoView.loadTaskNumEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadTaskNumEmpty();
                    }
                });
    }
}
