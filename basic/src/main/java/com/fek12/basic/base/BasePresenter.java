package com.fek12.basic.base;

/**
 * @author: liyang
 * 2019/9/9
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {

    protected V mView;

    @Override
    public void attachView(V mvpView) {
        this.mView = mvpView;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public boolean isViewBind() {
        return mView != null;
    }


    public V getView() {
        return mView;
    }
}
