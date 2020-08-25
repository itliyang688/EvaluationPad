package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: ExrGroupListEntity
 * @Description:
 * @CreateDate: 2019/12/17 11:05
 */
public class TrainExrGroupListEntity implements Serializable {
    private String group;
    private List<TrainEntity> data;


    public List<TrainEntity> getData() {
        return data;
    }

    public void setData(List<TrainEntity> data) {
        this.data = data;
    }

    public String getGroup() {
        return group;
    }


    public void setGroup(String group) {
        this.group = group;
    }
}
