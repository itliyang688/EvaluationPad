package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: SemesterEntity
 * @Description:
 * @CreateDate: 2019/11/5 10:14
 */
public class SemesterEntity implements Serializable {

    /**
     * data : [{"name":"上学期","id":16},{"name":"下学期","id":17}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<ChildSectionEntity> data;

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

    public List<ChildSectionEntity> getData() {
        return data;
    }

    public void setData(List<ChildSectionEntity> data) {
        this.data = data;
    }
}
