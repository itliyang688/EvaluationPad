package cn.fek12.evaluation.presenter;

import android.content.Context;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IAutonomyEvaluation;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class AutonomyEvaluationPresenter extends BasePresenter<IAutonomyEvaluation.View> implements IAutonomyEvaluation{
    private View infoView;
    public AutonomyEvaluationPresenter(View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void initTreeData(Context context,String grade,String semester,String subject,String textbook,  String userId,String type) {
        ApiRetrofit.getInstance().getApiService().queryTreeData(type,grade,semester,subject,textbook,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TreeDataEntity>() {

                    @Override
                    public void onSuccess(TreeDataEntity entity) {
                        if(entity.getState().equals("0")){
                            infoView.loadTreeSuc(entity);
                        }else{
                            infoView.loadTreeEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadTreeFail();
                    }
                });
    }
}
