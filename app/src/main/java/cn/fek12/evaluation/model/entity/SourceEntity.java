package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

public class SourceEntity implements Serializable {

    /**
     * data : [{"name":"测评","origin":"ASSESSMENT"},{"name":"考试","origin":"EXAMINATION"},{"name":"去练练","origin":"PRACTICE"},{"name":"强化训练","origin":"STRENGTHEN"},{"name":"作业","origin":"TASK"}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 测评
         * origin : ASSESSMENT
         */

        private String name;
        private String origin;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }
    }
}
