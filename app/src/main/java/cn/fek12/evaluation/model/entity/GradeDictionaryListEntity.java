package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: GradeDictionaryListEntity
 * @Description:
 * @CreateDate: 2019/11/4 18:36
 */
public class GradeDictionaryListEntity implements Serializable {

    /**
     * data : [{"name":"一年级","orders":1,"id":1,"type":"GRADE"},{"name":"二年级","orders":2,"id":2,"type":"GRADE"},{"name":"三年级","orders":3,"id":3,"type":"GRADE"},{"name":"四年级","orders":4,"id":4,"type":"GRADE"},{"name":"五年级","orders":5,"id":5,"type":"GRADE"},{"name":"六年级","orders":6,"id":6,"type":"GRADE"},{"name":"七年级","orders":7,"id":7,"type":"GRADE"},{"name":"八年级","orders":8,"id":8,"type":"GRADE"},{"name":"九年级","orders":9,"id":9,"type":"GRADE"},{"name":"高中","orders":10,"id":10,"type":"GRADE"}]
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
