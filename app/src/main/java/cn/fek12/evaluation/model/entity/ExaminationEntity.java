package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

public class ExaminationEntity implements Serializable {

    /**
     * data : {"records":[{"taskId":103,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"1111","status":1,"taskStatus":1,"createDate":"2020-06-28 05:20:43","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":102,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"ddd","status":1,"taskStatus":1,"createDate":"2020-06-24 03:42:09","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":101,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"www","status":1,"taskStatus":1,"createDate":"2020-06-24 03:24:49","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":100,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"xxx","status":3,"taskStatus":2,"createDate":"2020-06-24 03:12:07","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":99,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"xxx","status":3,"taskStatus":2,"createDate":"2020-06-24 03:03:31","drillId":null,"betweenDateStr":"已过完成时间"}],"total":5,"size":10,"current":1,"searchCount":true,"pages":1}
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
        /**
         * records : [{"taskId":103,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"1111","status":1,"taskStatus":1,"createDate":"2020-06-28 05:20:43","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":102,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"ddd","status":1,"taskStatus":1,"createDate":"2020-06-24 03:42:09","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":101,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"www","status":1,"taskStatus":1,"createDate":"2020-06-24 03:24:49","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":100,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"xxx","status":3,"taskStatus":2,"createDate":"2020-06-24 03:12:07","drillId":null,"betweenDateStr":"已过完成时间"},{"taskId":99,"subjectName":"小学英语","taskType":1,"imgUrl":null,"title":"xxx","status":3,"taskStatus":2,"createDate":"2020-06-24 03:03:31","drillId":null,"betweenDateStr":"已过完成时间"}]
         * total : 5
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * taskId : 103
             * subjectName : 小学英语
             * taskType : 1
             * imgUrl : null
             * title : 1111
             * status : 1
             * taskStatus : 1
             * createDate : 2020-06-28 05:20:43
             * drillId : null
             * betweenDateStr : 已过完成时间
             */

            private int taskId;
            private String subjectName;
            private int taskType;
            private Object imgUrl;
            private String title;
            private int status;
            private int taskStatus;
            private String createDate;
            private String drillId;
            private String betweenDateStr;

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
            }

            public int getTaskType() {
                return taskType;
            }

            public void setTaskType(int taskType) {
                this.taskType = taskType;
            }

            public Object getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(Object imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getTaskStatus() {
                return taskStatus;
            }

            public void setTaskStatus(int taskStatus) {
                this.taskStatus = taskStatus;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getDrillId() {
                return drillId;
            }

            public void setDrillId(String drillId) {
                this.drillId = drillId;
            }

            public String getBetweenDateStr() {
                return betweenDateStr;
            }

            public void setBetweenDateStr(String betweenDateStr) {
                this.betweenDateStr = betweenDateStr;
            }
        }
    }
}
