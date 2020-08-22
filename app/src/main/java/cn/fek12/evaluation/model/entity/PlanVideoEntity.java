package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

public class PlanVideoEntity implements Serializable {

    /**
     * data : [{"videoId":"845aaa17-6763-48a2-ad86-55591d55c5f6","videoName":"测试时长","videoUrl":"http://218.245.6.132//group1/M00/00/B2/2vUGhF8MI1uAWfryAFTZs3X4Kdk118.mp4?token=4d51ace25181753ee3394dba5a202ee7&ts=1597973876","imgUrl":"http://218.245.6.132//group1/M00/00/B2/2vUGhF8MI1yAeaJFAAArEyn-Zis276.jpg?token=0406c54dca6b509520efa43e56994d0a&ts=1597973876","palyCount":1,"length":489,"createDate":"2020/07/13","playScheduleTime":0,"isCollection":0},{"videoId":"74b6dd1d-b87c-462b-9ef0-ae989f612efd","videoName":"测试测试说得对","videoUrl":"http://218.245.6.132//group1/M00/00/B8/2vUGhF8nb3qAeHTCAIjznj-xCYw512.mp4?token=133ef6c41e0e21f5d19ce14e77219085&ts=1597973876","imgUrl":"http://218.245.6.132//group1/M00/00/B8/2vUGhF8nb3yALH93AAA4mgJ1utw152.jpg?token=55513abb04e6685d1330939b32f0eb9a&ts=1597973876","palyCount":1,"length":552,"createDate":"2020/08/03","playScheduleTime":0,"isCollection":0}]
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
         * videoId : 845aaa17-6763-48a2-ad86-55591d55c5f6
         * videoName : 测试时长
         * videoUrl : http://218.245.6.132//group1/M00/00/B2/2vUGhF8MI1uAWfryAFTZs3X4Kdk118.mp4?token=4d51ace25181753ee3394dba5a202ee7&ts=1597973876
         * imgUrl : http://218.245.6.132//group1/M00/00/B2/2vUGhF8MI1yAeaJFAAArEyn-Zis276.jpg?token=0406c54dca6b509520efa43e56994d0a&ts=1597973876
         * palyCount : 1
         * length : 489
         * createDate : 2020/07/13
         * playScheduleTime : 0
         * isCollection : 0
         */

        private String videoId;
        private String videoName;
        private String videoUrl;
        private String imgUrl;
        private int palyCount;
        private int length;
        private String createDate;
        private int playScheduleTime;
        private int isCollection;

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

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getPalyCount() {
            return palyCount;
        }

        public void setPalyCount(int palyCount) {
            this.palyCount = palyCount;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
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
    }
}
