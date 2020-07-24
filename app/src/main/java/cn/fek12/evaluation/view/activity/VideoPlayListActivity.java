package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.utils.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IVideoPlayList;
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.model.entity.CollectionEntity;
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
public class VideoPlayListActivity extends BaseActivity<VideoPlayListPresenter> implements IVideoPlayList.View, RelevantVideoAdapter.OnItemClickListener, MyJzvdStd.OnStateAutoComplete {
    @BindView(R.id.jzVideo)
    MyJzvdStd jzVideo;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.llClick)
    LinearLayout llClick;
    private String subjectCategoryId;
    private RelevantVideoAdapter adapter;
    private RelevantVideoListEntity.DataBean.VideoBean videoBean;
    private int isCollection;
    private List<RelevantVideoListEntity.DataBean.RelatedVideoBean> mList;
    private String isEnd = "0";

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
        mPresenter.videoList(getContext(), subjectCategoryId, MyApplication.getMyApp().getUserId());
    }

    @Override
    public void loadSuc(RelevantVideoListEntity entry) {
        multipleStatusView.showContent();
        if (entry != null) {
            if (entry.getData() != null && entry.getData().getVideo() != null) {
                videoBean = entry.getData().getVideo();
                /*String path = null;
                try {
                    path = FastDFSUtil.generateSourceUrl(videoBean.getVideoUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                jzVideo.seekToInAdvance = videoBean.getPlayScheduleTime();
                jzVideo.setUp(videoBean.getVideoUrl(), videoBean.getVideoName());
                isCollection = videoBean.getIsCollection();
                jzVideo.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
                jzVideo.ivExtend.setOnClickListener(onClickListener);
                jzVideo.setOnStateAutoComplete(VideoPlayListActivity.this);
                Glide.with(MyApplication.getApp()).load(videoBean.getImgUrl()).into(jzVideo.thumbImageView);
                //jzVideo.thumbImageView.setImageResource(R.mipmap.empty_bg);
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

        mList = entry.getData().getRelatedVideo();
        if (entry.getData() != null) {
            if(mList != null && mList.size() > 0){
                adapter.notifyChanged(entry.getData().getRelatedVideo());
            }else{
                multipleStatusView.showEmpty();
            }
        } else {
            multipleStatusView.showEmpty();
        }
    }

    private String tag;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivExtend:
                    if (isCollection == 0) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    mPresenter.collection(VideoPlayListActivity.this,String.valueOf(videoBean.getVideoId()),tag, MyApplication.getMyApp().getUserId());
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        long currentPos = jzVideo.getCurrentPositionWhenPlaying();
        if(currentPos > 0 || isEnd.equals("1")){
            mPresenter.schedule(VideoPlayListActivity.this, String.valueOf(currentPos),subjectCategoryId, String.valueOf(videoBean.getVideoId()), MyApplication.getMyApp().getUserId());
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
    public void loadCollectionSuc(CollectionEntity entry) {
        if (tag.equals("0")) {
            isCollection = 0;
            ToastUtils.popUpToast("已取消");
        } else {
            isCollection = 1;
            ToastUtils.popUpToast("已收藏");
        }
        jzVideo.ivExtend.setImageResource(isCollection == 0 ? R.mipmap.collection_video_normal : R.mipmap.collection_video_check);
    }

    @Override
    public void loadCollectionFail() {
        if (tag.equals("0")) {
            ToastUtils.popUpToast("取消失败");
        } else {
            ToastUtils.popUpToast("收藏失败");
        }
    }

    @Override
    protected VideoPlayListPresenter onInitLogicImpl() {
        return new VideoPlayListPresenter(this);
    }

    @Override
    public void onItemClick(int position) {
        String path = "";
        try {
            path = FastDFSUtil.generateSourceUrl(mList.get(position).getVideoUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this,MicrolessonVideoPlayActivity.class);
        intent.putExtra("pathUrl",path);
        intent.putExtra("videoName",mList.get(position).getVideoName());
        intent.putExtra("videoId",mList.get(position).getVideoId());
        intent.putExtra("imgUrl",mList.get(position).getImgUrl());
        intent.putExtra("playScheduleTime",mList.get(position).getPlayScheduleTime());
        intent.putExtra("isCollection",mList.get(position).getIsCollection());
        startActivity(intent);
    }

    @OnClick(R.id.llClick)
    public void onViewClicked() {
        String url = Configs.SMALLWORK + "userId=" + MyApplication.getMyApp().getUserId() + "&subjectCategoryId=" + subjectCategoryId;
        Intent intent = new Intent(VideoPlayListActivity.this, CommonWebViewActivity.class);
        intent.putExtra("webUrl",url);
        startActivity(intent);
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
