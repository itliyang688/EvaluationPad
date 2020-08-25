package cn.fek12.evaluation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.utils.toast.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMicrolessVideoPlay;
import cn.fek12.evaluation.model.entity.CollectionEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.presenter.MicrolessonVideoPlayPresenter;
import cn.fek12.evaluation.presenter.VideoPlayListPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.widget.MyJzvdStd;
import cn.jzvd.Jzvd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: VideoPlayListActivity
 * @Description:
 * @CreateDate: 2019/11/13 17:32
 */
public class PlanForTheWeekActivity extends BaseActivity<MicrolessonVideoPlayPresenter> implements MyJzvdStd.OnStateAutoComplete, IMicrolessVideoPlay.View {
    @BindView(R.id.jzVideo)
    MyJzvdStd jzVideo;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.iv_left_back)
    ImageView ivLeftBack;
    private int isCollection;
    private int playScheduleTime;
    private String isEnd = "0";
    private String videoUrl;
    private String imgUrl;
    private String videoId;
    private String videName;

    @Override
    public int setLayoutResource() {
        return R.layout.plan_the_week_activity;
    }

    @Override
    protected void onInitView() {
        //setEmptyTitle();
        //mViewRoot.setBackgroundResource(R.mipmap.plan_the_week_bg);
        rootView.setPadding(0, AppUtils.getStatusBarHeight(PlanForTheWeekActivity.this), 0, 0);
        videoUrl = getIntent().getStringExtra("videoUrl");
        videoId = getIntent().getStringExtra("videoId");
        imgUrl = getIntent().getStringExtra("imgUrl");
        videName = getIntent().getStringExtra("videName");
        isCollection = getIntent().getIntExtra("isCollection", 0);
        playScheduleTime = getIntent().getIntExtra("playScheduleTime", 0);

        jzVideo.seekToInAdvance = playScheduleTime;
        jzVideo.setUp(videoUrl, videName);
        jzVideo.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
        jzVideo.ivExtend.setOnClickListener(onClickListener);
        jzVideo.setOnStateAutoComplete(PlanForTheWeekActivity.this);
        Glide.with(MyApplication.getApp()).load(imgUrl).into(jzVideo.thumbImageView);

        ivLeftBack.setOnClickListener(onClickListener);
    }

    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    protected void onLoadData() {

    }

    private String tag;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.iv_left_back:
                    PlanForTheWeekActivity.this.finish();
                    break;
                case R.id.ivExtend:
                    if (isCollection == 0) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    mPresenter.collection(PlanForTheWeekActivity.this,videoId,tag, MyApplication.getMyApp().getUserId());
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        jzVideo.goOnPlayOnPause();
        long currentPos = jzVideo.getCurrentPositionWhenPlaying();
        if (currentPos > 0 || isEnd.equals("1")) {
            mPresenter.addOrUpdateVideoPlayCount(PlanForTheWeekActivity.this,String.valueOf(currentPos),MyApplication.getMyApp().getUserId(),videoId);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Jzvd.releaseAllVideos();
        super.onDestroy();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
        jzVideo.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
    }

    @Override
    public void loadCollectionFail() {
        if(tag.equals("0")){
            ToastUtils.popUpToast("取消失败");
        }else{
            ToastUtils.popUpToast("收藏失败");
        }
    }
}
