package com.fek12.basic.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.fek12.basic.R;
import com.fek12.basic.application.BaseApplication;
import com.fek12.basic.utils.immersion.BarHide;
import com.fek12.basic.utils.immersion.ImmersionBar;
import com.fek12.basic.utils.toast.ToastUtils;
import com.fek12.basic.view.dialog.MyDialog;
import com.fek12.basic.view.titleView.BaseTitleView;
import com.fek12.basic.view.titleView.TitleView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author: liyang
 * 2019/9/9
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements BaseView {

    protected LinearLayout mViewRoot;

    public abstract int setLayoutResource();

    protected abstract void onInitView();

    protected abstract void onLoadData();
    Unbinder unbinder;


    /**
     * 设置是否侵入状态栏
     * @return true 不侵入系统栏  false  侵入系统栏
     */
    protected boolean getFitsSystemWindows() {
        return true;
    }

    protected void setListener() {
    }

    protected void onCreateSuccess(Bundle savedInstanceState) {

    }

    protected P mPresenter;

    public BaseActivity<P> getContext() {
        return this;
    }

    private MyDialog myDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //overridePendingTransition(R.anim.enter_in_up, R.anim.enter_in_down);
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        mViewRoot = new LinearLayout(this);
        mViewRoot.setOrientation(LinearLayout.VERTICAL);
        boolean fitsSystemWindows = getFitsSystemWindows();
        immersionInit(fitsSystemWindows ? R.color.transparent : R.color.transparent);
        mViewRoot.setFitsSystemWindows(fitsSystemWindows);
        setContentView(mViewRoot);
        getLayoutInflater().inflate(setLayoutResource(), mViewRoot);
        BaseApplication.getApp().addActivity(this);
        myDialog = new MyDialog(this);
        unbinder = ButterKnife.bind(this);

        mPresenter = onInitLogicImpl();
        onInitView();
        onCreateSuccess(savedInstanceState);
        onLoadData();
        setListener();
    }

    /**
     * 添加title布局
     * @param titleLayout
     */
    protected void addTitleLayout(View titleLayout) {
        mViewRoot.addView(titleLayout, 0);
    }

    /**
     * 获取无包装并且添加进去的title
     */
    protected BaseTitleView setDefaultTitle() {
        BaseTitleView defaultTitle = TitleView.getTitle(this);
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
     * 设置空title
     * @param
     */
    protected BaseTitleView setEmptyTitle() {
        BaseTitleView baseTitleView = setDefaultTitle("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseTitleView.hideTitle();
        return baseTitleView;
    }

    /**
     * 设置空title
     * @param
     */
    protected BaseTitleView setEmptyTitle(View.OnClickListener onClickListener) {
        BaseTitleView baseTitleView = setDefaultTitle("", onClickListener);
        baseTitleView.hideTitle();
        return baseTitleView;
    }

    /**
     * 带返回按钮的title
     * @param title
     */
    protected BaseTitleView setDefaultTitle(String title) {
        return setDefaultTitle(title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * 自定义返回按钮事件的title
     * @param title
     */
    protected BaseTitleView setDefaultTitle(String title, View.OnClickListener onClickListener) {
        BaseTitleView defaultTitle = TitleView.getTitle(this);
        defaultTitle.setMiddleTitle(title, onClickListener);
        addTitleLayout(defaultTitle);
        return defaultTitle;
    }

    protected void immersionInit(@ColorRes int color) {
        ImmersionBar.with(this)
                .statusBarDarkFont(false)
                .barColor(color)
                .init();
    }

    protected <T extends View> T bindView(@IdRes int id) {
        return (T) findViewById(id);
    }

    @Override
    public void showLoading() {
        myDialog.show();
    }

    @Override
    public void hideLoading() {
        myDialog.hide();
    }

    public void onResumeBuck(){
        onPause();
        onStop();
        onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if(decorView != null){
            init();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null && mPresenter.isViewBind()) {
            mPresenter.detachView();
        }
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
        BaseApplication.getApp().removeActivity(this);
    }

    public void showToast(String text){
        ToastUtils.popUpToast(text);
    }

    @Override
    public void finish() {
        super.finish();
        //overridePendingTransition(R.anim.exit_out_down, R.anim.exit_out_up);
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    protected void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    /**
     * 自动生成 presenter , 需要无参构造方法(测试)
     */
    protected P onInitLogicImpl() {
        try {
            Type genericSuperclass = getClass().getGenericSuperclass();
            while (true) {
                if (genericSuperclass instanceof Class) {
                    genericSuperclass = ((Class) genericSuperclass).getGenericSuperclass();
                } else {
                    break;
                }
            }
            if (genericSuperclass instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof Class) {
                        Class aClass = (Class) actualTypeArgument;
                        if (BasePresenter.class.isAssignableFrom(aClass)) {
                            return (P) aClass.newInstance();
                        }
                    }
                }
            }
        } catch (Throwable e) {
        }
        return null;
    }

}
