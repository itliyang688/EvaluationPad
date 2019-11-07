package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: EvaluationListEntity
 * @Description:
 * @CreateDate: 2019/11/7 13:25
 */
public class EvaluationListEntity implements Serializable {


    /**
     * data : {"page_info":{"totalCount":50,"totalPage":2,"pageSize":30,"currentPage":1},"papers":[{"id":15977,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15974,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15972,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15971,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15969,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15968,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15967,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15966,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15965,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15964,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15963,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15962,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":1},{"id":15961,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15937,"createDate":"2019-09-16","name":"test0916","description":"111","score":110,"second":6600,"courseName":"","isanswered":0},{"id":11427,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11428,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11429,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":10800,"courseName":"语文","isanswered":0},{"id":11430,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11431,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11432,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11425,"createDate":"2017-12-12","name":"小学语文人教版一年级上a o e随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":11287,"createDate":"2017-12-04","name":"小学语文人教版一年级上静夜思随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":11286,"createDate":"2017-12-04","name":"小学语文人教版一年级上静夜思随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":8347,"createDate":"2017-08-19","name":"小学语文人教版一年级上小松鼠找花生随堂测评","description":"易","score":100,"second":5400,"courseName":"语文","isanswered":0},{"id":8320,"createDate":"2017-08-19","name":"小学语文人教版一年级上一去二三里随堂测评","description":"易","score":100,"second":4800,"courseName":"语文","isanswered":0},{"id":8321,"createDate":"2017-08-19","name":"小学语文人教版一年级上口耳目随堂测评","description":"易","score":100,"second":10200,"courseName":"语文","isanswered":0},{"id":8322,"createDate":"2017-08-19","name":"小学语文人教版一年级上在家里随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":8323,"createDate":"2017-08-19","name":"小学语文人教版一年级上操场上随堂测评","description":"易","score":100,"second":7800,"courseName":"语文","isanswered":0},{"id":8324,"createDate":"2017-08-19","name":"小学语文人教版一年级上画随堂测评","description":"易","score":100,"second":6000,"courseName":"语文","isanswered":0},{"id":8325,"createDate":"2017-08-19","name":"小学语文人教版一年级上四季随堂测评","description":"易","score":100,"second":11400,"courseName":"语文","isanswered":0}]}
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
         * page_info : {"totalCount":50,"totalPage":2,"pageSize":30,"currentPage":1}
         * papers : [{"id":15977,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15974,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15972,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15971,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15969,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15968,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15967,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15966,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15965,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15964,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15963,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15962,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":1},{"id":15961,"createDate":"2019-09-24","name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0},{"id":15937,"createDate":"2019-09-16","name":"test0916","description":"111","score":110,"second":6600,"courseName":"","isanswered":0},{"id":11427,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11428,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11429,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":10800,"courseName":"语文","isanswered":0},{"id":11430,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11431,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11432,"createDate":"2017-12-12","name":"小学语文人教版一年级上课文1单元测评","description":"易","score":100,"second":12000,"courseName":"语文","isanswered":0},{"id":11425,"createDate":"2017-12-12","name":"小学语文人教版一年级上a o e随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":11287,"createDate":"2017-12-04","name":"小学语文人教版一年级上静夜思随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":11286,"createDate":"2017-12-04","name":"小学语文人教版一年级上静夜思随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":8347,"createDate":"2017-08-19","name":"小学语文人教版一年级上小松鼠找花生随堂测评","description":"易","score":100,"second":5400,"courseName":"语文","isanswered":0},{"id":8320,"createDate":"2017-08-19","name":"小学语文人教版一年级上一去二三里随堂测评","description":"易","score":100,"second":4800,"courseName":"语文","isanswered":0},{"id":8321,"createDate":"2017-08-19","name":"小学语文人教版一年级上口耳目随堂测评","description":"易","score":100,"second":10200,"courseName":"语文","isanswered":0},{"id":8322,"createDate":"2017-08-19","name":"小学语文人教版一年级上在家里随堂测评","description":"易","score":100,"second":9000,"courseName":"语文","isanswered":0},{"id":8323,"createDate":"2017-08-19","name":"小学语文人教版一年级上操场上随堂测评","description":"易","score":100,"second":7800,"courseName":"语文","isanswered":0},{"id":8324,"createDate":"2017-08-19","name":"小学语文人教版一年级上画随堂测评","description":"易","score":100,"second":6000,"courseName":"语文","isanswered":0},{"id":8325,"createDate":"2017-08-19","name":"小学语文人教版一年级上四季随堂测评","description":"易","score":100,"second":11400,"courseName":"语文","isanswered":0}]
         */

        private PageInfoBean page_info;
        private List<PapersBean> papers;

        public PageInfoBean getPage_info() {
            return page_info;
        }

        public void setPage_info(PageInfoBean page_info) {
            this.page_info = page_info;
        }

        public List<PapersBean> getPapers() {
            return papers;
        }

        public void setPapers(List<PapersBean> papers) {
            this.papers = papers;
        }

        public static class PageInfoBean {
            /**
             * totalCount : 50
             * totalPage : 2
             * pageSize : 30
             * currentPage : 1
             */

            private int totalCount;
            private int totalPage;
            private int pageSize;
            private int currentPage;

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }
        }

        public static class PapersBean {
            /**
             * id : 15977
             * createDate : 2019-09-24
             * name : test0924
             * description : 测试
             * score : 0
             * second : 12600
             * courseName :
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
