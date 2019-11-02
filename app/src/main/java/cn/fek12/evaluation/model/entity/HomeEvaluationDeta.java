package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: HomeEvaluationDeta
 * @Description:
 * @CreateDate: 2019/10/24 11:53
 */
public class HomeEvaluationDeta implements Serializable {

    /**
     * data : {"myPaper":{"date":"2019-09-10","count":7},"recommendPaper":[{"id":8140,"createDate":"2017-08-18","name":"小学语文人教版三年级下第七单元单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":3782,"createDate":"2016-12-30","name":"初中语文人教版九年级下期中测评","description":"易","score":100,"second":18000,"courseName":"语文","isanswered":0},{"id":1860,"createDate":"2016-12-16","name":"初中物理人教版八年级上声音的产生与传播随堂测评","description":"中","score":100,"second":54120,"courseName":"物理","isanswered":0}],"banner":[{"name":"xx","addressUrl":"https://www.baidu.com/","imageUrl":"http://2016.fek12.cn/resources/admin_cp/img/home/banner1.jpg"},{"name":"cc","addressUrl":"https://www.baidu.com/","imageUrl":"http://2016.fek12.cn/resources/admin_cp/img/home/banner2.jpg"},{"name":"vv","addressUrl":"https://www.baidu.com/","imageUrl":"http://2016.fek12.cn/resources/admin_cp/img/home/banner3.jpg"}]}
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
         * myPaper : {"date":"2019-09-10","count":7}
         * recommendPaper : [{"id":8140,"createDate":"2017-08-18","name":"小学语文人教版三年级下第七单元单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":3782,"createDate":"2016-12-30","name":"初中语文人教版九年级下期中测评","description":"易","score":100,"second":18000,"courseName":"语文","isanswered":0},{"id":1860,"createDate":"2016-12-16","name":"初中物理人教版八年级上声音的产生与传播随堂测评","description":"中","score":100,"second":54120,"courseName":"物理","isanswered":0}]
         * banner : [{"name":"xx","addressUrl":"https://www.baidu.com/","imageUrl":"http://2016.fek12.cn/resources/admin_cp/img/home/banner1.jpg"},{"name":"cc","addressUrl":"https://www.baidu.com/","imageUrl":"http://2016.fek12.cn/resources/admin_cp/img/home/banner2.jpg"},{"name":"vv","addressUrl":"https://www.baidu.com/","imageUrl":"http://2016.fek12.cn/resources/admin_cp/img/home/banner3.jpg"}]
         */

        private MyPaperBean myPaper;
        private List<RecommendPaperBean> recommendPaper;
        private List<BannerBean> banner;

        public MyPaperBean getMyPaper() {
            return myPaper;
        }

        public void setMyPaper(MyPaperBean myPaper) {
            this.myPaper = myPaper;
        }

        public List<RecommendPaperBean> getRecommendPaper() {
            return recommendPaper;
        }

        public void setRecommendPaper(List<RecommendPaperBean> recommendPaper) {
            this.recommendPaper = recommendPaper;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class MyPaperBean {
            /**
             * date : 2019-09-10
             * count : 7
             */

            private String date;
            private int count;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class RecommendPaperBean {
            /**
             * id : 8140
             * createDate : 2017-08-18
             * name : 小学语文人教版三年级下第七单元单元测评
             * description : 易
             * score : 100
             * second : 12000
             * courseName : 语文
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

        public static class BannerBean {
            /**
             * name : xx
             * addressUrl : https://www.baidu.com/
             * imageUrl : http://2016.fek12.cn/resources/admin_cp/img/home/banner1.jpg
             */

            private String name;
            private String addressUrl;
            private String imageUrl;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddressUrl() {
                return addressUrl;
            }

            public void setAddressUrl(String addressUrl) {
                this.addressUrl = addressUrl;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
