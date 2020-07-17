package cn.fek12.evaluation.model.entity;

import java.util.List;

public class SubjectModel {

    /**
     * data : [{"id":23,"subId":"33268067-64f4-4ecc-98ea-1cff58fa3fcf","subName":"初中语文"},{"id":25,"subId":"cb3004af-0c07-4bfb-9faf-98d4f5e31c6d","subName":"初中物理"},{"id":29,"subId":"e122ffa3-0093-4fc2-9e90-c456270acd70","subName":"初中数学"},{"id":30,"subId":"271bdcf0-42e0-40af-9772-1aabf1063afe","subName":"初中英语"},{"id":31,"subId":"320f13f9-f7ca-47ff-8cb6-3fa8c3f3ccee","subName":"初中化学"},{"id":32,"subId":"b364f20d-9738-11ea-8a98-fa163e3f02e7","subName":"初中生物"},{"id":33,"subId":"b35fb630-9738-11ea-8a98-fa163e3f02e7","subName":"初中道德与法治"},{"id":34,"subId":"b361742b-9738-11ea-8a98-fa163e3f02e7","subName":"初中歷史"},{"id":35,"subId":"b3632cff-9738-11ea-8a98-fa163e3f02e7","subName":"初中地理"}]
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
         * id : 23
         * subId : 33268067-64f4-4ecc-98ea-1cff58fa3fcf
         * subName : 初中语文
         */

        private int id;
        private String subId;
        private String subName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubId() {
            return subId;
        }

        public void setSubId(String subId) {
            this.subId = subId;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }
    }
}
