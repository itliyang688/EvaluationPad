package com.fek12.basic.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fek12.basic.utils.click.ClickUtils;
import com.fek12.basic.view.dialog.MyDialog;
import com.fek12.basic.view.titleView.BaseTitleView;
import com.fek12.basic.view.titleView.TitleView;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: liyang
 * 2019/9/9
 */

public abstract class BaseFragment<P extends BasePresenter> extends RxFragment implements BaseView, View.OnClickListener {

    protected abstract int getLayoutResource();

    protected abstract void onInitView(Bundle savedInstanceState);

    protected abstract void onLoadDataRemote();

    protected abstract P onInitLogicImpl();

    protected LinearLayout rootView;
    protected Context mContext = null;
    protected Activity activity;
    protected P mPresenter;
    Unbinder unbinder;
    protected BackFragmentInterface backFragmentInterface;
    public abstract boolean onBackPressed();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && backFragmentInterface != null){
            onBackPressedListener();
        }
    }

    /**
     * 设置是否侵入状态栏
     *
     * @return true 不侵入系统栏  false  侵入系统栏
     */
    protected boolean getFitsSystemWindows() {
        return true;
    }

    private MyDialog myDialog;

    @Override
    public void onStart() {
        super.onStart();
        onBackPressedListener();
    }

    private void onBackPressedListener(){
        if(this.getClass().getSimpleName().equals("EvaluationContainerFragment") ||
                this.getClass().getSimpleName().equals("EvaluationListFragment")){
            backFragmentInterface.onSelectedFragment(this);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity().getClass().getSimpleName().equals("MainActivity") && getActivity() instanceof BackFragmentInterface){
            this.backFragmentInterface = (BackFragmentInterface)getActivity();
        }
        this.activity = getActivity();
        this.mContext = getActivity();
        myDialog = new MyDialog(mContext);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onLoadDataRemote();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = new LinearLayout(mContext);
        rootView.setOrientation(LinearLayout.VERTICAL);
        //View view = View.inflate(mContext, getLayoutResource(), rootView);
        View view = inflater.inflate(getLayoutResource(),rootView);
        unbinder = ButterKnife.bind(this, view);
        onInitView(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = onInitLogicImpl();
        }
        return view;
    }


    /**
     * 添加title布局
     *
     * @param titleLayout
     */
    private void addTitleLayout(View titleLayout) {
        rootView.addView(titleLayout, 0);
    }


    /**
     * 获取无包装并且添加进去的title
     */
    protected BaseTitleView setDefaultTitle() {
        BaseTitleView defaultTitle = TitleView.getTitle(activity);
        addTitleLayout(defaultTitle);
        return defaultTitle;
    }

    /**
     * 带返回按钮的title
     *
     * @param title
     */
    protected BaseTitleView setDefaultTitle(String title) {
        BaseTitleView defaultTitle = TitleView.getTitle(activity);
        defaultTitle.setMiddleTitle(title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        addTitleLayout(defaultTitle);
        return defaultTitle;
    }

    /**
     * 设置title
     * @param title
     */
    protected BaseTitleView setDefaultTitle(String title, boolean isShowBack) {
        BaseTitleView baseTitleView = setDefaultTitle();
        baseTitleView.setMiddleTitle(title, isShowBack);
        return baseTitleView;
    }

    /**
     * 带返回按钮的title
     *
     * @param title
     */
    protected BaseTitleView setDefaultTitle(String title, View.OnClickListener onClickListener) {
        BaseTitleView defaultTitle = TitleView.getTitle(activity);
        defaultTitle.setMiddleTitle(title, onClickListener);
        addTitleLayout(defaultTitle);
        return defaultTitle;
    }

    @Override
    public void showLoading() {
        myDialog.show();
    }

    @Override
    public void hideLoading() {
        myDialog.hide();
    }

    protected <T extends View> T bindView(@IdRes int id) {
        View viewById = rootView.findViewById(id);
        return (T) viewById;
    }


    /**
     * 子类实现此方法处理点击事件
     *
     * @param v
     * @param id
     */
    public void onClick(View v, int id) {
    }

    @Override
    public void onClick(View v) {
        if (ClickUtils.isClick()) {
            onClick(v, v.getId());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null && mPresenter.isViewBind())
            mPresenter.detachView();
    }

}
