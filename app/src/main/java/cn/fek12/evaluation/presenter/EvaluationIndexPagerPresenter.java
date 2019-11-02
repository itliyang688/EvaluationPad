package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IEvaluationIndexPager;
import cn.fek12.evaluation.impl.IEvaluationList;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class EvaluationIndexPagerPresenter extends BasePresenter<IEvaluationIndexPager.View> implements IEvaluationIndexPager{
    private View infoView;
    public EvaluationIndexPagerPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void getIndexPaper(Context context, String grade, String semester, String subject, String textbook, String ptype, String userId) {
        ApiRetrofit.getInstance().getApiService().getIndexPaper(grade,semester,subject,textbook,ptype,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<AssessmentIndexPaperResp>() {

                    @Override
                    public void onSuccess(AssessmentIndexPaperResp entry) {
                        if(entry.getState().equals("0")){
                            infoView.loginSuc(entry);
                        }else{
                            infoView.loginEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loginFail(msg);
                    }
                });
    }
}
