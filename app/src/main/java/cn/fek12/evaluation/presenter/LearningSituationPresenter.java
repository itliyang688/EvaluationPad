package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.ILearningSituation;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.PlanVideoEntity;
import cn.fek12.evaluation.model.entity.PromoteRecommedVideoEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class LearningSituationPresenter extends BasePresenter<ILearningSituation.View> implements ILearningSituation{
    private View infoView;
    public LearningSituationPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void querWeekStudyPlanVideoList(Context context, String userId, String subjectId) {
        ApiRetrofit.getInstance().getApiService().querWeekStudyPlanVideoList(userId,subjectId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<PlanVideoEntity>() {

                    @Override
                    public void onSuccess(PlanVideoEntity entry) {
                        if(entry.getState().equals("0") && entry.getData() != null && entry.getData().size() > 0){
                            infoView.loadPlanVideoSuc(entry);
                        }else{
                            infoView.loadPlanVideoEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadPlanVideoEmpty();
                    }
                });
    }

    @Override
    public void getPromoteRecommedVideo(Context context, String userId, String current, String size) {
        ApiRetrofit.getInstance().getApiService().getPromoteRecommedVideo(userId,current,size)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<PromoteRecommedVideoEntity>() {

                    @Override
                    public void onSuccess(PromoteRecommedVideoEntity entry) {
                        if(entry.getState().equals("0") && entry.getData() != null && entry.getData().getRecords() != null
                                && entry.getData().getRecords().size() > 0){
                            infoView.loadPromoteRecommedVideoSuc(entry);
                        }else{
                            infoView.loadPromoteRecommedVideoEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadPromoteRecommedVideoEmpty();
                    }
                });
    }

    @Override
    public void queryTaskPage(Context context, String userId, String current, String size) {
        ApiRetrofit.getInstance().getApiService().queryTaskPage(userId,null,null,null,null,null,current,size)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<ExaminationEntity>() {

                    @Override
                    public void onSuccess(ExaminationEntity entry) {
                        if(entry.getState().equals("0") && entry.getData() != null
                                && entry.getData().getRecords() != null && entry.getData().getRecords().size() > 0){
                            infoView.loadExaminationSuc(entry);
                        }else{
                            infoView.loadExaminationEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadExaminationEmpty();
                    }
                });
    }
}
