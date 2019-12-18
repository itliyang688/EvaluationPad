package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: PracticeListEntity
 * @Description:
 * @CreateDate: 2019/12/17 10:13
 */
public class PracticeListEntity implements Serializable {

    /**
     * data : [{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":10,"count":4,"rightAmount":2,"modifyData":"2019/11/18"},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":10,"count":5,"rightAmount":2,"modifyData":"2019/11/18"}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<RecordInfoEntity> data;

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

    public List<RecordInfoEntity> getData() {
        return data;
    }

    public void setData(List<RecordInfoEntity> data) {
        this.data = data;
    }
}
