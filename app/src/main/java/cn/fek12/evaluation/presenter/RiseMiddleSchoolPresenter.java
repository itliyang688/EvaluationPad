package cn.fek12.evaluation.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.impl.IRiseMiddleSchool;
import cn.fek12.evaluation.impl.ISynchroVideoTree;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.presenter
 * @ClassName: EvaluationPresenter
 * @Description:
 * @CreateDate: 2019/10/23 15:14
 */
public class RiseMiddleSchoolPresenter extends BasePresenter<IRiseMiddleSchool.View> implements IRiseMiddleSchool{
    private View infoView;
    public RiseMiddleSchoolPresenter(@NonNull View view, Context activity) {
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


    @Override
    public void queryPaperList(Context context, String grade, String subject, String textbook, String semester, String userId, String currentPage, String knowledge,String type) {
        ApiRetrofit.getInstance().getApiService().videoTreeList(grade,semester,subject,textbook,type,knowledge,userId)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<VideoMoreListEntity>() {

                    @Override
                    public void onSuccess(VideoMoreListEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadVideoTreeListSuc(entry);
                        }else{
                            infoView.loadVideoTreeListEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadVideoTreeListEmpty();
                    }
                });
    }
}
