package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: RecordsEntitiy
 * @Description:
 * @CreateDate: 2019/11/6 15:07
 */
public class RecordsEntitiy implements Serializable {

    /**
     * data : [{"id":15981,"createDate":"2019-09-29","name":"test1","score":0,"second":0,"courseName":"语文","isanswered":0},{"id":15982,"createDate":"2019-10-14","name":"test1","score":0,"second":0,"courseName":"语文","isanswered":0}]
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
         * id : 15981
         * createDate : 2019-09-29
         * name : test1
         * score : 0
         * second : 0
         * courseName : 语文
         * isanswered : 0
         */

        private int id;
        private String createDate;
        private String name;
        private int score;
        private int second;
        private String courseName;
        private int isanswered;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
    }
}
