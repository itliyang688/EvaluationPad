package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fek12.basic.base.BaseFragment;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.zakariya.stickyheaders.OnStickyChangeListener;
import org.zakariya.stickyheaders.StickyHeadContainer;
import org.zakariya.stickyheaders.StickyItemDecoration;

import java.util.ArrayList;
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
import cn.fek12.evaluation.view.activity.ConqueredActivity;
import cn.fek12.evaluation.view.adapter.PresentationNewsAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页测评页面
 * @CreateDate: 2019/10/23 15:12
 */
public class PromoteNewsFragment extends BaseFragment<PresentationPresenter> implements PresentationPresenter.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.rooView)
    LinearLayout rooView;
    @BindView(R.id.ivIcon)
    ImageView ivIcon;
    @BindView(R.id.header)
    TextView header;
    @BindView(R.id.rlContain)
    RelativeLayout rlContain;
    //private SectionedRecyclerViewAdapter leftAdapter;
    //private Presentation1Adapter adapter;
    private PresentationNewsAdapter adapter;
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
    private List<PresentationEntity> earlierList;
    @BindView(R.id.shc_pictrues)
    StickyHeadContainer stickyHeadContainer;

    @Override
    protected int getLayoutResource() {
        return R.layout.promoter_news_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("提升", false).addRightImgButton(R.mipmap.more_icon, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow = new MenuPopupWindow(getActivity(), new MenuPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String gradeId, String semesterId, String subjectId, String textbookId, String type) {
                        isLoadMore = false;
                        currentPage = 1;
                        grade = gradeId;
                        semester = semesterId;
                        subject = subjectId;
                        textbook = textbookId;
                        userType = type;
                        multipleStatusView.showLoading();
                        mPresenter.queryAWeek(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType);
                    }
                });
                //popupWindow.setClippingEnabled(false);
                AppUtils.fitPopupWindowOverStatusBar(popupWindow, true);
                popupWindow.showAtLocation(rootView,
                        Gravity.RIGHT | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(getActivity());
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setOverScrollHeight(0);
        initLeftRecycler();
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryEarlier(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType, String.valueOf(currentPage), pageSize);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryAWeek(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType);
        }

        @Override
        public void onPullingDown(TwinklingRefreshLayout refreshLayout, float fraction) {
            stickyHeadContainer.reset();
            stickyHeadContainer.setVisibility(View.INVISIBLE);
            super.onPullingDown(refreshLayout, fraction);

        }
    };


    private void initLeftRecycler() {

        StickyItemDecoration stickyItemDecoration = new StickyItemDecoration(stickyHeadContainer, PresentationEntity.PICTURE_TITLE);
        stickyItemDecoration.setOnStickyChangeListener(new OnStickyChangeListener() {
            @Override
            public void onScrollable(int offset) {
                stickyHeadContainer.scrollChild(offset);
                stickyHeadContainer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onInVisible() {
                stickyHeadContainer.reset();
                stickyHeadContainer.setVisibility(View.INVISIBLE);
            }
        });

        stickyHeadContainer.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                List<PresentationEntity> listModels = adapter.getData();
                if (listModels.size() > pos) {
                    if(listModels.get(pos).getTitleType() == 1){
                        header.setText("近三天");
                        header.setTextColor(Color.parseColor("#6B400D"));
                        ivIcon.setImageResource(R.mipmap.three_days_icon);
                        rlContain.setBackgroundResource(R.drawable.bg_three_days_header);
                    }else if(listModels.get(pos).getTitleType() == 2){
                        header.setText("近一周");
                        header.setTextColor(Color.parseColor("#188C6A"));
                        ivIcon.setImageResource(R.mipmap.aweek_icon);
                        rlContain.setBackgroundResource(R.drawable.bg_aweek_header);
                    }else if(listModels.get(pos).getTitleType() == 3){
                        header.setText("较早");
                        header.setTextColor(Color.parseColor("#753C09"));
                        ivIcon.setImageResource(R.mipmap.earlier);
                        rlContain.setBackgroundResource(R.drawable.bg_earlier_header);
                    }
                }
            }
        });
        adapter = new PresentationNewsAdapter(null, PresentationNewsAdapter.PROMOTE);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PresentationEntity entity = (PresentationEntity) adapter.getItem(position);
                if(entity.getTitleType() == 0){
                    Intent intent = new Intent(mContext, ConqueredActivity.class);
                    intent.putExtra("paperResultId",String.valueOf(entity.getPaperResultId()));
                    mContext.startActivity(intent);
                }
            }
        });
        GridLayoutManager manager = new GridLayoutManager(getContext(), 6);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(stickyItemDecoration);
        //SpaceDecoration spaceDecoration = new SpaceDecoration(DisUtil.dp2px(mContext, 10));
        //spaceDecoration.setPaddingStart(false);
        //recycler.addItemDecoration(spaceDecoration);
        recycler.setAdapter(adapter);
        adapter.bindToRecyclerView(recycler);
    }


    @Override
    protected void onLoadDataRemote() {
        multipleStatusView.showLoading();
        mPresenter.queryAWeek(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType);
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
        daylist = entry.getData().getDay();
        if (daylist != null && daylist.size() > 0) {
            multipleStatusView.showContent();
        }
        aweeklist = entry.getData().getWeek();
        if (aweeklist != null && aweeklist.size() > 0) {
            multipleStatusView.showContent();
        }
        mPresenter.queryEarlier(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType, String.valueOf(currentPage), pageSize);
    }

    @Override
    public void loadEarlierSuc(EarlierEntity entry) {
        EarlierEntity.DataBean.PageInfoBean pageInfoBean = entry.getData().getPage_info();
        earlierList = entry.getData().getPapers();
        if (pageInfoBean.getTotalCount() == 0) {
            isEmpty();
            return;
        }
        multipleStatusView.showContent();
        if (pageInfoBean.getTotalPage() > currentPage) {
            refreshLayout.setEnableLoadmore(true);
        } else {
            refreshLayout.setEnableLoadmore(false);
        }
        List<PresentationEntity> presentationModels = new ArrayList<>();
        if (earlierList != null && earlierList.size() > 0) {
            if (isLoadMore) {
                adapter.notifyChanged(earlierList, isLoadMore);
            } else {
                //添加标题
                PresentationEntity title = new PresentationEntity();
                title.setType(PresentationEntity.PICTURE_TITLE);
                title.setTitleType(1);
                presentationModels.add(title);
                if (daylist != null && daylist.size() > 0) {
                    for (PresentationEntity model : daylist) {
                        //添加数据
                        presentationModels.add(model);
                    }
                }

                //添加标题
                PresentationEntity title2 = new PresentationEntity();
                title2.setType(PresentationEntity.PICTURE_TITLE);
                title2.setTitleType(2);
                presentationModels.add(title2);
                if (aweeklist != null && aweeklist.size() > 0) {
                    for (PresentationEntity model : aweeklist) {
                        //添加数据
                        presentationModels.add(model);
                    }
                }

                //添加标题
                PresentationEntity title3 = new PresentationEntity();
                title3.setType(PresentationEntity.PICTURE_TITLE);
                title3.setTitleType(3);
                presentationModels.add(title3);
                for (PresentationEntity model : earlierList) {
                    //添加数据
                    presentationModels.add(model);
                }

                adapter.notifyChanged(presentationModels, isLoadMore);
            }
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadAWeekFail(String msg) {
        isLoadMore = false;
        currentPage = 1;
        /**一周或三天报告请求失败还要去请求较早的报告*/
        mPresenter.queryEarlier(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType, String.valueOf(currentPage), pageSize);
    }

    @Override
    public void loadEarlierFail(String msg) {
        isEmpty();
    }

    @Override
    public void loadAWeekEmpty() {
        multipleStatusView.showEmpty();
        if (daylist != null) {
            daylist.clear();
        }
        if (aweeklist != null) {
            aweeklist.clear();
        }

        isLoadMore = false;
        currentPage = 1;
        /**一周或三天报告请求失败还要去请求较早的报告*/
        mPresenter.queryEarlier(getContext(), grade, semester, subject, textbook, MyApplication.getMyApp().getUserId(), userType, String.valueOf(currentPage), pageSize);
    }

    @Override
    public void loadDictionaryEmpty() {

    }

    @Override
    public void loadEarlierEmpty() {
        isEmpty();
    }

    private void isEmpty() {
        List<PresentationEntity> presentationModels = new ArrayList<>();
        boolean isEmpty = false;
        //添加标题
        PresentationEntity title = new PresentationEntity();
        title.setType(1);
        title.setTitleType(1);
        presentationModels.add(title);
        if (daylist != null && daylist.size() > 0) {
            isEmpty = true;
            for (PresentationEntity model : daylist) {
                //添加数据
                presentationModels.add(model);
            }
        }

        //添加标题
        PresentationEntity title2 = new PresentationEntity();
        title2.setType(1);
        title2.setTitleType(2);
        presentationModels.add(title2);
        if (aweeklist != null && aweeklist.size() > 0) {
            isEmpty = true;
            for (PresentationEntity model : aweeklist) {
                //添加数据
                presentationModels.add(model);
            }
        }

        //添加标题
        PresentationEntity title3 = new PresentationEntity();
        title3.setType(1);
        title3.setTitleType(3);
        presentationModels.add(title3);
        if (!isEmpty) {
            multipleStatusView.showEmpty();
        } else {
            adapter.notifyChanged(presentationModels, isLoadMore);
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }
}
