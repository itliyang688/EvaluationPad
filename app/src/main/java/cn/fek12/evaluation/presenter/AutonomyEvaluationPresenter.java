package cn.fek12.evaluation.presenter;

import android.content.Context;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IAutonomyEvaluation;
import cn.fek12.evaluation.model.entity.CheckPaperNameEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperIdEntity;
import cn.fek12.evaluation.model.entity.QueryTopicEntity;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
import cn.fek12.evaluation.model.entity.TopicCountEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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

    @Override
    public void queryRecordsList(Context context, String userId) {
        ApiRetrofit.getInstance().getApiService().queryRecordsList(userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<RecordsEntitiy>() {

                    @Override
                    public void onSuccess(RecordsEntitiy entity) {
                        if(entity.getState().equals("0")){
                            infoView.loadRecordsListSuc(entity);
                        }else{
                            infoView.loadRecordsListEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadRecordsListEmpty();
                    }
                });
    }

    @Override
    public void queryTopicCount(Context context, String json) {
        MediaType parse = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(parse,json);
        ApiRetrofit.getInstance().getApiService().queryTopicCount(body)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<TopicCountEntity>() {

                    @Override
                    public void onSuccess(TopicCountEntity entity) {
                        if(entity.getState().equals("0")){
                            infoView.loadTopicCountSuc(entity);
                        }else{
                            infoView.loadTopicCountEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadTopicCountEmpty();
                    }
                });
    }

    @Override
    public void saveStudentPaper(Context context, String json) {
        MediaType parse = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(parse,json);
        ApiRetrofit.getInstance().getApiService().saveStudentPaper(body)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<PaperIdEntity>() {

                    @Override
                    public void onSuccess(PaperIdEntity entity) {
                        if(entity.getState().equals("0")){
                            infoView.loadPaperSuc(entity);
                        }else{
                            infoView.loadPaperEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadPaperEmpty();
                    }
                });
    }

    @Override
    public void checkPaperName(Context context, String paperName, String userId) {
        ApiRetrofit.getInstance().getApiService().checkPaperName(paperName,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<CheckPaperNameEntity>() {

                    @Override
                    public void onSuccess(CheckPaperNameEntity entity) {
                        if(entity.getState().equals("0")){
                            infoView.loadCheckPaperNameSuc(entity);
                        }else{
                            infoView.loadCheckPaperNameEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadCheckPaperNameEmpty();
                    }
                });
    }
}
