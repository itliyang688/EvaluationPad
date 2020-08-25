package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IExercisePage;
import cn.fek12.evaluation.impl.ITrainExercisePage;
import cn.fek12.evaluation.model.entity.PracticeListEntity;
import cn.fek12.evaluation.model.entity.TrainListEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: ExerciseNotesPresenter
 * @Description:
 * @CreateDate: 2019/12/16 14:27
 */
public class TrainExercisePagePresenter extends BasePresenter<ITrainExercisePage.View>  implements ITrainExercisePage{
    private View infoView;
    public TrainExercisePagePresenter(@NonNull View view) {
        this.infoView = view;
    }

    @Override
    public void getPracticeList(Context context, String userId, String date) {
        ApiRetrofit.getInstance().getApiService().getTrainList(userId,date)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TrainListEntity>() {

                    @Override
                    public void onSuccess(TrainListEntity entry) {
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
