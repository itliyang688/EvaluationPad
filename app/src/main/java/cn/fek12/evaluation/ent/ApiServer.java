package cn.fek12.evaluation.ent;

import cn.fek12.evaluation.model.entity.AWeekEntity;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.CheckPaperNameEntity;
import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.model.entity.EarlierEntity;
import cn.fek12.evaluation.model.entity.EvaluationListEntity;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.PaperIdEntity;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.model.entity.QueryTopicEntity;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TopicCountEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    /**
     * 首页测评
     */
    @FormUrlEncoded
    @POST("assessment/index")
    Observable<HomeEvaluationDeta> queryEvaluation(@Field("userId") String userId);

    /**
     * 查询学科
     */
    @FormUrlEncoded
    @POST("assessment/dictionaryList")
    Observable<SubjectEntity> querySubject(@Field("grade") String grade);

    /**
     * 去攻克
     */
    @FormUrlEncoded
    @POST("promote/promoteDetail")
    Observable<ConqueredEntity> promoteDetail(@Field("paperResultId") String paperResultId );

    /**
     * 相关视频列表页面
     */
    @FormUrlEncoded
    @POST("promote/capture")
    Observable<RelevantVideoListEntity> videoList(@Field("subjectCategoryId ") String subjectCategoryId, @Field("userId  ") String userId);


    /**
     * 查询版本
     */
    @FormUrlEncoded
    @POST("assessment/dictionaryList")
    Observable<TextbookEntity> queryTextbook(@Field("grade") String grade, @Field("subject") String subject);

    /**
     * 查询教材
     */
    @FormUrlEncoded
    @POST("assessment/dictionaryList")
    Observable<SemesterEntity> querySemester(@Field("grade") String grade, @Field("subject") String subject, @Field("textbook") String textbook);

    @GET("assessment/gradeDictionaryList")
    Observable<GradeDictionaryListEntity> queryGradeDictionaryList();

    @GET("assessment/paperTypeList")
    Observable<PaperTypeListResp> getPaperTypeList();

    /**
     * 自测记录
     */
    @GET("independent/records")
    Observable<RecordsEntitiy> queryRecordsList(@Query("userId") String userId);

    /**
     * 请求题目数量
     */
    //@FormUrlEncoded
    //@Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("independent/usableSubjectNum")
    Observable<TopicCountEntity> queryTopicCount(@Body RequestBody reqJson);

    /**
     * 生成试卷
     */
    //@FormUrlEncoded
    //@Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("independent/saveStudentPaper")
    Observable<PaperIdEntity> saveStudentPaper(@Body RequestBody reqJson);


    @FormUrlEncoded
    @POST("assessment/indexPaper")
    Observable<AssessmentIndexPaperResp> getIndexPaper(@Field("grade") String grade,
                                                       @Field("semester") String semester,
                                                       @Field("subject") String subject,
                                                       @Field("textbook") String textbook,
                                                       @Field("ptype") String ptype,
                                                       @Field("userId") String userId);

    @FormUrlEncoded
    @POST("paperReport/weekIndividualReport")
    Observable<AWeekEntity> queryAWeek(@Field("grade") String grade,
                                       @Field("semester") String semester,
                                       @Field("subject") String subject,
                                       @Field("textbook") String textbook,
                                       @Field("userId") String userId,
                                       @Field("userType") String userType);

    @FormUrlEncoded
    @POST("paperReport/priorityIndividualReport")
    Observable<EarlierEntity> queryEarlier(@Field("grade") String grade,
                                           @Field("semester") String semester,
                                           @Field("subject") String subject,
                                           @Field("textbook") String textbook,
                                           @Field("userId") String userId,
                                           @Field("userType") String userType,
                                           @Field("currentPage") String currentPage,
                                           @Field("pageSize") String pageSize);


    @GET("assessment/subjectCategoryList")
    Observable<TreeDataEntity> queryTreeData(@Query("paperType") String paperType,
                                             @Query("grade") String grade,
                                             @Query("semester") String semester,
                                             @Query("subject") String subject,
                                             @Query("textbook") String textbook,
                                             @Query("userId") String userId);

    /**检查试卷名称是否重复*/
    @GET("independent/checkPaperName")
    Observable<CheckPaperNameEntity> checkPaperName(@Query("paperName") String paperName ,
                                                    @Query("userId") String userId);

    @FormUrlEncoded
    @POST("assessment/paperList")
    Observable<EvaluationListEntity> queryPaperList(@Field("grade") String grade,
                                                    @Field("semester") String semester,
                                                    @Field("subject") String subject,
                                                    @Field("textbook") String textbook,
                                                    @Field("userId") String userId,
                                                    @Field("ptype") String ptype,
                                                    @Field("userType") String userType,
                                                    @Field("knowledge") String knowledge,
                                                    @Field("paperListType") String paperListType,
                                                    @Field("currentPage") String currentPage);

    @FormUrlEncoded
    @POST("assessment/paperList")
    Observable<EvaluationListEntity> queryAutonomyPaperList(@Field("grade") String grade,
                                                    @Field("semester") String semester,
                                                    @Field("subject") String subject,
                                                    @Field("textbook") String textbook,
                                                    @Field("userId") String userId,
                                                    @Field("currentPage") String currentPage);
}

