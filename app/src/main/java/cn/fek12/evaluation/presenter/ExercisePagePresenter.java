package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IExerciseNoters;
import cn.fek12.evaluation.impl.IExercisePage;
import cn.fek12.evaluation.model.entity.PracticeListEntity;
import cn.fek12.evaluation.model.entity.YearMonthEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: ExerciseNotesPresenter
 * @Description:
 * @CreateDate: 2019/12/16 14:27
 */
public class ExercisePagePresenter extends BasePresenter<IExercisePage.View>  implements IExercisePage{
    private View infoView;
    public ExercisePagePresenter(@NonNull View view) {
        this.infoView = view;
    }

    @Override
    public void getPracticeList(Context context, String userId, String structId, String date) {
        ApiRetrofit.getInstance().getApiService().getPracticeList(userId,structId,date)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<PracticeListEntity>() {

                    @Override
                    public void onSuccess(PracticeListEntity entry) {
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
}
