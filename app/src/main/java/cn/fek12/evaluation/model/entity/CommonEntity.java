package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: CommonEntity
 * @Description:
 * @CreateDate: 2019/11/15 15:20
 */
public class CommonEntity implements Serializable {

    /**
     * data : 16012
     * state : 0
     * message : 成功
     */

    private boolean data;
    private String state;
    private String message;

    public boolean getData() {
        return data;
    }

    public void setData(boolean data) {
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
