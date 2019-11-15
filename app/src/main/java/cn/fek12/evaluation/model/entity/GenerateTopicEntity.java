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
public class GenerateTopicEntity implements Serializable {

    /**
     * earlyYear : string
     * grade : 0
     * judgeSubject : {"common":0,"difficult":0,"easy":0}
     * knowledges : [0]
     * multipleSubject : {"common":0,"difficult":0,"easy":0}
     * paperName : string
     * ptype : 0
     * semester : 0
     * singleSubject : {"common":0,"difficult":0,"easy":0}
     * subject : 0
     * subjectName : string
     * textbook : 0
     * userId : 0
     * way : 0
     * years : ["string"]
     */

    private String earlyYear;
    private String grade;
    private SubjectBean judgeSubject;
    private SubjectBean multipleSubject;
    private String paperName;
    private String ptype;
    private String semester;
    private SubjectBean singleSubject;
    private String subject;
    private String subjectName;
    private String textbook;
    private String userId;
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

    public SubjectBean getJudgeSubject() {
        return judgeSubject;
    }

    public void setJudgeSubject(SubjectBean judgeSubject) {
        this.judgeSubject = judgeSubject;
    }

    public SubjectBean getMultipleSubject() {
        return multipleSubject;
    }

    public void setMultipleSubject(SubjectBean multipleSubject) {
        this.multipleSubject = multipleSubject;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public SubjectBean getSingleSubject() {
        return singleSubject;
    }

    public void setSingleSubject(SubjectBean singleSubject) {
        this.singleSubject = singleSubject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public static class SubjectBean {
        /**
         * common : 0
         * difficult : 0
         * easy : 0
         */

        private String common;
        private String difficult;
        private String easy;

        public String getCommon() {
            return common;
        }

        public void setCommon(String common) {
            this.common = common;
        }

        public String getDifficult() {
            return difficult;
        }

        public void setDifficult(String difficult) {
            this.difficult = difficult;
        }

        public String getEasy() {
            return easy;
        }

        public void setEasy(String easy) {
            this.easy = easy;
        }
    }
}
