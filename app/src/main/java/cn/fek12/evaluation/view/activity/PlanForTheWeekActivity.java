package cn.fek12.evaluation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
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
public class PlanForTheWeekActivity extends BaseActivity implements MyJzvdStd.OnStateAutoComplete {
    @BindView(R.id.jzVideo)
    MyJzvdStd jzVideo;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    private String subjectCategoryId;
    private int isCollection;
    private String isEnd = "0";

    @Override
    public int setLayoutResource() {
        return R.layout.plan_the_week_activity;
    }

    @Override
    protected void onInitView() {
        //setEmptyTitle();
        //mViewRoot.setBackgroundResource(R.mipmap.plan_the_week_bg);
        rootView.setPadding(0, AppUtils.getStatusBarHeight(PlanForTheWeekActivity.this), 0, 0);
        subjectCategoryId = getIntent().getStringExtra("subjectCategoryId");
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
                case R.id.ivExtend:
                    if (isCollection == 0) {
                        tag = "1";
                    } else {
                        tag = "0";
                    }
                    //mPresenter.collection(PlanForTheWeekActivity.this,String.valueOf(videoBean.getVideoId()),tag, MyApplication.getMyApp().getUserId());
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        jzVideo.goOnPlayOnPause();
        long currentPos = jzVideo.getCurrentPositionWhenPlaying();
        if (currentPos > 0 || isEnd.equals("1")) {
            //mPresenter.schedule(PlanForTheWeekActivity.this, String.valueOf(currentPos),subjectCategoryId, String.valueOf(videoBean.getVideoId()), MyApplication.getMyApp().getUserId());
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Jzvd.releaseAllVideos();
        super.onDestroy();
    }


    @Override
    protected VideoPlayListPresenter onInitLogicImpl() {
        return null;
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
}
