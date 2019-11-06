package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: QueryTopicEntity
 * @Description:
 * @CreateDate: 2019/11/6 17:23
 */
public class QueryTopicEntity implements Serializable {
    private String grade;
    private String semester;
    private String subject;
    private String textbook;
    private String earlyYear;
    private String way;
    private List<String> knowledges;
    private List<String> years;

    public String getEarlyYear() {
        return earlyYear;
    }

    public void setEarlyYear(String earlyYear) {
        this.earlyYear = earlyYear;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public List<String> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<String> knowledges) {
        this.knowledges = knowledges;
    }

    public List<String> getYears() {
        return years;
    }

    public void setYears(List<String> years) {
        this.years = years;
    }
}
