package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.utils.toast.ToastUtils;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ISpeciaVideoPlay;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.presenter.SpeciaVideoPlayPresenter;
import cn.fek12.evaluation.view.widget.MyJzvdStd;
import cn.jzvd.Jzvd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: SpecialVideoActivity
 * @Description:
 * @CreateDate: 2019/11/12 15:23
 */
public class SpecialVideoActivity extends BaseActivity<SpeciaVideoPlayPresenter> implements ISpeciaVideoPlay.View, MyJzvdStd.OnStateAutoComplete {
    @BindView(R.id.jzVideo)
    MyJzvdStd myJzvdStd;
    @BindView(R.id.tvChapter)
    TextView tvChapter;
    @BindView(R.id.tvDescribe)
    TextView tvDescribe;
    private String pathUrl;
    private String videoName;
    private String chapter;
    private String describe;
    private String cacheKey;
    private String imgUrl;
    private String structLayKey;
    private int videoType;
    private int videoId;
    private long playScheduleTime;
    private int isCollection;
    private String isEnd = "0";
    private long currentPos = 0;

    @Override
    public int setLayoutResource() {
        return R.layout.specia_video_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPos = myJzvdStd.getCurrentPositionWhenPlaying();
                Jzvd.releaseAllVideos();
                overridePendingTransition(com.fek12.basic.R.anim.slide_left_in, com.fek12.basic.R.anim.slide_right_out);
                finish();
            }
        });
        Intent intent = getIntent();
        pathUrl = intent.getStringExtra("pathUrl");
        videoName = intent.getStringExtra("videoName");
        chapter = intent.getStringExtra("chapter");
        describe = intent.getStringExtra("describe");
        cacheKey = intent.getStringExtra("cacheKey");
        imgUrl = intent.getStringExtra("imgUrl");
        structLayKey = intent.getStringExtra("structLayKey");
        videoType = intent.getIntExtra("videoType",0);
        videoId = intent.getIntExtra("videoId",0);
        playScheduleTime = intent.getLongExtra("playScheduleTime",0);
        isCollection = intent.getIntExtra("isCollection",0);
        tvChapter.setText(chapter);
        tvDescribe.setText(describe);
        //String path = "http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542";
        //String path1 = "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4";
        myJzvdStd.setUp(pathUrl, videoName);
        //myJzvdStd.gotoScreenFullscreen();
        myJzvdStd.seekToInAdvance = playScheduleTime;
        //myJzvdStd.getCurrentPositionWhenPlaying();
        //Bitmap bitmap = VideoUtils.getInstance().getNetVideoBitmap(path);
        //myJzvdStd.thumbImageView.setImageBitmap(bitmap);
        Glide.with(MyApplication.getApp()).load(imgUrl).into(myJzvdStd.thumbImageView);
        myJzvdStd.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
        //myJzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        myJzvdStd.ivExtend.setOnClickListener(onClickListener);
        myJzvdStd.startVideo();
        myJzvdStd.setOnStateAutoComplete(this);
    }

    private String tag;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivExtend:
                    if(isCollection == 0){
                        tag = "1";
                    }else{
                        tag = "0";
                    }
                    mPresenter.collection(SpecialVideoActivity.this,cacheKey,String.valueOf(videoType),String.valueOf(videoId),tag,MyApplication.getMyApp().getUserId());
                    break;
            }
        }
    };

    @Override
    public void loadCollectionSuc(CommonEntity entry) {
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
    public void onBackPressed() {
        currentPos = myJzvdStd.getCurrentPositionWhenPlaying();
        Jzvd.releaseAllVideos();
        overridePendingTransition(com.fek12.basic.R.anim.slide_left_in, com.fek12.basic.R.anim.slide_right_out);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        if(currentPos > 0 || isEnd.equals("1")){
            mPresenter.schedule(SpecialVideoActivity.this,cacheKey,structLayKey,String.valueOf(currentPos),
                    String.valueOf(videoType),String.valueOf(videoId), MyApplication.getMyApp().getUserId(),0,isEnd);
        }
        super.onPause();
    }

    @Override
    protected void onLoadData() {

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
    protected SpeciaVideoPlayPresenter onInitLogicImpl() {
        return new SpeciaVideoPlayPresenter(this);
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
