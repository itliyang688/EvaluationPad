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
        private List<WeekAndDayBean> Week;
        private List<WeekAndDayBean> Day;

        public List<WeekAndDayBean> getWeek() {
            return Week;
        }

        public void setWeek(List<WeekAndDayBean> week) {
            Week = week;
        }

        public List<WeekAndDayBean> getDay() {
            return Day;
        }

        public void setDay(List<WeekAndDayBean> day) {
            Day = day;
        }

        public static class WeekAndDayBean {
            /**
             * id : 15983
             * name : test20191029
             * description : 测试
             * score : 0
             * second : 12000
             * courseName :
             * isanswered : 0
             * grade : 3
             * subject : 14
             * imageUrl : http://192.168.0.60:8084/noc_basicedu/upload/image/201909/98f5fec0-1276-41b7-a646-79dd41b73a9a.jpg
             * paperResultId : 11435
             * paperResultDate : 2019-10-29 16:08:37.0
             */

            private int id;
            private String name;
            private String description;
            private int score;
            private int second;
            private String courseName;
            private int isanswered;
            private int grade;
            private int subject;
            private String imageUrl;
            private int paperResultId;
            private String paperResultDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getSubject() {
                return subject;
            }

            public void setSubject(int subject) {
                this.subject = subject;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public int getPaperResultId() {
                return paperResultId;
            }

            public void setPaperResultId(int paperResultId) {
                this.paperResultId = paperResultId;
            }

            public String getPaperResultDate() {
                return paperResultDate;
            }

            public void setPaperResultDate(String paperResultDate) {
                this.paperResultDate = paperResultDate;
            }
        }
    }
}
