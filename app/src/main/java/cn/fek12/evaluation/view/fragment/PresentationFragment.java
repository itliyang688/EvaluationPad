package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.AWeekEntity;
import cn.fek12.evaluation.model.entity.EarlierEntity;
import cn.fek12.evaluation.model.entity.PresentationEntity;
import cn.fek12.evaluation.presenter.PresentationPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.PopupWindow.MenuPopupWindow;
import cn.fek12.evaluation.view.activity.AnswerWebViewActivity;
import cn.fek12.evaluation.view.adapter.PresentationAweekItemSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页测评页面
 * @CreateDate: 2019/10/23 15:12
 */
public class PresentationFragment extends BaseFragment<PresentationPresenter> implements PresentationPresenter.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private SectionedRecyclerViewAdapter leftAdapter;
    private MenuPopupWindow popupWindow;
    private boolean isLoadMore = false;
    private String grade = null;
    private String semester = null;
    private String subject = null;
    private String textbook = null;
    private String userType = null;
    private int currentPage = 1;
    private String pageSize = "18";
    private List<PresentationEntity> daylist;
    private List<PresentationEntity> aweeklist;

    @Override
    protected int getLayoutResource() {
        return R.layout.presentation_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("报告", false).addRightImgButton(R.mipmap.more_icon, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow = new MenuPopupWindow(getActivity(), new MenuPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String gradeId, String semesterId, String subjectId, String textbookId,String type) {
                        isLoadMore = false;
                        currentPage = 1;
                        grade = gradeId;
                        semester = semesterId;
                        subject = subjectId;
                        textbook = textbookId;
                        userType = type;
                        multipleStatusView.showLoading();
                        mPresenter.queryAWeek(getContext(), grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType);
                    }
                });
                popupWindow.setClippingEnabled(false);
                AppUtils.fitPopupWindowOverStatusBar(popupWindow, true);
                popupWindow.showAtLocation(rootView,
                        Gravity.RIGHT | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(getActivity());
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        //LoadingView loadingView = new LoadingView(getActivity());
        //refreshLayout.setHeaderView(progressView);
        refreshLayout.setBottomView(bottomProgressView);
        initLeftRecycler();
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryEarlier(getContext(),grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType,String.valueOf(currentPage),pageSize);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryAWeek(getContext(), grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType);
        }
    };

    private void initLeftRecycler() {
        leftAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 6);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 6;
                } else {
                    return 1;
                }
            }
        });
        recycler.setLayoutManager(manager);
        recycler.setAdapter(leftAdapter);
    }


    @Override
    protected void onLoadDataRemote() {
        multipleStatusView.showLoading();
        mPresenter.queryAWeek(getContext(), grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType);
    }

    @Override
    protected PresentationPresenter onInitLogicImpl() {
        return new PresentationPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void loadAWeekSuc(AWeekEntity entry) {
        leftAdapter.removeAllSections();
        daylist = entry.getData().getDay();
        if (daylist != null && daylist.size() > 0) {
            multipleStatusView.showContent();
            leftAdapter.addSection("threeDays", new PresentationAweekItemSection(1,daylist, getContext(), "近三天", new PresentationAweekItemSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    /**跳转页面答题*/
                    Intent intent = new Intent(getContext(), AnswerWebViewActivity.class);
                    intent.putExtra("isanswered",1);
                    intent.putExtra("paperId",daylist.get(pos).getId());
                    intent.putExtra("paperResult",daylist.get(pos).getPaperResultId());
                    startActivity(intent);
                }
            }));
        }
        aweeklist = entry.getData().getWeek();
        if (aweeklist != null && aweeklist.size() > 0) {
            multipleStatusView.showContent();
            leftAdapter.addSection("aweek", new PresentationAweekItemSection(1,aweeklist, getContext(), "一周内", new PresentationAweekItemSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    /**跳转页面答题*/
                    Intent intent = new Intent(getContext(), AnswerWebViewActivity.class);
                    intent.putExtra("isanswered",1);
                    intent.putExtra("paperId",aweeklist.get(pos).getId());
                    intent.putExtra("paperResult",aweeklist.get(pos).getPaperResultId());
                    startActivity(intent);
                }
            }));

        }
        mPresenter.queryEarlier(getContext(),grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType,String.valueOf(currentPage),pageSize);
    }

    @Override
    public void loadEarlierSuc(EarlierEntity entry) {
        EarlierEntity.DataBean.PageInfoBean pageInfoBean = entry.getData().getPage_info();
        List<PresentationEntity> list = entry.getData().getPapers();
        if(pageInfoBean.getTotalCount() == 0){
            isEmpty();
            return;
        }
        multipleStatusView.showContent();
        if(pageInfoBean.getTotalPage() > currentPage){
            refreshLayout.setEnableLoadmore(true);
        }else{
            refreshLayout.setEnableLoadmore(false);
        }
        if(list != null && list.size() > 0){
            if(isLoadMore){
                PresentationAweekItemSection itemSection = (PresentationAweekItemSection) leftAdapter.getSection("earlier");
                itemSection.updateAndAddList(list,isLoadMore);
                //leftAdapter.getAdapterForSection("earlier").notifyAllItemsChanged("payloads");
            }else{
                leftAdapter.addSection("earlier", new PresentationAweekItemSection( 1, list, getContext(), "较早", new PresentationAweekItemSection.OnSelectItmeListener() {
                                    @Override
                                    public void onSelectItme(int pos) {
                                        /**跳转页面答题*/
                                        Intent intent = new Intent(getContext(), AnswerWebViewActivity.class);
                                        intent.putExtra("isanswered",1);
                                        intent.putExtra("paperId",list.get(pos).getId());
                                        intent.putExtra("paperResult",list.get(pos).getPaperResultId());
                                        startActivity(intent);
                                    }
                                }));
            }
        }

        leftAdapter.notifyDataSetChanged();

        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadAWeekFail(String msg) {
        leftAdapter.removeAllSections();
        /**一周或三天报告请求失败还要去请求较早的报告*/
        mPresenter.queryEarlier(getContext(),grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType,String.valueOf(currentPage),pageSize);
    }

    @Override
    public void loadEarlierFail(String msg) {
        isEmpty();
    }

    @Override
    public void loadAWeekEmpty() {
        multipleStatusView.showEmpty();
        if(daylist != null){
            daylist.clear();
        }
        if(aweeklist != null){
            aweeklist.clear();
        }
        isLoadMore = false;
        currentPage = 1;
        leftAdapter.removeAllSections();
        /**一周或三天报告请求失败还要去请求较早的报告*/
        mPresenter.queryEarlier(getContext(),grade, semester, subject, textbook,  MyApplication.getMyApplication().getUserId(), userType,String.valueOf(currentPage),pageSize);
    }

    @Override
    public void loadDictionaryEmpty() {

    }

    @Override
    public void loadEarlierEmpty() {
        isEmpty();
    }

    private void isEmpty(){
        boolean isEmpty = false;
        if(daylist != null && daylist.size() > 0){
            isEmpty = true;
        }
        if(aweeklist != null && aweeklist.size() > 0){
            isEmpty = true;
        }
        if(!isEmpty){
            multipleStatusView.showEmpty();
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }
}
