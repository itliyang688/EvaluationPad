package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: RelevantVideoListEntity
 * @Description:
 * @CreateDate: 2019/11/14 15:16
 */
public class RelevantVideoListEntity implements Serializable {

    /**
     * data : {"video":{"videoId":"344d726b-67e2-453d-91ee-61254145888d","videoName":"测试一下时间","subjcetName":"小学英语","createDate":"2020/07/13","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnaAKTjJAAA5Nhd3I5E322.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnKACK89ALXuAGQXBck157.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"2b923c2c-2d0b-11ea-85e1-00155d79743b","textbookName":"人教B版","gradeName":"一年级"},"relatedVideo":[{"videoId":"7149bc71-d0bf-4f27-9031-393ec4160bdd","videoName":"测试123123","subjcetName":"小学英语","createDate":"2020/07/11","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JQZmAf8fBAAA5Nhd3I5E804.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JQZmAaWXkALXuAGQXBck457.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"2b923c2c-2d0b-11ea-85e1-00155d79743b","textbookName":"人教B版","gradeName":"一年级"},{"videoId":"ce2576c2-0584-4a3a-b475-dddf6748ee93","videoName":"测试版本","subjcetName":"小学英语","createDate":"2020/07/11","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JT7GALlblAAA5Nhd3I5E975.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JT7CAGBmQALXuAGQXBck235.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"5bd4cf39-1d8c-11e6-85b3-00163e022218","textbookName":"人教B版","gradeName":"一年级"},{"videoId":"edb80176-665f-4b92-95c1-bfe58c4829ea","videoName":"测试一下视频ss","subjcetName":"小学英语","createDate":"2020/07/13","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JOXOAVadGAAA5Nhd3I5E325.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JOXKANYKuALXuAGQXBck003.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"2b923c2c-2d0b-11ea-85e1-00155d79743b","textbookName":"人教B版","gradeName":"一年级"}]}
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
         * video : {"videoId":"344d726b-67e2-453d-91ee-61254145888d","videoName":"测试一下时间","subjcetName":"小学英语","createDate":"2020/07/13","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnaAKTjJAAA5Nhd3I5E322.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnKACK89ALXuAGQXBck157.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"2b923c2c-2d0b-11ea-85e1-00155d79743b","textbookName":"人教B版","gradeName":"一年级"}
         * relatedVideo : [{"videoId":"7149bc71-d0bf-4f27-9031-393ec4160bdd","videoName":"测试123123","subjcetName":"小学英语","createDate":"2020/07/11","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JQZmAf8fBAAA5Nhd3I5E804.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JQZmAaWXkALXuAGQXBck457.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"2b923c2c-2d0b-11ea-85e1-00155d79743b","textbookName":"人教B版","gradeName":"一年级"},{"videoId":"ce2576c2-0584-4a3a-b475-dddf6748ee93","videoName":"测试版本","subjcetName":"小学英语","createDate":"2020/07/11","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JT7GALlblAAA5Nhd3I5E975.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JT7CAGBmQALXuAGQXBck235.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"5bd4cf39-1d8c-11e6-85b3-00163e022218","textbookName":"人教B版","gradeName":"一年级"},{"videoId":"edb80176-665f-4b92-95c1-bfe58c4829ea","videoName":"测试一下视频ss","subjcetName":"小学英语","createDate":"2020/07/13","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JOXOAVadGAAA5Nhd3I5E325.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JOXKANYKuALXuAGQXBck003.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":"2b923c2c-2d0b-11ea-85e1-00155d79743b","textbookName":"人教B版","gradeName":"一年级"}]
         */

        private VideoBean video;
        private List<RelatedVideoBean> relatedVideo;

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public List<RelatedVideoBean> getRelatedVideo() {
            return relatedVideo;
        }

        public void setRelatedVideo(List<RelatedVideoBean> relatedVideo) {
            this.relatedVideo = relatedVideo;
        }

        public static class VideoBean {
            /**
             * videoId : 344d726b-67e2-453d-91ee-61254145888d
             * videoName : 测试一下时间
             * subjcetName : 小学英语
             * createDate : 2020/07/13
             * imgUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnaAKTjJAAA5Nhd3I5E322.jpg
             * videoUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnKACK89ALXuAGQXBck157.mp4
             * playScheduleTime : 0
             * isCollection : 0
             * playCount : 0
             * subject : 2b923c2c-2d0b-11ea-85e1-00155d79743b
             * textbookName : 人教B版
             * gradeName : 一年级
             */

            private String videoId;
            private String videoName;
            private String subjcetName;
            private String createDate;
            private String imgUrl;
            private String videoUrl;
            private int playScheduleTime;
            private int isCollection;
            private int playCount;
            private String subject;
            private String textbookName;
            private String gradeName;

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

            public String getSubjcetName() {
                return subjcetName;
            }

            public void setSubjcetName(String subjcetName) {
                this.subjcetName = subjcetName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public int getPlayScheduleTime() {
                return playScheduleTime;
            }

            public void setPlayScheduleTime(int playScheduleTime) {
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
        }

        public static class RelatedVideoBean {
            /**
             * videoId : 7149bc71-d0bf-4f27-9031-393ec4160bdd
             * videoName : 测试123123
             * subjcetName : 小学英语
             * createDate : 2020/07/11
             * imgUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8JQZmAf8fBAAA5Nhd3I5E804.jpg
             * videoUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8JQZmAaWXkALXuAGQXBck457.mp4
             * playScheduleTime : 0
             * isCollection : 0
             * playCount : 0
             * subject : 2b923c2c-2d0b-11ea-85e1-00155d79743b
             * textbookName : 人教B版
             * gradeName : 一年级
             */

            private String videoId;
            private String videoName;
            private String subjcetName;
            private String createDate;
            private String imgUrl;
            private String videoUrl;
            private int playScheduleTime;
            private int isCollection;
            private int playCount;
            private String subject;
            private String textbookName;
            private String gradeName;

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

            public String getSubjcetName() {
                return subjcetName;
            }

            public void setSubjcetName(String subjcetName) {
                this.subjcetName = subjcetName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public int getPlayScheduleTime() {
                return playScheduleTime;
            }

            public void setPlayScheduleTime(int playScheduleTime) {
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
        }
    }
}
