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
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;

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
    public void getPaperTypeList(Context context) {
        ApiRetrofit.getInstance().getApiService().getPaperTypeList()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<PaperTypeListResp>() {

                    @Override
                    public void onSuccess(PaperTypeListResp entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadPaperTypeSuc(entry);
                        }else{
                            infoView.loadPaperTypeEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }

    /**请求年级*/
    @Override
    public void queryGradeDictionaryList(Context context) {
        ApiRetrofit.getInstance().getApiService().queryGradeDictionaryList()
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<GradeDictionaryListEntity>() {

                    @Override
                    public void onSuccess(GradeDictionaryListEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadGradeDictionarySuc(entry);
                        }else{
                            infoView.loadDictionaryEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }

    /**请求学科*/
    @Override
    public void querySubjectList(Context context, String grade) {
        ApiRetrofit.getInstance().getApiService().querySubject(grade)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<SubjectEntity>() {

                    @Override
                    public void onSuccess(SubjectEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadSubjectSuc(entry);
                        }else{
                            infoView.loadDictionaryEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }

    /**请求版本*/
    @Override
    public void queryTextBookList(Context context, String grade, String subject) {
        ApiRetrofit.getInstance().getApiService().queryTextbook(grade,subject)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TextbookEntity>() {

                    @Override
                    public void onSuccess(TextbookEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadTextBookSuc(entry);
                        }else{
                            infoView.loadDictionaryEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }
    /**请求教材*/
    @Override
    public void querySemesterList(Context context, String grade, String subject, String textbook) {
        ApiRetrofit.getInstance().getApiService().querySemester(grade,subject,textbook)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<SemesterEntity>() {

                    @Override
                    public void onSuccess(SemesterEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadSemesterSuc(entry);
                        }else{
                            infoView.loadDictionaryEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }
}
