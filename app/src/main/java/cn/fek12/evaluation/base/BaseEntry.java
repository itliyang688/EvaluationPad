package cn.fek12.evaluation.base;

import java.io.Serializable;

/**
 * File descripition:   mode基类
 *
 * @author liyang
 * @date 2019/9/9
 */
public class BaseEntry<T> implements Serializable {
    private String state;
    private String message;
    private T data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
