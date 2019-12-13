package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.ApiServer;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.ITestCharRecord;
import cn.fek12.evaluation.model.entity.TestRecordEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: TestCharRecordPresenter
 * @Description:
 * @CreateDate: 2019/12/12 16:54
 */
public class TestCharRecordPresenter extends BasePresenter<ITestCharRecord.View> implements ITestCharRecord{
    private View infoView;
    public TestCharRecordPresenter(@NonNull View view) {
        this.infoView = view;
    }


    @Override
    public void requestData(Context context, String subject, String userId) {
        ApiRetrofit.getInstance().getApiService().testRecordsList(subject,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TestRecordEntity>() {

                    @Override
                    public void onSuccess(TestRecordEntity entry) {
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
