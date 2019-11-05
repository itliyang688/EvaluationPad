package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: ContainListEntity
 * @Description:
 * @CreateDate: 2019/11/5 11:26
 */
public class ContainListEntity implements Serializable {
    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;

    public List<ChildSectionEntity> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<ChildSectionEntity> gradeList) {
        this.gradeList = gradeList;
    }

    public List<SubjectEntity.DataBean> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SubjectEntity.DataBean> subjectList) {
        this.subjectList = subjectList;
    }

    public List<TextbookChildEntity> getTextBookList() {
        return textBookList;
    }

    public void setTextBookList(List<TextbookChildEntity> textBookList) {
        this.textBookList = textBookList;
    }

    public List<ChildSectionEntity> getSemesterList() {
        return semesterList;
    }

    public void setSemesterList(List<ChildSectionEntity> semesterList) {
        this.semesterList = semesterList;
    }
}
