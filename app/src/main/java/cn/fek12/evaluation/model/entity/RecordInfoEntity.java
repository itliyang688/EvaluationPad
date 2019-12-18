package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: RecordInfoEntity
 * @Description:
 * @CreateDate: 2019/12/17 10:08
 */
public class RecordInfoEntity implements Serializable {
    /**
     * subject : 数学
     * grade : 四年级
     * textBook : 人教版
     * knowledgePoint : 数级与数位
     * time : 1
     * count : 5
     * rightAmount : 0
     */

    private String subject;
    private String grade;
    private String textBook;
    private String knowledgePoint;
    private String modifyData;
    private String subjectCategoryId;
    private String practiceId;
    private int time;
    private int count;
    private int rightAmount;

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    public String getSubjectCategoryId() {
        return subjectCategoryId;
    }

    public void setSubjectCategoryId(String subjectCategoryId) {
        this.subjectCategoryId = subjectCategoryId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTextBook() {
        return textBook;
    }

    public void setTextBook(String textBook) {
        this.textBook = textBook;
    }

    public String getKnowledgePoint() {
        return knowledgePoint;
    }

    public void setKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    public String getModifyData() {
        return modifyData;
    }

    public void setModifyData(String modifyData) {
        this.modifyData = modifyData;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRightAmount() {
        return rightAmount;
    }

    public void setRightAmount(int rightAmount) {
        this.rightAmount = rightAmount;
    }
}
