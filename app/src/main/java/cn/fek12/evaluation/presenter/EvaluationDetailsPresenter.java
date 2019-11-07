package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IEvaluationDetails;
import cn.fek12.evaluation.model.entity.EvaluationListEntity;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class EvaluationDetailsPresenter extends BasePresenter<IEvaluationDetails.View> implements IEvaluationDetails{
    private View infoView;
    public EvaluationDetailsPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void initTreeData(Context context, String pageType, String grade, String semester, String subject, String textbook, String userId) {
        ApiRetrofit.getInstance().getApiService().queryTreeData(pageType,grade,semester,subject,textbook,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TreeDataEntity>() {

                    @Override
                    public void onSuccess(TreeDataEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadTreeSuc(entry);
                        }else{
                            infoView.loadTreeEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadTreeEmpty();
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

    @Override
    public void queryPaperList(Context context, String grade, String subject, String textbook, String semester, String ptype, String userId, String userType, String currentPage,String knowledge,String paperListType) {
        ApiRetrofit.getInstance().getApiService().queryPaperList(grade,semester,subject,textbook,userId,ptype,userType,knowledge,paperListType,currentPage)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<EvaluationListEntity>() {

                    @Override
                    public void onSuccess(EvaluationListEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadPaperListSuc(entry);
                        }else{
                            infoView.loadPaperTypeEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadPaperTypeEmpty();
                    }
                });
    }
}
