package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.lang.reflect.Field;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.presenter.EvaluationPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.PopupWindow.MenuPopupWindow;
import cn.fek12.evaluation.view.activity.MenuDialogActivity;
import cn.fek12.evaluation.view.adapter.PresentationItemSection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页测评页面
 * @CreateDate: 2019/10/23 15:12
 */
public class PresentationFragment extends BaseFragment<EvaluationPresenter> implements IEvaluation.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private SectionedRecyclerViewAdapter leftAdapter;
    private MenuPopupWindow popupWindow;

    @Override
    protected int getLayoutResource() {
        return R.layout.presentation_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("报告", false).addRightImgButton(R.mipmap.more_icon, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow = new MenuPopupWindow(getActivity(), null);
                AppUtils.fitPopupWindowOverStatusBar(popupWindow,true);
                popupWindow.showAtLocation(rootView,
                        Gravity.RIGHT | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });



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

        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {

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
        addSection();
    }

    private void addSection() {
        leftAdapter.addSection("threeDays", new PresentationItemSection("近三天", new PresentationItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {

            }
        }));

        leftAdapter.addSection("aweek", new PresentationItemSection("一周内", new PresentationItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {

            }
        }));

        leftAdapter.addSection("earlier", new PresentationItemSection("较早", new PresentationItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {

            }
        }));
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    public void loginSuc(HomeEvaluationDeta entry) {

    }

    @Override
    protected EvaluationPresenter onInitLogicImpl() {
        return new EvaluationPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void loginFail(String msg) {
    }

    @Override
    public void showBaner() {

    }
}
