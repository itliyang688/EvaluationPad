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
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
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
    public void initTreeData(Context context, String type) {
        ApiRetrofit.getInstance().getApiService().queryGoodsClassDataTree(type)
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
    public void queryVideoList(Context context, String coursePackType, String knowledgePointId, String userId, String current, String size) {
        ApiRetrofit.getInstance().getApiService().queryCoursePackVideo(coursePackType,knowledgePointId,userId,current,size,null)
                .compose(RxHelper.observableIO2Main(context))
                .subscribe(new BaseObserver<MicrolessonVideoEntity>() {
                    @Override
                    public void onSuccess(MicrolessonVideoEntity entry) {
                        if(entry.getState().equals("0")){
                            infoView.loadVideoSuc(entry);
                        }else{
                            infoView.loadVideoEmpty();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        infoView.loadFail(msg);
                    }
                });
    }
}
