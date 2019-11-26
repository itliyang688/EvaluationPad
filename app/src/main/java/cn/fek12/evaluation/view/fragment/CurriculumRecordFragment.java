package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ICurriculemRecord;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.CurriculumEntity;
import cn.fek12.evaluation.presenter.CurriculumRecordPresenter;
import cn.fek12.evaluation.utils.DialogUtils;
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
public class CurriculumRecordFragment extends BaseFragment<CurriculumRecordPresenter> implements ICurriculemRecord.View {
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
    @BindView(R.id.llContain)
    LinearLayout llContain;
    @BindView(R.id.selectSubject)
    RelativeLayout selectSubject;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    private CurriculumRecordAdapter adapter;
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
        selectSubject.setOnClickListener(this);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(getContext());
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        adapter = new CurriculumRecordAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApplication().getUserId(), String.valueOf(currentPage));
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApplication().getUserId(), String.valueOf(currentPage));
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
            case R.id.selectSubject:
                selectSubject.setVisibility(View.GONE);
                break;
            case R.id.llStartDate:
                SelectDateDialog startDateDialog = new SelectDateDialog(getContext(), "选择起始日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
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
                        endDate = date;
                        tvEndDate.setText(date);

                        loadView.showLoading();
                        isLoadMore = false;
                        currentPage = 1;
                        mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApplication().getUserId(), String.valueOf(currentPage));
                    }
                });
                endDateDialog.show();
                break;
            case R.id.llSubject:
                llContain.removeAllViews();
                selectSubject.setVisibility(View.VISIBLE);
                List<ChildSectionEntity> mList = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    if (i == 0) {
                        ChildSectionEntity sectionEntity = new ChildSectionEntity();
                        sectionEntity.setName("全部");
                        mList.add(sectionEntity);
                    } else {
                        ChildSectionEntity sectionEntity = new ChildSectionEntity();
                        sectionEntity.setName("数学" + i);
                        mList.add(sectionEntity);
                    }
                }
                for(int i = 0; i < mList.size(); i++){
                    View viewItem = LayoutInflater.from(mContext).inflate(R.layout.subject_itme, null);
                    TextView tvName = viewItem.findViewById(R.id.tvName);
                    tvName.setText(mList.get(i).getName());
                    int finalI = i;
                    viewItem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            tvName.setTextColor(mContext.getResources().getColor(R.color.white));
                            selectSubject.setVisibility(View.GONE);
                        }
                    });
                    llContain.addView(viewItem);
                }

                /*DialogUtils.subjectSelectDialog(getContext(), list, new DialogUtils.OnSelectSubjectItmeListener() {
                    @Override
                    public void onSelectItme(int pos) {
                        tvSubject.setText(list.get(pos).getName());
                    }
                });*/

                break;
        }
    }

    @Override
    protected void onLoadDataRemote() {
        loadView.showLoading();
        mPresenter.courseRecord(getContext(), startDate, endDate, subject, MyApplication.getMyApplication().getUserId(), String.valueOf(currentPage));
    }

    @Override
    protected CurriculumRecordPresenter onInitLogicImpl() {
        return new CurriculumRecordPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
