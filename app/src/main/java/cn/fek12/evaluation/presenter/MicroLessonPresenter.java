package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IEvaluationList;
import cn.fek12.evaluation.impl.IMicorLesson;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
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
public class MicroLessonPresenter extends BasePresenter<IMicorLesson.View> implements IMicorLesson{
    private View infoView;
    public MicroLessonPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }


    /**请求年级*/
    @Override
    public void queryGradeDictionaryList(Context context,String secType) {
        ApiRetrofit.getInstance().getApiService().queryGradeDictionaryList(secType)
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
                        infoView.loadDictionaryEmpty();
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
                        infoView.loadSubjectSuc(entry);
                    }

                    @Override
                    public void onError(String msg) {

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
                        infoView.loadTextBookSuc(entry);
                    }

                    @Override
                    public void onError(String msg) {
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
                        infoView.loadSemesterSuc(entry);
                    }

                    @Override
                    public void onError(String msg) {
                    }
                });
    }
}
