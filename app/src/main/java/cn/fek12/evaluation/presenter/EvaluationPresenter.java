package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class EvaluationPresenter extends BasePresenter<IEvaluation.View> implements IEvaluation{
    private IEvaluation.View infoView;
    public EvaluationPresenter(@NonNull IEvaluation.View view, Context activity) {
        this.infoView = view;
    }
    @Override
    public void initEvaluation(Context context,String userId,String grade) {
        ApiRetrofit.getInstance().getApiService().queryEvaluation(userId,grade)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<HomeEvaluationDeta>() {

                    @Override
                    public void onSuccess(HomeEvaluationDeta entry) {
                        if(entry.getState().equals("0")){
                            infoView.loginSuc(entry);
                        }else{
                            infoView.loginFail(entry.getMessage());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loginFail(msg);
                    }
                });
    }
}
