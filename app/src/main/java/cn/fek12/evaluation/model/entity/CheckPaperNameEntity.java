package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: CheckPaperNameEntity
 * @Description:
 * @CreateDate: 2019/11/15 15:45
 */
public class CheckPaperNameEntity implements Serializable {

    /**
     * data : true
     * state : 0
     * message : 成功
     */

    private boolean data;
    private String state;
    private String message;

    public boolean isData() {
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
