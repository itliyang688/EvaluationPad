package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: SubjectsEntity
 * @Description:
 * @CreateDate: 2019/12/13 16:21
 */
public class SubjectsEntity implements Serializable {


    /**
     * data : [{"id":13,"orders":1,"name":"语文","type":"SUBJECT"},{"id":14,"orders":2,"name":"数学","type":"SUBJECT"},{"id":15,"orders":3,"name":"英语","type":"SUBJECT"},{"id":26,"orders":4,"name":"历史","type":"SUBJECT"},{"id":27,"orders":5,"name":"政治","type":"SUBJECT"},{"id":28,"orders":6,"name":"地理","type":"SUBJECT"},{"id":29,"orders":7,"name":"生物","type":"SUBJECT"},{"id":30,"orders":8,"name":"化学","type":"SUBJECT"},{"id":31,"orders":9,"name":"物理","type":"SUBJECT"},{"id":65,"orders":10,"name":"科学","type":"SUBJECT"},{"id":69,"orders":12,"name":"品德","type":"SUBJECT"}]
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
         * id : 13
         * orders : 1
         * name : 语文
         * type : SUBJECT
         */

        private int id;
        private int orders;
        private String name;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getOrders() {
            return orders;
        }

        public void setOrders(int orders) {
            this.orders = orders;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
