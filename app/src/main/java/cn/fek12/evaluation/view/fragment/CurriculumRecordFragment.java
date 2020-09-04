package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ICurriculemRecord;
import cn.fek12.evaluation.model.entity.CurriculumEntity;
import cn.fek12.evaluation.presenter.CurriculumRecordPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.DisUtil;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.PopupWindow.SubjectAllPopupWindow;
import cn.fek12.evaluation.view.activity.MicrolessonVideoPlayActivity;
import cn.fek12.evaluation.view.adapter.CurriculumRecordAdapter;
import cn.fek12.evaluation.view.dialog.SelectDateDialog;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonRecordFragment
 * @Description:
 * @CreateDate: 2019/11/20 15:18
 */
public class CurriculumRecordFragment extends BaseFragment<CurriculumRecordPresenter> implements ICurriculemRecord.View,CurriculumRecordAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvSubject)
    TextView tvSubject;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.tvStartDate)
    TextView tvStartDate;
    @BindView(R.id.llStartDate)
    LinearLayout llStartDate;
    @BindView(R.id.tvEndDate)
    TextView tvEndDate;
    @BindView(R.id.llEndDate)
    LinearLayout llEndDate;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    @BindView(R.id.llContainSubject)
    LinearLayout llContainSubject;
    private CurriculumRecordAdapter adapter;
    private SubjectAllPopupWindow subjectPopupWindow;
    private int mPageType;
    private int currentPage = 1;
    private boolean isLoadMore = false;
    private String startDate = null;
    private String endDate = null;
    private String subject = null;

    public CurriculumRecordFragment(int pageType) {
        mPageType = pageType;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.curriculum_record_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

        llSubject.setOnClickListener(this);
        llStartDate.setOnClickListener(this);
        llEndDate.setOnClickListener(this);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(getContext());
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        adapter = new CurriculumRecordAdapter(getContext());
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApp().getUserId(), String.valueOf(currentPage));
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApp().getUserId(), String.valueOf(currentPage));
        }
    };

    @Override
    public void loginSuc(CurriculumEntity entry) {
        CurriculumEntity.DataBean.PageInfoBean pageInfoBean = entry.getData().getPage_info();
        List<CurriculumEntity.DataBean.VideosBean> list = entry.getData().getVideos();
        if (pageInfoBean.getTotalCount() == 0) {
            loadView.showEmpty();
            return;
        }
        loadView.showContent();
        if (pageInfoBean.getTotalPage() > currentPage) {
            refreshLayout.setEnableLoadmore(true);
        } else {
            refreshLayout.setEnableLoadmore(false);
        }
        if (list != null && list.size() > 0) {
            adapter.notifyChanged(list, isLoadMore);
            if (!isLoadMore) {
                recyclerView.smoothScrollToPosition(0);
            }
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loginEmpty() {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void onClick(View v, int id) {
        switch (id) {
            case R.id.llStartDate:
                SelectDateDialog startDateDialog = new SelectDateDialog(getContext(), "选择起始日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if (!TextUtils.isEmpty(endDate)) {
                            if(AppUtils.dateToTime(endDate) < AppUtils.dateToTime(date)){
                                ToastUtils.popUpToast("不能大于结束时间");
                                return;
                            }
                            loadView.showLoading();
                            isLoadMore = false;
                            currentPage = 1;
                            mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApp().getUserId(), String.valueOf(currentPage));
                        }
                        startDate = date;
                        tvStartDate.setText(date);
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
                            ToastUtils.popUpToast("不能小于开始时间");
                            return;
                        }
                        endDate = date;
                        tvEndDate.setText(date);

                        loadView.showLoading();
                        isLoadMore = false;
                        currentPage = 1;
                        mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApp().getUserId(), String.valueOf(currentPage));
                    }
                });
                endDateDialog.show();
                break;
            case R.id.llSubject:
                subjectPopupWindow = new SubjectAllPopupWindow(getContext(), new SubjectAllPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        loadView.showLoading();
                        isLoadMore = false;
                        currentPage = 1;
                        tvSubject.setText(subjectName);
                        subject = subjectId;
                        if(subjectId.equals("0")){
                            subject = null;
                        }
                        mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApp().getUserId(), String.valueOf(currentPage));
                    }
                },0);

                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow.setImageResource(R.mipmap.rise_icon);
                int tv_width = llContainSubject.getWidth();//获取对应的控件view宽度px值
                int pop_width = DisUtil.dp2px(getContext(),150);
                int width = (tv_width - pop_width) / 2;//获取x轴偏移量px
                subjectPopupWindow.showAsDropDown(llContainSubject, width, 0);//设置x轴偏移量：注意单位为px
                //subjectPopupWindow.showAsDropDown(llContainSubject, -155, 0);
                //subjectPopupWindow.showAtLocation(llContainSubject, Gravity.CENTER_VERTICAL,0,0);
                subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivArrow.setImageResource(R.mipmap.lower_icon);
                    }
                });
                break;
        }
    }

    @Override
    protected void onLoadDataRemote() {
        loadView.showLoading();
        mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApp().getUserId(), String.valueOf(currentPage));
    }

    @Override
    protected CurriculumRecordPresenter onInitLogicImpl() {
        return new CurriculumRecordPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onItemClick(CurriculumEntity.DataBean.VideosBean videoBean) {
        /*String path = "";
        try {
            path = FastDFSUtil.generateSourceUrl(videoBean.getVideoUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Intent intent = new Intent(mContext, MicrolessonVideoPlayActivity.class);
        intent.putExtra("pathUrl",videoBean.getVideoUrl());
        intent.putExtra("videoName",videoBean.getVideoName());
        intent.putExtra("videoId",videoBean.getVideoId());
        intent.putExtra("imgUrl",videoBean.getImgUrl());
        intent.putExtra("playScheduleTime",videoBean.getPlayScheduleTime());
        intent.putExtra("isCollection",videoBean.getIsCollection());
        mContext.startActivity(intent);
    }
}
