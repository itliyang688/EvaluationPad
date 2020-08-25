package cn.fek12.evaluation.model.entity;

public class TrainEntity {

    /**
     * drillId : 1
     * taskId : 103
     * drillName : 小学英语
     * subject : 小学英语
     * time : 70
     * dateStr : 1分10秒
     * count : 4
     * rightAmount : 2
     * wrongNum : 2
     */

    private int drillId;
    private int taskId;
    private String drillName;
    private String subject;
    private int time;
    private String dateStr;
    private int count;
    private int rightAmount;
    private int wrongNum;

    public int getDrillId() {
        return drillId;
    }

    public void setDrillId(int drillId) {
        this.drillId = drillId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDrillName() {
        return drillName;
    }

    public void setDrillName(String drillName) {
        this.drillName = drillName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
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

    public int getWrongNum() {
        return wrongNum;
    }

    public void setWrongNum(int wrongNum) {
        this.wrongNum = wrongNum;
    }
}
