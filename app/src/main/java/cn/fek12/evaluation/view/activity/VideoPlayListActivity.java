package cn.fek12.evaluation.view.activity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.utils.toast.ToastUtils;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IVideoPlayList;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;
import cn.fek12.evaluation.presenter.VideoPlayListPresenter;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.adapter.RelevantVideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import cn.fek12.evaluation.view.widget.MyJzvdStd;
import cn.jzvd.Jzvd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: VideoPlayListActivity
 * @Description:
 * @CreateDate: 2019/11/13 17:32
 */
public class VideoPlayListActivity extends BaseActivity<VideoPlayListPresenter> implements IVideoPlayList.View, RelevantVideoAdapter.OnItemClickListener {
    @BindView(R.id.jzVideo)
    MyJzvdStd jzVideo;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private String subjectCategoryId;
    private RelevantVideoAdapter adapter;
    private RelevantVideoListEntity.DataBean.VideoBean videoBean;
    private int isCollection;

    @Override
    public int setLayoutResource() {
        return R.layout.video_play_list_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle();
        subjectCategoryId = getIntent().getStringExtra("subjectCategoryId");
        adapter = new RelevantVideoAdapter(this);
        adapter.setOnItemClickListener(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onLoadData() {
        multipleStatusView.showLoading();
        mPresenter.videoList(getContext(),subjectCategoryId, MyApplication.getMyApplication().getUserId());
    }

    @Override
    public void loadSuc(RelevantVideoListEntity entry) {
        multipleStatusView.showContent();
        if(entry != null){
            if(entry.getData() != null && entry.getData().getVideo() != null){
                videoBean = entry.getData().getVideo();
                String path = null;
                try {
                    path = FastDFSUtil.generateSourceUrl(videoBean.getAddressUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                jzVideo.seekToInAdvance = videoBean.getPlayScheduleTime();
                jzVideo.setUp(path, videoBean.getVideoName());
                isCollection = videoBean.getCollect();
                jzVideo.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
                jzVideo.ivExtend.setOnClickListener(onClickListener);
                //jzVideo.thumbImageView.setImageResource(R.mipmap.presentation_empty_bg);
                /*String finalPath = path;
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = VideoUtils.getInstance().getNetVideoBitmap(finalPath);
                            if(bitmap != null){
                                jzVideo.thumbImageView.setImageBitmap(bitmap);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });*/
            }
        }
        if(entry.getData() != null && entry.getData().getRelatedVideo() != null && entry.getData().getRelatedVideo().size() > 0){
            adapter.notifyChanged(entry.getData().getRelatedVideo());
        }else{
            multipleStatusView.showEmpty();
        }
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
                    mPresenter.collection(VideoPlayListActivity.this,videoBean.getCacheKey(),String.valueOf(videoBean.getType()),String.valueOf(videoBean.getVideoId()),tag,MyApplication.getMyApplication().getUserId());
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        long currentPos = jzVideo.getCurrentPositionWhenPlaying();
        if(currentPos > 0){
            mPresenter.schedule(VideoPlayListActivity.this,videoBean.getCacheKey(),"",String.valueOf(currentPos),
                    String.valueOf(videoBean.getType()),String.valueOf(videoBean.getVideoId()),MyApplication.getMyApplication().getUserId());
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Jzvd.releaseAllVideos();
        super.onDestroy();
    }

    @Override
    public void loadEmpty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void loadCollectionSuc(CommonEntity entry) {
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

    @Override
    protected VideoPlayListPresenter onInitLogicImpl() {
        return new VideoPlayListPresenter(this);
    }

    @Override
    public void onItemClick(int position) {

    }
}
