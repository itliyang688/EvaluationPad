package cn.fek12.evaluation.ent;

import cn.fek12.evaluation.model.entity.AWeekEntity;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.AuthEntity;
import cn.fek12.evaluation.model.entity.CheckPaperNameEntity;
import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.entity.CollectionListEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.model.entity.CurriculumEntity;
import cn.fek12.evaluation.model.entity.EarlierEntity;
import cn.fek12.evaluation.model.entity.EvaluationListEntity;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.model.entity.MicroLessonPageEnetity;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.model.entity.PlanVideoEntity;
import cn.fek12.evaluation.model.entity.PracticeListEntity;
import cn.fek12.evaluation.model.entity.PromoteRecommedVideoEntity;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SourceEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;
import cn.fek12.evaluation.model.entity.SubjectsEntity;
import cn.fek12.evaluation.model.entity.SubjectsObsoleteEntity;
import cn.fek12.evaluation.model.entity.TaskNumEntity;
import cn.fek12.evaluation.model.entity.TestRecordEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TopicCountEntity;
import cn.fek12.evaluation.model.entity.TrainListEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.entity.UpdateApkEntity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;
import cn.fek12.evaluation.model.entity.YearMonthEntity;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiServer {

    /**
     * 首页测评
     */
    @FormUrlEncoded
    @POST("assessment/index")
    Observable<HomeEvaluationDeta> queryEvaluation(@Field("userId") String userId,@Field("grade") String grade);

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
    Observable<ConqueredEntity> promoteDetail(@Field("paperResultId") String paperResultId);

    /**
     * 相关视频列表页面
     */
    @FormUrlEncoded
    @POST("promote/capture")
    Observable<RelevantVideoListEntity> videoList(@Field("subjectCategoryId") String subjectCategoryId, @Field("userId") String userId);


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
    Observable<GradeDictionaryListEntity> queryGradeDictionaryList(@Query("secType") String secType);

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
    Observable<CommonEntity> saveStudentPaper(@Body RequestBody reqJson);

    /**
     * 记录播放时间
     */
    @FormUrlEncoded
    @POST("video/schedule")
    Observable<CommonEntity> schedule(@Field("cacheKey") String cacheKey,
                                      @Field("structLayKey") String structLayKey,
                                              @Field("playScheduleTime") String playScheduleTime,
                                              @Field("type") String type,
                                              @Field("videoId") String videoId,
                                              @Field("userId") String userId,
                                              @Field("isEnd") String isEnd);
    /**
     * 播放记录
     */
    @FormUrlEncoded
    @POST("promote/addCourseRecord")
    Observable<CommonEntity> addCourseRecord(@Field("playScheduleTime") String playScheduleTime,
                                      @Field("subjectCategoryId") String subjectCategoryId,
                                      @Field("videoId") String videoId,
                                      @Field("userId") String userId);

    /**
     * 收藏、取消收藏
     */
    @FormUrlEncoded
    @POST("video/collection")
    Observable<CommonEntity> collection(@Field("cacheKey") String cacheKey,
                                      @Field("type") String type,
                                      @Field("videoId") String videoId,
                                      @Field("isCollection") String isCollection,
                                      @Field("userId") String userId);

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
                                                    @Field("currentPage") String currentPage,
                                                    @Field("pageSize") String pageSize);

    @FormUrlEncoded
    @POST("independent/paperList")
    Observable<EvaluationListEntity> queryAutonomyPaperList(@Field("grade") String grade,
                                                    @Field("semester") String semester,
                                                    @Field("subject") String subject,
                                                    @Field("textbook") String textbook,
                                                    @Field("userId") String userId,
                                                    @Field("currentPage") String currentPage);

    /**首页微课全部模块*/
    @FormUrlEncoded
    @POST("video/all")
    Observable<MicroLessonEnetity> queryVideoList(@Field("grade") String grade,
                                                  @Field("semester") String semester,
                                                  @Field("subject") String subject,
                                                  @Field("textbook") String textbook,
                                                  @Field("type") String type,
                                                  @Field("userId") String userId);

    /**首页微课专题模块*/
    @FormUrlEncoded
    @POST("video/synchro/all")
    Observable<MicroLessonEnetity> querySynchroVideo(@Field("grade") String grade,
                                                     @Field("semester") String semester,
                                                     @Field("subject") String subject,
                                                     @Field("textbook") String textbook,
                                                     @Field("userId") String userId);

    /**首页微课专题模块*/
    @FormUrlEncoded
    @POST("video/special/all")
    Observable<MicroLessonEnetity> querySpecialVideo(@Field("grade") String grade,
                                                  @Field("semester") String semester,
                                                  @Field("subject") String subject,
                                                  @Field("textbook") String textbook,
                                                  @Field("userId") String userId);

    /**收藏列表*/
    @GET("nocVideoCollection/queryUserCollectionList")
    Observable<CollectionListEntity> collectionList(@Query("userId") String userId,
                                                    @Query("subId") String subId);
    /**观看历史*/
    @GET("nocVideoData/queryPlayRecordByUserId/{userId}/{subId}")
    Observable<CollectionListEntity> hisPlayList(@Path("userId") String userId,
                                                    @Path("subId") String subId);

    /**推荐查看更多*/
    @FormUrlEncoded
    @POST("video/recommoned/more")
    Observable<VideoMoreListEntity> recommendMoreList(@Field("grade") String grade,
                                                       @Field("semester") String semester,
                                                       @Field("subject") String subject,
                                                       @Field("textbook") String textbook,
                                                       @Field("type") String type,
                                                       @Field("userId") String userId);
    /**热门查看更多*/
    @FormUrlEncoded
    @POST("video/top/more")
    Observable<VideoMoreListEntity> hotMoreList(@Field("grade") String grade,
                                                       @Field("semester") String semester,
                                                       @Field("subject") String subject,
                                                       @Field("textbook") String textbook,
                                                       @Field("type") String type,
                                                       @Field("userId") String userId);
    /**最近更新查看更多*/
    @FormUrlEncoded
    @POST("video/near/more")
    Observable<VideoMoreListEntity> nearMoreList(@Field("grade") String grade,
                                                 @Field("semester") String semester,
                                                 @Field("subject") String subject,
                                                 @Field("textbook") String textbook,
                                                 @Field("type") String type,
                                                 @Field("userId") String userId);

    /**攻克足迹课程记录*/
    @FormUrlEncoded
    @POST("conquerFootprint/courseRecord")
    Observable<CurriculumEntity> courseRecord(@Field("beginDate") String beginDate,
                                              @Field("endDate") String endDate,
                                              @Field("subject") String subject,
                                              @Field("userId") String userId,
                                              @Field("currentPage") String currentPage);

    /**查看更多*/
    @FormUrlEncoded
    @POST("video/list")
    Observable<VideoMoreListEntity> videoTreeList(@Field("grade") String grade,
                                                    @Field("semester") String semester,
                                                    @Field("subject") String subject,
                                                    @Field("textbook") String textbook,
                                                    @Field("type") String type,
                                                    @Field("knowledgePoint") String knowledgePoint,
                                                    @Field("userId") String userId);

    /**
     * 练习记录
     */
    @GET("/practice/statistics/{userId}/{structId}")
    Observable<TestRecordEntity> testRecordsList(@Path("structId") String structId, @Path("userId") String userId);

    /**
     * 学科
     */
    @GET("/dictionary/subjects")
    Observable<SubjectsObsoleteEntity> subjects();

    /**
     * 学科
     */
    @GET("nocSubject/getNocSubjectInfoList")
    Observable<SubjectsEntity> getNocSubjectInfoList();

    /**
     * 来源
     */
    @POST("wrongTopic/wrongTopicOrigin")
    Observable<SourceEntity> wrongTopicOrigin();

    /**
     * 获取月份
     */
    @GET("/practice/months/{userId}")
    Observable<YearMonthEntity> getYears(@Path("userId") String userId);

    /**
     * 更多强化训练获取月份
     */
    @GET("/taskDrill/months/{userId}")
    Observable<YearMonthEntity> getTaskDrillYears(@Path("userId") String userId);
    /**
     * 获取每日记录
     */
    @GET("/practice/list/{userId}/{structId}/{date}")
    Observable<PracticeListEntity> getPracticeList(@Path("userId") String userId, @Path("structId") String structId, @Path("date") String date);
    /**
     * 获取强化训练每日记录
     */
    @GET("taskDrill/list/{userId}/{date}")
    Observable<TrainListEntity> getTrainList(@Path("userId") String userId, @Path("date") String date);

    /**
     * 微课小学模块
     */
    @GET("nocVideo/queryCoursePackVideo")
    Observable<MicrolessonVideoEntity> queryCoursePackVideo(@Query("coursePackType")String coursePackType,
                                                            @Query("knowledgePointId") String knowledgePointId,
                                                            @Query("userId")String userId,
                                                            @Query("current")String current,
                                                            @Query("size")String size,
                                                            @Query("coursePackId")String coursePackId);

    /**
     * 收藏、取消收藏
     */
    @FormUrlEncoded
    @POST("nocVideoCollection/addOrCancelVideoCollection")
    Observable<CollectionEntity> addOrCancelVideoCollection(
                                        @Field("videoId") String videoId,
                                        @Field("status") String status ,
                                        @Field("userId") String userId);

    /**
     * 添加或修改视频播放量及用户视频播放记录
     */
    @FormUrlEncoded
    @POST("nocVideoData/addOrUpdateVideoPlayCount")
    Observable<CollectionEntity> addOrUpdateVideoPlayCount(
            @Field("playLength") String playLength,
            @Field("userId") String userId ,
            @Field("videoId") String videoId);

    /**小学同步视频*/
    @GET("nocVideo/querySyncVideoPage")
    Observable<MicrolessonVideoEntity> querySyncVideoPage(@Query("gradeId") String grade,
                                                  @Query("secId") String secId,
                                                  @Query("subId") String subId,
                                                  @Query("versionId") String versionId,
                                                  @Query("knowledgeId") String knowledgeId,
                                                  @Query("userId") String userId,
                                                  @Query("current")String current,
                                                  @Query("size")String size);

    /**
     * 获取左侧知识树
     */
    @GET("nocVideoCoursePack/queryGoodsClassDataTree/{type}")
    Observable<TreeDataEntity> queryGoodsClassDataTree(@Path("type") String type );

    /**首页微课全部模块*/
    @GET("nocVideo/getJuniorMainVideoInfo")
    Observable<MicroLessonEnetity> getJuniorMainVideoInfo(@Query("gradeId") String gradeId,
                                                  @Query("subId") String subId,
                                                  @Query("secId") String secId,
                                                  @Query("versionId") String versionId,
                                                  @Query("moreType") String moreType,
                                                  @Query("userId") String userId);

    /***带分页的微课模块*/
    @GET("nocVideo/getJuniorMainVideoInfo")
    Observable<MicroLessonPageEnetity> getJuniorMainVideoInfo(@Query("gradeId") String gradeId,
                                                              @Query("subId") String subId,
                                                              @Query("secId") String secId,
                                                              @Query("versionId") String versionId,
                                                              @Query("moreType") String moreType,
                                                              @Query("userId") String userId,
                                                              @Query("current") String current,
                                                              @Query("size") String size);


    /**
     * 查询学科
     */
    @GET("nocVideoCoursePack/getBkCoursePackSubList/{type}")
    Observable<SubjectModel> getBkCoursePackSubList(@Path("type") String type);

    /**
     * auth认证
     */
    @FormUrlEncoded
    @POST("auth/uauth")
    Observable<AuthEntity> uauth(@Field("userId") String userId);

    /***检查更新*/
    @GET("nocPadVersion/checkPadVersionByCode")
    Observable<UpdateApkEntity> checkPadVersionByCode(@Query("versionCode") String versionCode);

    /***学情本周计划*/
    @GET("studyPlan/querWeekStudyPlanVideoList")
    Observable<PlanVideoEntity> querWeekStudyPlanVideoList(@Query("userId") String userId, @Query("subjectId") String subjectId);

    /***查询作业考试任务数量*/
    @GET("task/getUntreatedTaskNumByUserId")
    Observable<TaskNumEntity> getUntreatedTaskNumByUserId(@Query("userId") String userId);

    /***学情作业考试*/
    @GET("task/queryTaskPage")
    Observable<ExaminationEntity> queryTaskPage(@Query("userId") String userId, @Query("subjectId") String subjectId, @Query("taskType") String taskType, @Query("startDate") String startDate, @Query("endDate") String endDate, @Query("status") String status,@Query("current") String current, @Query("size") String size);

    /***根据用户获取学科*/
    @GET("nocSubject/getStuSubjectByUserId")
    Observable<SubjectModel> getStuSubjectByUserId(@Query("userId") String userId);
    /***提升视频推荐*/
    @GET("studyPlan/getPromoteRecommedVideo")
    Observable<PromoteRecommedVideoEntity> getPromoteRecommedVideo(@Query("userId") String userId,@Query("current") String current,@Query("size") String size);
}

