package cn.fek12.evaluation.ent;

import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    /**
     * 首页测评
     */
    @FormUrlEncoded
    @POST("assessment/index")
    Observable<HomeEvaluationDeta> queryEvaluation(@Field("userId") String userId);

    @GET("assessment/dictionaryList")
    Observable<DictionaryListResp>  getDictionaryList();

    @GET("assessment/paperTypeList")
    Observable<PaperTypeListResp>  getPaperTypeList();

    @FormUrlEncoded
    @POST("assessment/indexPaper")
    Observable<AssessmentIndexPaperResp> getIndexPaper(@Field("grade") String grade,
           @Field("semester") String semester,
           @Field("subject") String subject,
           @Field("textbook") String textbook,
           @Field("ptype") String ptype,
           @Field("userId") String userId);


    @GET("assessment/subjectCategoryList")
    Observable<TreeDataEntity> queryTreeData(@Query("paperType") String paperType,
                                             @Query("grade") String grade,
                                             @Query("semester") String semester,
                                             @Query("subject") String subject,
                                             @Query("textbook") String textbook,
                                             @Query("userId") String userId);
}
