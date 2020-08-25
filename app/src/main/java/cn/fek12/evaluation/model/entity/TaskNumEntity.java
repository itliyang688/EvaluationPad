package cn.fek12.evaluation.model.entity;

public class TaskNumEntity {


    /**
     * data : 3
     * state : 0
     * message : 成功
     */

    private int data;
    private String state;
    private String message;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
