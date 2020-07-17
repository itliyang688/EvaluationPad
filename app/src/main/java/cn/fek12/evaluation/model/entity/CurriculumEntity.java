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
     * data : {"page_info":{"totalCount":1,"totalPage":1,"pageSize":30,"currentPage":1},"videos":[{"videoId":"e718cef7-21eb-4e89-86bf-a8980f9e63fb","videoName":"测试数据-gaowx","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8RAw2ADmjXAAArEyn-Zis955.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8RAwyAIpOZAFTZs3X4Kdk029.mp4","playScheduleTime":183253,"isCollection":0,"playCount":0,"subject":"小学英语","textbookName":"人教B版","gradeName":"一年级","subjectCategoryId":57172,"date":"2020-07-17 11:34:58.0","length":489,"content":"哈哈哈哈哈哈哈","playBack":"37.5"}]}
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
         * videos : [{"videoId":"e718cef7-21eb-4e89-86bf-a8980f9e63fb","videoName":"测试数据-gaowx","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8RAw2ADmjXAAArEyn-Zis955.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8RAwyAIpOZAFTZs3X4Kdk029.mp4","playScheduleTime":183253,"isCollection":0,"playCount":0,"subject":"小学英语","textbookName":"人教B版","gradeName":"一年级","subjectCategoryId":57172,"date":"2020-07-17 11:34:58.0","length":489,"content":"哈哈哈哈哈哈哈","playBack":"37.5"}]
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
             * videoId : e718cef7-21eb-4e89-86bf-a8980f9e63fb
             * videoName : 测试数据-gaowx
             * imgUrl : http://218.245.6.132/group1/M00/00/B2/2vUGhF8RAw2ADmjXAAArEyn-Zis955.jpg
             * videoUrl : http://218.245.6.132/group1/M00/00/B2/2vUGhF8RAwyAIpOZAFTZs3X4Kdk029.mp4
             * playScheduleTime : 183253
             * isCollection : 0
             * playCount : 0
             * subject : 小学英语
             * textbookName : 人教B版
             * gradeName : 一年级
             * subjectCategoryId : 57172
             * date : 2020-07-17 11:34:58.0
             * length : 489
             * content : 哈哈哈哈哈哈哈
             * playBack : 37.5
             */

            private String videoId;
            private String videoName;
            private String imgUrl;
            private String videoUrl;
            private long playScheduleTime;
            private int isCollection;
            private int playCount;
            private String subject;
            private String textbookName;
            private String gradeName;
            private int subjectCategoryId;
            private String date;
            private int length;
            private String content;
            private String playBack;

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public long getPlayScheduleTime() {
                return playScheduleTime;
            }

            public void setPlayScheduleTime(long playScheduleTime) {
                this.playScheduleTime = playScheduleTime;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public int getPlayCount() {
                return playCount;
            }

            public void setPlayCount(int playCount) {
                this.playCount = playCount;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getTextbookName() {
                return textbookName;
            }

            public void setTextbookName(String textbookName) {
                this.textbookName = textbookName;
            }

            public String getGradeName() {
                return gradeName;
            }

            public void setGradeName(String gradeName) {
                this.gradeName = gradeName;
            }

            public int getSubjectCategoryId() {
                return subjectCategoryId;
            }

            public void setSubjectCategoryId(int subjectCategoryId) {
                this.subjectCategoryId = subjectCategoryId;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPlayBack() {
                return playBack;
            }

            public void setPlayBack(String playBack) {
                this.playBack = playBack;
            }
        }
    }
}
