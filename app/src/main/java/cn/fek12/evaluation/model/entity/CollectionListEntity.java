package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: CollectionListEntity
 * @Description:
 * @CreateDate: 2019/11/23 17:43
 */
public class CollectionListEntity implements Serializable {

    /**
     * data : {"earlier":[],"amonth":[],"aweek":[{"videoId":"344d726b-67e2-453d-91ee-61254145888d","videoName":"测试一下时间","subjcetName":"小学数学","createDate":"2020/07/13","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnaAKTjJAAA5Nhd3I5E322.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnKACK89ALXuAGQXBck157.mp4","playScheduleTime":0,"isCollection":1,"playCount":0,"subject":null,"textbookName":"人教版,人教B版","gradeName":"一年级","videoContent":"水电费"}]}
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
        private List<VideoBean> earlier;
        private List<VideoBean> amonth;
        private List<VideoBean> aweek;

        public List<VideoBean> getEarlier() {
            return earlier;
        }

        public void setEarlier(List<VideoBean> earlier) {
            this.earlier = earlier;
        }

        public List<VideoBean> getAmonth() {
            return amonth;
        }

        public void setAmonth(List<VideoBean> amonth) {
            this.amonth = amonth;
        }

        public List<VideoBean> getAweek() {
            return aweek;
        }

        public void setAweek(List<VideoBean> aweek) {
            this.aweek = aweek;
        }

        public static class VideoBean {
            /**
             * videoId : 344d726b-67e2-453d-91ee-61254145888d
             * videoName : 测试一下时间
             * subjcetName : 小学数学
             * createDate : 2020/07/13
             * imgUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnaAKTjJAAA5Nhd3I5E322.jpg
             * videoUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8JNnKACK89ALXuAGQXBck157.mp4
             * playScheduleTime : 0
             * isCollection : 1
             * playCount : 0
             * subject : null
             * textbookName : 人教版,人教B版
             * gradeName : 一年级
             * videoContent : 水电费
             */

            private String videoId;
            private String videoName;
            private String subjcetName;
            private String playBack;
            private String createDate;
            private String imgUrl;
            private String videoUrl;
            private int playScheduleTime;
            private int isCollection;
            private int playCount;
            private Object subject;
            private String textbookName;
            private String gradeName;
            private String videoContent;

            public String getPlayBack() {
                return playBack;
            }

            public void setPlayBack(String playBack) {
                this.playBack = playBack;
            }

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

            public Object getSubject() {
                return subject;
            }

            public void setSubject(Object subject) {
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

            public String getVideoContent() {
                return videoContent;
            }

            public void setVideoContent(String videoContent) {
                this.videoContent = videoContent;
            }
        }
    }
}