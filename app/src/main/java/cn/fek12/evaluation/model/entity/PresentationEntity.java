package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: PresentationEntity
 * @Description:
 * @CreateDate: 2019/11/25 15:28
 */
public class PresentationEntity implements Serializable {
    /**
     * id : 15962
     * name : test0924
     * description : 测试
     * score : 0
     * second : 12600
     * courseName :
     * isanswered : 0
     * grade : 1
     * subject : 13
     * imageUrl : http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg
     * paperResultId : 11430
     * paperResultDate : 2019-10-14 16:32:28.0
     */

    private int id;
    private String name;
    private String description;
    private int score;
    private int second;
    private int studentScore;
    private String courseName;
    private int isanswered;
    private int grade;
    private int subject;
    private String imageUrl;
    private int paperResultId;
    private String paperResultDate;

    public int getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(int studentScore) {
        this.studentScore = studentScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getIsanswered() {
        return isanswered;
    }

    public void setIsanswered(int isanswered) {
        this.isanswered = isanswered;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPaperResultId() {
        return paperResultId;
    }

    public void setPaperResultId(int paperResultId) {
        this.paperResultId = paperResultId;
    }

    public String getPaperResultDate() {
        return paperResultDate;
    }

    public void setPaperResultDate(String paperResultDate) {
        this.paperResultDate = paperResultDate;
    }

}
