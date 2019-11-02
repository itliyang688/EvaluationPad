package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: AssessmentIndexPaperResp
 * @Description:
 * @CreateDate: 2019/10/28 15:49
 */
public class AssessmentIndexPaperResp implements Serializable {


    /**
     * data : {"update":[{"id":15978,"createDate":"2019-09-29","name":"222","description":"222","score":0,"second":600,"courseName":"1","isanswered":0},{"id":15977,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15976,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15975,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15974,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15973,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15972,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15971,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15970,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15969,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0}],"hot":[{"id":8352,"createDate":"2017-08-19","name":"小学语文人教版一年级上小熊住山洞随堂测评","description":"易","score":100,"second":14400,"courseName":"语文","isanswered":0},{"id":15962,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":1},{"id":11425,"createDate":"2017-12-12","name":"小学语文人教版一年级上a o e随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":8437,"createDate":"2017-08-19","name":"小学语文人教版一年级上期末测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":8438,"createDate":"2017-08-19","name":"小学语文人教版一年级上期末测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0}]}
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
        private List<HotAndUpdateBean> update;
        private List<HotAndUpdateBean> hot;

        public List<HotAndUpdateBean> getUpdate() {
            return update;
        }

        public void setUpdate(List<HotAndUpdateBean> update) {
            this.update = update;
        }

        public List<HotAndUpdateBean> getHot() {
            return hot;
        }

        public void setHot(List<HotAndUpdateBean> hot) {
            this.hot = hot;
        }

        public static class HotAndUpdateBean {
            /**
             * id : 15978
             * createDate : 2019-09-29
             * name : 222
             * description : 222
             * score : 0
             * second : 600
             * courseName : 1
             * isanswered : 0
             */

            private int id;
            private String createDate;
            private String name;
            private String description;
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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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
}
