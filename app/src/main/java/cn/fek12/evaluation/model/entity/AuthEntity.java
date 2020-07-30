package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

public class AuthEntity implements Serializable {


    /**
     * data : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNzNhNzljNS02MjJiLTRkODEtODFiYy1lODYyZjQ1Njk0Y2MifQ.5ppU6qtw4_xTmlCMJTpWtniODU5O6lM0ig4JLMUbnEk
     * state : 0
     * message : 成功
     */

    private String data;
    private String state;
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

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
}
