package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IMicroLessonPage;
import cn.fek12.evaluation.impl.ITreeView;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class MicroLessonPagePresenter extends BasePresenter<IMicroLessonPage.View> implements IMicroLessonPage {
    private View infoView;
    public MicroLessonPagePresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void queryAllVideo(Context context, String grade, String semester, String subject, String textbook, String userId) {
        ApiRetrofit.getInstance().getApiService().queryVideoList(grade,semester,subject,textbook,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<MicroLessonEnetity>() {

                    @Override
                    public void onSuccess(MicroLessonEnetity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadVideoSuc(entry);
                        }else{
                            infoView.loadVideoEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadVideoEmpty();
                    }
                });
    }

    @Override
    public void querySpecialVideo(Context context, String grade, String semester, String subject, String textbook, String userId) {
        ApiRetrofit.getInstance().getApiService().querySpecialVideo(grade,semester,subject,textbook,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<MicroLessonEnetity>() {

                    @Override
                    public void onSuccess(MicroLessonEnetity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadVideoSuc(entry);
                        }else{
                            infoView.loadVideoEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadVideoEmpty();
                    }
                });
    }

    @Override
    public void querySynchroVideo(Context context, String grade, String semester, String subject, String textbook, String userId) {
        ApiRetrofit.getInstance().getApiService().querySynchroVideo(grade,semester,subject,textbook,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<MicroLessonEnetity>() {

                    @Override
                    public void onSuccess(MicroLessonEnetity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadVideoSuc(entry);
                        }else{
                            infoView.loadVideoEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadVideoEmpty();
                    }
                });
    }
}
