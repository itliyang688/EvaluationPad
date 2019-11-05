package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: ChildSectionEntity
 * @Description:
 * @CreateDate: 2019/11/4 20:00
 */
public class ChildSectionEntity implements Serializable {
    /**
     * name : 上学期
     * id : 16
     */

    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
