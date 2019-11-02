package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import java.util.List;

import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.impl.IEvaluationList;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class EvaluationListPresenter extends BasePresenter<IEvaluationList.View> implements IEvaluationList{
    private View infoView;
    public EvaluationListPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void queryEvaluationList(Context context) {
        ApiRetrofit.getInstance().getApiService().getDictionaryList()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<DictionaryListResp>() {

                    @Override
                    public void onSuccess(DictionaryListResp entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadDictionarySuc(entry);
                        }else{
                            infoView.loadFail(entry.getMessage());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }

    @Override
    public void getPaperTypeList(Context context) {
        ApiRetrofit.getInstance().getApiService().getPaperTypeList()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<PaperTypeListResp>() {

                    @Override
                    public void onSuccess(PaperTypeListResp entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadPaperTypeSuc(entry);
                        }else{
                            infoView.loadFail(entry.getMessage());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }
}
