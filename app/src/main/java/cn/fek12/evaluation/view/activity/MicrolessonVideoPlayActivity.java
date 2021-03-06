package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.utils.string.StringUtils;
import com.fek12.basic.utils.toast.ToastUtils;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMicrolessVideoPlay;
import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.presenter.MicrolessonVideoPlayPresenter;
import cn.fek12.evaluation.view.widget.MyJzvdStd;
import cn.jzvd.Jzvd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: FullScreenVideoPlayActivity
 * @Description:
 * @CreateDate: 2019/11/12 13:01
 */
public class MicrolessonVideoPlayActivity extends BaseActivity<MicrolessonVideoPlayPresenter> implements IMicrolessVideoPlay.View, MyJzvdStd.OnStateAutoComplete {
    @BindView(R.id.jzVideo)
    MyJzvdStd myJzvdStd;

    private String pathUrl;
    private String videoName;
    private String imgUrl;
    private String videoId;
    private String subjectCategoryId;
    private long playScheduleTime;
    private int isCollection;
    private String isEnd = "0";
    private long currentPos = 0;

    @Override
    public int setLayoutResource() {
        return R.layout.video_play_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        pathUrl = intent.getStringExtra("pathUrl");
        videoName = intent.getStringExtra("videoName");
        imgUrl = intent.getStringExtra("imgUrl");
        videoId = intent.getStringExtra("videoId");
        subjectCategoryId = intent.getStringExtra("subjectCategoryId");
        playScheduleTime = intent.getIntExtra("playScheduleTime",0);
        isCollection = intent.getIntExtra("isCollection",0);

        //String path = "http://res.51jxpj.cn/group1/M00/01/CD/wKgo0F8iP3KAM9cMAlOxL3yPwT4824.mp4?token=3b1a840fa85c4bd60ab5a20ba534b55a&ts=1596081630";
        //String path1 = "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4";
        myJzvdStd.setUp(pathUrl,videoName);
        myJzvdStd.seekToInAdvance = playScheduleTime;
        myJzvdStd.gotoScreenFullscreen();
        //设置容器内播放器高,解决黑边（视频全屏）
        myJzvdStd.setVideoImageDisplayType(myJzvdStd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
        //将缩略图的scaleType设置为FIT_XY（图片全屏）
        myJzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(MyApplication.getApp()).load(imgUrl).into(myJzvdStd.thumbImageView);
        myJzvdStd.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
        myJzvdStd.backButton.setOnClickListener(onClickListener);
        myJzvdStd.fullscreenButton.setOnClickListener(onClickListener);
        myJzvdStd.ivExtend.setOnClickListener(onClickListener);
        myJzvdStd.startVideo();
        myJzvdStd.setOnStateAutoComplete(this);
    }

    private String tag;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fullscreen:
                    break;
                case R.id.back:
                    currentPos = myJzvdStd.getCurrentPositionWhenPlaying();
                    Jzvd.releaseAllVideos();
                    overridePendingTransition(com.fek12.basic.R.anim.slide_left_in, com.fek12.basic.R.anim.slide_right_out);
                    finish();
                    break;
                case R.id.ivExtend:
                    if(isCollection == 0){
                        tag = "1";
                    }else{
                        tag = "0";
                    }
                    mPresenter.collection(MicrolessonVideoPlayActivity.this,String.valueOf(videoId),tag, MyApplication.getMyApp().getUserId());
                    break;
            }
        }
    };
    @Override
    public void onBackPressed() {
        currentPos = myJzvdStd.getCurrentPositionWhenPlaying();
        Jzvd.releaseAllVideos();
        overridePendingTransition(com.fek12.basic.R.anim.slide_left_in, com.fek12.basic.R.anim.slide_right_out);
        super.onBackPressed();
    }


    @Override
    protected void onLoadData() {

    }

    @Override
    public void loadCollectionSuc(CollectionEntity entry) {
        PrefUtilsData.setIsCollectionRefresh(true);
        if(tag.equals("0")){
            isCollection = 0;
            ToastUtils.popUpToast("已取消");
        }else{
            isCollection = 1;
            ToastUtils.popUpToast("已收藏");
        }
        myJzvdStd.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
    }

    @Override
    public void loadCollectionFail() {
        if(tag.equals("0")){
            ToastUtils.popUpToast("取消失败");
        }else{
            ToastUtils.popUpToast("收藏失败");
        }
    }

    @Override
    protected void onPause() {
        if(currentPos > 0){
            if(StringUtils.isEmpty(subjectCategoryId)){
                mPresenter.addOrUpdateVideoPlayCount(MicrolessonVideoPlayActivity.this,String.valueOf(currentPos),MyApplication.getMyApp().getUserId(),videoId);
            }else{
                mPresenter.schedule(MicrolessonVideoPlayActivity.this, String.valueOf(currentPos),subjectCategoryId, videoId, MyApplication.getMyApp().getUserId());
            }
        }
        super.onPause();
    }

    @Override
    protected MicrolessonVideoPlayPresenter onInitLogicImpl() {
        return new MicrolessonVideoPlayPresenter(this);
    }


    @Override
    public void stateAutoComplete() {
        isEnd = "1";
    }

    @Override
    public void statePlaying() {
        isEnd = "0";
    }
}
