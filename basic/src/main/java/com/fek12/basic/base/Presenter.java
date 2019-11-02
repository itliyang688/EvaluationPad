package com.fek12.basic.base;

/**
 * @author: liyang
 * @data: 2019/9/9
 */
public interface Presenter<V> {
    void attachView(V mvpView);

    void detachView();
}
