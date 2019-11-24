package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.ICurriculemRecord;
import cn.fek12.evaluation.model.entity.CurriculumEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class CurriculumRecordPresenter extends BasePresenter<ICurriculemRecord.View> implements ICurriculemRecord {
    private View infoView;
    public CurriculumRecordPresenter(@NonNull View view,Context activity) {
        this.infoView = view;
    }

    @Override
    public void courseRecord(Context context, String beginDate, String endDate, String subject, String userId, String currentPage) {
        ApiRetrofit.getInstance().getApiService().courseRecord(beginDate,endDate,subject,userId,currentPage)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CurriculumEntity>() {

                    @Override
                    public void onSuccess(CurriculumEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loginSuc(entry);
                        }else{
                            infoView.loginEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loginEmpty();
                    }
                });
    }
}
