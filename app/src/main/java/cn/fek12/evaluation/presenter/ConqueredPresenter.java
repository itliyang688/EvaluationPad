package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IConquered;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class ConqueredPresenter extends BasePresenter<IConquered.View> implements IConquered {
    private View infoView;
    public ConqueredPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void promoteDetail(Context context, String paperResultId) {
        ApiRetrofit.getInstance().getApiService().promoteDetail(paperResultId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<ConqueredEntity>() {

                    @Override
                    public void onSuccess(ConqueredEntity entry) {
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
