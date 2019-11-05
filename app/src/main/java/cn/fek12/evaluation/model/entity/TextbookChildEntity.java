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
public class TextbookChildEntity implements Serializable {
    /**
     * name : 部编版
     * semester : [{"name":"上学期","id":16},{"name":"下学期","id":17}]
     * id : 63
     */

    private String name;
    private int id;
    private List<ChildSectionEntity> semester;

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

    public List<ChildSectionEntity> getSemester() {
        return semester;
    }

    public void setSemester(List<ChildSectionEntity> semester) {
        this.semester = semester;
    }
}