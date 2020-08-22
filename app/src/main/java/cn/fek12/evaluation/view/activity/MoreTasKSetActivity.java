package cn.fek12.evaluation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMoreTaskSet;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.presenter.MoreTaskSetPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.adapter.MoreTaskSetAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import pl.droidsonroids.gif.GifImageView;

public class MoreTasKSetActivity extends BaseActivity<MoreTaskSetPresenter> implements IMoreTaskSet.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    @BindView(R.id.rlLeftBack)
    RelativeLayout rlLeftBack;
    @BindView(R.id.llTagList)
    LinearLayout llTagList;
    @BindView(R.id.type1All)
    TextView type1All;
    @BindView(R.id.type1Tesk)
    TextView type1Tesk;
    @BindView(R.id.type1Examination)
    TextView type1Examination;
    @BindView(R.id.subjectRecyclerView)
    RecyclerView subjectRecyclerView;
    @BindView(R.id.type3All)
    TextView type3All;
    @BindView(R.id.type3Complete)
    TextView type3Complete;
    @BindView(R.id.type3Unfinished)
    TextView type3Unfinished;
    @BindView(R.id.type4All)
    TextView type4All;
    @BindView(R.id.tvStartDate)
    TextView tvStartDate;
    @BindView(R.id.llStartDate)
    LinearLayout llStartDate;
    @BindView(R.id.tvEndDate)
    TextView tvEndDate;
    @BindView(R.id.llEndDate)
    LinearLayout llEndDate;
    @BindView(R.id.gifView)
    GifImageView gifView;
    private SectionedRecyclerViewAdapter leftAdapter;
    private MoreTaskSetAdapter taskSetAdapter;
    private List<ExaminationEntity.DataBean.RecordsBean> mList;

    private String subjectId;
    private String taskType;
    private String status;
    private String startDate;
    private String endDate;
    private String current;
    private String size;
    private int currentPage = 1;
    private String pageSize = "30";
    private boolean isLoadMore = false;


    @Override
    public int setLayoutResource() {
        return R.layout.more_task_activity;
    }

    @Override
    protected void onInitView() {
        rlLeftBack.setPadding(0, AppUtils.getStatusBarHeight(MoreTasKSetActivity.this), 10, 0);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        taskSetAdapter = new MoreTaskSetAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskSetAdapter);

    }

    @Override
    protected MoreTaskSetPresenter onInitLogicImpl() {
        return new MoreTaskSetPresenter(this, getContext());
    }
    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    protected void onLoadData() {
        loadView.showLoading();
        mPresenter.queryTaskPage(getContext(), MyApplication.getMyApp().getUserId(),subjectId,taskType,startDate,endDate,String.valueOf(currentPage),pageSize);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rlLeftBack, R.id.type1All, R.id.type1Tesk, R.id.type1Examination, R.id.type3All, R.id.type3Complete, R.id.type3Unfinished, R.id.type4All, R.id.llStartDate, R.id.llEndDate, R.id.gifView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlLeftBack:
                MoreTasKSetActivity.this.finish();
                break;
            case R.id.type1All:
                break;
            case R.id.type1Tesk:
                break;
            case R.id.type1Examination:
                break;
            case R.id.type3All:
                break;
            case R.id.type3Complete:
                break;
            case R.id.type3Unfinished:
                break;
            case R.id.type4All:
                break;
            case R.id.llStartDate:
                break;
            case R.id.llEndDate:
                break;
            case R.id.gifView:
                break;
        }
    }

    @Override
    public void loadExaminationSuc(ExaminationEntity entry) {
        if(entry.getData().getPages() == 0){
            loadView.showEmpty();
            return;
        }
        List<ExaminationEntity.DataBean.RecordsBean> recordsBeans = entry.getData().getRecords();
        loadView.showContent();
        if(entry.getData().getPages() > currentPage){
            refreshLayout.setEnableLoadmore(true);
        }else{
            refreshLayout.setEnableLoadmore(false);
        }
        if(recordsBeans != null && recordsBeans.size() > 0){
            taskSetAdapter.notifyChanged(recordsBeans,isLoadMore);
            if(isLoadMore){
                mList.addAll(recordsBeans);
            }else{
                mList = recordsBeans;
            }
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadExaminationEmpty() {
        loadView.showEmpty();
    }
}
