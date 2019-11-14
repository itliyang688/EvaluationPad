package cn.fek12.evaluation.view.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fek12.basic.base.BaseActivity;
import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.IVideoPlayList;
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;
import cn.fek12.evaluation.presenter.VideoPlayListPresenter;
import cn.fek12.evaluation.view.adapter.RelevantVideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import cn.fek12.evaluation.view.widget.MyJzvdStd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: VideoPlayListActivity
 * @Description:
 * @CreateDate: 2019/11/13 17:32
 */
public class VideoPlayListActivity extends BaseActivity<VideoPlayListPresenter> implements IVideoPlayList.View {
    @BindView(R.id.jzVideo)
    MyJzvdStd jzVideo;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private String subjectCategoryId;
    private RelevantVideoAdapter adapter;

    @Override
    public int setLayoutResource() {
        return R.layout.video_play_list_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle();
        subjectCategoryId = getIntent().getStringExtra("subjectCategoryId");
        adapter = new RelevantVideoAdapter(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void loadSuc(RelevantVideoListEntity entry) {

    }

    @Override
    public void loadEmpty() {

    }

    @Override
    protected VideoPlayListPresenter onInitLogicImpl() {
        return new VideoPlayListPresenter(this);
    }

}
