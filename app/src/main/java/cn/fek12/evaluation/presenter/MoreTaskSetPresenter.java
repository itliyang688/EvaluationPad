package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.ILearningSituation;
import cn.fek12.evaluation.impl.IMoreTaskSet;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.PlanVideoEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class MoreTaskSetPresenter extends BasePresenter<IMoreTaskSet.View> implements IMoreTaskSet{
    private View infoView;
    public MoreTaskSetPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void queryTaskPage(Context context, String userId, String subjectId, String taskType, String startDate, String endDate,String status, String current, String size) {
        ApiRetrofit.getInstance().getApiService().queryTaskPage(userId,subjectId,taskType,startDate,endDate,status,current,size)
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

    @Override
    public void getStuSubjectByUserId(Context context, String userId) {
        ApiRetrofit.getInstance().getApiService().getStuSubjectByUserId(userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<SubjectModel>() {

                    @Override
                    public void onSuccess(SubjectModel entry) {
                        if(entry.getState().equals("0") && entry.getData() != null
                                 && entry.getData().size() > 0){
                            infoView.loadSubjectSuc(entry);
                        }else{
                            infoView.loadSubjectEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadSubjectEmpty();
                    }
                });
    }
}
