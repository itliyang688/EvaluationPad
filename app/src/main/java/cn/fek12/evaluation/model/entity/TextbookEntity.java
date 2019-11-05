package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: TextbookEntity
 * @Description:
 * @CreateDate: 2019/11/4 19:23
 */
public class TextbookEntity implements Serializable {

    /**
     * data : [{"name":"部编版","semester":[{"name":"上学期","id":16},{"name":"下学期","id":17}],"id":63}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<TextbookChildEntity> data;

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

    public List<TextbookChildEntity> getData() {
        return data;
    }

    public void setData(List<TextbookChildEntity> data) {
        this.data = data;
    }
}
