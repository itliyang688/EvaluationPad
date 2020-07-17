package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IPrepareExamination;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;
import cn.fek12.evaluation.model.entity.TreeDataEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class PrepareExaminationPresenter extends BasePresenter<IPrepareExamination.View> implements IPrepareExamination {
    private View infoView;
    public PrepareExaminationPresenter(@NonNull View view, Context activity) {
        this.infoView = view;
    }

    @Override
    public void initTreeData(Context context, String subId) {
        ApiRetrofit.getInstance().getApiService().queryGoodsClassDataTree(subId)
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
    public void querySubjectList(Context context, String paperType) {
        ApiRetrofit.getInstance().getApiService().getBkCoursePackSubList(paperType)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<SubjectModel>() {

                    @Override
                    public void onSuccess(SubjectModel entry) {
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

    @Override
    public void queryPaperList(Context context, String coursePackType, String knowledgePointId, String userId, String current, String size) {
        ApiRetrofit.getInstance().getApiService().queryCoursePackVideo(null,knowledgePointId,userId,current,size,coursePackType)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<MicrolessonVideoEntity>() {

                    @Override
                    public void onSuccess(MicrolessonVideoEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadVideoListSuc(entry);
                        }else{
                            infoView.loadVideoListEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadVideoListEmpty();
                    }
                });
    }
}
