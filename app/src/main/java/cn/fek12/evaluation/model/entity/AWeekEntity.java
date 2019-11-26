package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: AWeekEntity
 * @Description:
 * @CreateDate: 2019/11/4 9:48
 */
public class AWeekEntity implements Serializable {


    /**
     * data : {"Week":[{"id":15983,"name":"test20191029","description":"测试","score":0,"second":12000,"courseName":"","isanswered":0,"grade":3,"subject":14,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/98f5fec0-1276-41b7-a646-79dd41b73a9a.jpg","paperResultId":11435,"paperResultDate":"2019-10-29 16:08:37.0"}],"Day":[{"id":44,"name":"小学数学人教版四年级上亿以内数的计数单位、数级、数位随堂测评","description":"易","score":100,"second":33000,"courseName":"数学","isanswered":0,"grade":4,"subject":14,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/98f5fec0-1276-41b7-a646-79dd41b73a9a.jpg","paperResultId":11437,"paperResultDate":"2019-11-03 03:49:28.0"}]}
     * state : 0
     * message : 成功
     */

    private DataBean data;
    private String state;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        private List<PresentationEntity> Week;
        private List<PresentationEntity> Day;

        public List<PresentationEntity> getWeek() {
            return Week;
        }

        public void setWeek(List<PresentationEntity> week) {
            Week = week;
        }

        public List<PresentationEntity> getDay() {
            return Day;
        }

        public void setDay(List<PresentationEntity> day) {
            Day = day;
        }
    }
}
