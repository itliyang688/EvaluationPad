package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.utils.string.StringUtils;
import com.fek12.basic.utils.toast.ToastUtils;
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
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;
import cn.fek12.evaluation.presenter.MoreTaskSetPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.adapter.MoreTaskSetAdapter;
import cn.fek12.evaluation.view.adapter.SubjectAdapter;
import cn.fek12.evaluation.view.dialog.SelectDateDialog;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import pl.droidsonroids.gif.GifImageView;

public class MoreTasKSetActivity extends BaseActivity<MoreTaskSetPresenter> implements IMoreTaskSet.View,SubjectAdapter.OnItemClickListener,MoreTaskSetAdapter.OnItemClickListener {
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
    private MoreTaskSetAdapter taskSetAdapter;
    private List<ExaminationEntity.DataBean.RecordsBean> mList;
    private SubjectAdapter subjectAdapter;

    private String subjectId = "";
    private String taskType = "";
    private String status = "";
    private String startDate = "";
    private String endDate = "";
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
        taskSetAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(taskSetAdapter);

        subjectAdapter = new SubjectAdapter(getContext());
        subjectAdapter.setOnItemClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        subjectRecyclerView.setLayoutManager(manager);
        subjectRecyclerView.setAdapter(subjectAdapter);
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
        mPresenter.getStuSubjectByUserId(getContext(),MyApplication.getMyApp().getUserId());
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData(){
        loadView.showLoading();
        mPresenter.queryTaskPage(getContext(), MyApplication.getMyApp().getUserId(),subjectId,taskType,startDate,endDate,status,String.valueOf(currentPage),pageSize);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryTaskPage(getContext(), MyApplication.getMyApp().getUserId(),subjectId,taskType,startDate,endDate,status,String.valueOf(currentPage),pageSize);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryTaskPage(getContext(), MyApplication.getMyApp().getUserId(),subjectId,taskType,startDate,endDate,status,String.valueOf(currentPage),pageSize);
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
                if(StringUtils.isEmpty(taskType)){
                    return;
                }
                taskType  = "";
                type1All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                type1Tesk.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type1Examination.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                initData();
                break;
            case R.id.type1Tesk:
                if(taskType.equals("1")){
                    return;
                }
                taskType = "1";
                type1All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type1Tesk.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                type1Examination.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                initData();
                break;
            case R.id.type1Examination:
                if(taskType.equals("2")){
                    return;
                }
                taskType = "2";
                type1All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type1Tesk.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type1Examination.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                initData();
                break;
            case R.id.type3All:
                if(StringUtils.isEmpty(status)){
                    return;
                }
                status = "";
                type3All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                type3Complete.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type3Unfinished.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                initData();
                break;
            case R.id.type3Complete:
                if(status.equals("0")){
                    return;
                }
                status = "0";
                type3All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type3Complete.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                type3Unfinished.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                initData();
                break;
            case R.id.type3Unfinished:
                if(status.equals("1")){
                    return;
                }
                status = "1";
                type3All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type3Complete.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                type3Unfinished.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                initData();
                break;
            case R.id.type4All:
                if(StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)){
                    return;
                }
                startDate = "";
                endDate = "";
                type4All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_F7A42B));
                tvStartDate.setText("起始日期");
                tvEndDate.setText("结束日期");
                initData();
                break;
            case R.id.llStartDate:
                SelectDateDialog startDateDialog = new SelectDateDialog(getContext(), "选择起始日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if (!TextUtils.isEmpty(endDate)) {
                            if(AppUtils.dateToTime(endDate) < AppUtils.dateToTime(date)){
                                ToastUtils.popUpToast("不能大于结束时间");
                                return;
                            }
                            startDate = date;
                            tvStartDate.setText(date);
                            type4All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                            initData();
                        }else{
                            startDate = date;
                            tvStartDate.setText(date);
                        }
                    }
                });
                startDateDialog.show();
                break;
            case R.id.llEndDate:
                if (TextUtils.isEmpty(startDate)) {
                    ToastUtils.popUpToast("请选择起始日期");
                    return;
                }
                SelectDateDialog endDateDialog = new SelectDateDialog(getContext(), "选择结束日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if(AppUtils.dateToTime(date) < AppUtils.dateToTime(startDate)){
                            ToastUtils.popUpToast("不能小于开始日期");
                            return;
                        }
                        endDate = date;
                        tvEndDate.setText(date);
                        type4All.setTextColor(MoreTasKSetActivity.this.getResources().getColor(R.color.color_333));
                        initData();
                    }
                });
                endDateDialog.show();
                break;
            case R.id.gifView:
                startActivity(new Intent(MoreTasKSetActivity.this,TrainExerciseNotesActivity.class));
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


    List<SubjectModel.DataBean> subjectList;
    @Override
    public void loadSubjectSuc(SubjectModel entry) {
        subjectList = entry.getData();
        SubjectModel.DataBean dataBean = new SubjectModel.DataBean();
        dataBean.setTagColor(1);
        dataBean.setSubName("全部");
        subjectList.add(0,dataBean);
        subjectAdapter.notifyChanged(subjectList);
    }

    @Override
    public void loadSubjectEmpty() {

    }

    @Override
    public void onItemClick(String subId) {
        if(StringUtils.isEmpty(subjectId) && StringUtils.isEmpty(subId)){
            subjectId = "";
            return;
        }
        subjectId = subId;

        initData();
    }

    @Override
    public void onItemButClick(int taskStatus,int position) {
        Intent intent;
        switch (taskStatus){
            case 0://去做作业
            case 1://补交作业
            case 5://马上开始
                intent = new Intent(MoreTasKSetActivity.this, CommonNewsWebViewActivity.class);
                intent.putExtra("typePage",CommonNewsWebViewActivity.TASKPAGE);
                intent.putExtra("webUrl",Configs.TASK + "userId=" + MyApplication.getMyApp().getUserId() + "&taskId=" + mList.get(position).getTaskId() + "&qrParem=" + mList.get(position).getQrId());
                MoreTasKSetActivity.this.startActivity(intent);
                break;
            case 2://查看结果
                intent = new Intent(MoreTasKSetActivity.this, CommonNewsWebViewActivity.class);
                intent.putExtra("typePage",CommonNewsWebViewActivity.TASKPAGE);
                intent.putExtra("webUrl",Configs.LEARNING + "userId=" + MyApplication.getMyApp().getUserId() + "&taskId=" + mList.get(position).getTaskId());
                MoreTasKSetActivity.this.startActivity(intent);
                break;
            case 3://强化训练
                intent = new Intent(MoreTasKSetActivity.this, CommonNewsWebViewActivity.class);
                intent.putExtra("typePage",CommonNewsWebViewActivity.ANSWER);
                intent.putExtra("webUrl",Configs.INTENSIVE + "userId=" + MyApplication.getMyApp().getUserId() + "&taskDrillId=" + mList.get(position).getDrillId());
                intent.putExtra("drillId",String.valueOf(mList.get(position).getDrillId()));
                MoreTasKSetActivity.this.startActivity(intent);
                break;
            case 4://强化训练解析
                intent = new Intent(MoreTasKSetActivity.this, CommonNewsWebViewActivity.class);
                intent.putExtra("typePage",CommonNewsWebViewActivity.ANSWER);
                intent.putExtra("webUrl",Configs.STRENGTHENING + "taskDrillId=" + mList.get(position).getDrillId());
                MoreTasKSetActivity.this.startActivity(intent);
                break;
        }
    }
}
