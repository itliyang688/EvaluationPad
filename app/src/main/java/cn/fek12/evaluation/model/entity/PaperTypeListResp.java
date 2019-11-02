package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: PaperTypeListResp
 * @Description:
 * @CreateDate: 2019/10/25 17:18
 */
public class PaperTypeListResp implements Serializable {

    /**
     * data : [{"name":"随堂测评","id":53,"type":1},{"name":"单元测评","id":54,"type":1},{"name":"复习测评","id":51,"type":1},{"name":"专项测评","id":52,"type":1},{"name":"自主测评","id":86,"type":2}]
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
         * name : 随堂测评
         * id : 53
         * type : 1
         */

        private String name;
        private String id;
        private String value;
        private int type;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
