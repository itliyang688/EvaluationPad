package cn.fek12.evaluation.model.entity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: PresentationModel
 * @Description:
 * @CreateDate: 2020/5/12 14:33
 */
public class PresentationModel {
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private PresentationEntity entity;

    public PresentationEntity getEntity() {
        return entity;
    }

    public void setEntity(PresentationEntity entity) {
        this.entity = entity;
    }
}
