package cn.fek12.evaluation.model.entity;

import java.util.List;

public class TrainListEntity {


    /**
     * data : [{"drillId":1,"taskId":103,"drillName":"小学英语","subject":"小学英语","time":70,"dateStr":"1分10秒","count":4,"rightAmount":2,"wrongNum":2}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<TrainEntity> data;

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

    public List<TrainEntity> getData() {
        return data;
    }

    public void setData(List<TrainEntity> data) {
        this.data = data;
    }
}
