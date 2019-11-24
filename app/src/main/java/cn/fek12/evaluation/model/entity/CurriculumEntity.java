package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: CurriculumEntity
 * @Description:
 * @CreateDate: 2019/11/24 13:40
 */
public class CurriculumEntity implements Serializable {

    /**
     * data : {"page_info":{"totalCount":1,"totalPage":1,"pageSize":30,"currentPage":1},"videos":[{"videoId":108,"addressUrl":"group1/M00/00/00/2vUGhF3aAy6ARvCzAIjznj-xCYw660.mp4","isCollection":0,"playScheduleTime":3222,"count":3,"subject":"数学","textbook":"人教版","grade":"四年级","date":"2019-11-24"}]}
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
         * page_info : {"totalCount":1,"totalPage":1,"pageSize":30,"currentPage":1}
         * videos : [{"videoId":108,"addressUrl":"group1/M00/00/00/2vUGhF3aAy6ARvCzAIjznj-xCYw660.mp4","isCollection":0,"playScheduleTime":3222,"count":3,"subject":"数学","textbook":"人教版","grade":"四年级","date":"2019-11-24"}]
         */

        private PageInfoBean page_info;
        private List<VideosBean> videos;

        public PageInfoBean getPage_info() {
            return page_info;
        }

        public void setPage_info(PageInfoBean page_info) {
            this.page_info = page_info;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class PageInfoBean {
            /**
             * totalCount : 1
             * totalPage : 1
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

        public static class VideosBean {
            /**
             * videoId : 108
             * addressUrl : group1/M00/00/00/2vUGhF3aAy6ARvCzAIjznj-xCYw660.mp4
             * isCollection : 0
             * playScheduleTime : 3222
             * count : 3
             * subject : 数学
             * textbook : 人教版
             * grade : 四年级
             * date : 2019-11-24
             */

            private int videoId;
            private String addressUrl;
            private int isCollection;
            private int playScheduleTime;
            private int count;
            private String videoName;
            private String subject;
            private String textbook;
            private String grade;
            private String date;

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public String getAddressUrl() {
                return addressUrl;
            }

            public void setAddressUrl(String addressUrl) {
                this.addressUrl = addressUrl;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public int getPlayScheduleTime() {
                return playScheduleTime;
            }

            public void setPlayScheduleTime(int playScheduleTime) {
                this.playScheduleTime = playScheduleTime;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getTextbook() {
                return textbook;
            }

            public void setTextbook(String textbook) {
                this.textbook = textbook;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
