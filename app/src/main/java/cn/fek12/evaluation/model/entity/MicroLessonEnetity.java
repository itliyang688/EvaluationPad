package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: MicroLessonEnetity
 * @Description:
 * @CreateDate: 2019/11/18 10:08
 */
public class MicroLessonEnetity implements Serializable {

    /**
     * data : {"near":[{"videoId":1,"cacheKey":"special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*:video:1","structLayKey":"special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*","type":2,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542","videoName":"未来教育视频","videoCreateTime":null,"specialName":"专题视频","introduction":"未来教育视频"}],"hot":[{"videoId":1,"cacheKey":"special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*:video:1","structLayKey":"special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*","type":2,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542","videoName":"未来教育视频","videoCreateTime":null,"specialName":"专题视频","introduction":"未来教育视频"}],"recommoned":[{"videoId":1,"cacheKey":"special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*:video:1","structLayKey":"special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*","type":2,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542","videoName":"未来教育视频","videoCreateTime":null,"specialName":"专题视频","introduction":"未来教育视频"}]}
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
        private List<VideoBean> near;
        private List<VideoBean> hot;
        private List<VideoBean> recommoned;

        public List<VideoBean> getNear() {
            return near;
        }

        public void setNear(List<VideoBean> near) {
            this.near = near;
        }

        public List<VideoBean> getHot() {
            return hot;
        }

        public void setHot(List<VideoBean> hot) {
            this.hot = hot;
        }

        public List<VideoBean> getRecommoned() {
            return recommoned;
        }

        public void setRecommoned(List<VideoBean> recommoned) {
            this.recommoned = recommoned;
        }

        public static class VideoBean {
            /**
             * videoId : 1
             * cacheKey : special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*:video:1
             * structLayKey : special:*grade:1*:subject:1*:textbook:1*:semester:1*:chapter:1*
             * type : 2
             * addressUrl : http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542
             * videoName : 未来教育视频
             * videoCreateTime : null
             * specialName : 专题视频
             * introduction : 未来教育视频
             */

            private int videoId;
            private String cacheKey;
            private String structLayKey;
            private String playNum;
            private int type;
            private int isCollection;
            private long playScheduleTime = 0;
            private String addressUrl;
            private String videoName;
            private String videoCreateTime;
            private String specialName;
            private String introduction;
            private String textbookName;
            private String subjectName;

            public String getTextbookName() {
                return textbookName;
            }

            public void setTextbookName(String textbookName) {
                this.textbookName = textbookName;
            }

            public String getSubjectName() {
                return subjectName;
            }

            public void setSubjectName(String subjectName) {
                this.subjectName = subjectName;
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

            public String getPlayNum() {
                return playNum;
            }

            public void setPlayNum(String playNum) {
                this.playNum = playNum;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
            }

            public String getCacheKey() {
                return cacheKey;
            }

            public void setCacheKey(String cacheKey) {
                this.cacheKey = cacheKey;
            }

            public String getStructLayKey() {
                return structLayKey;
            }

            public void setStructLayKey(String structLayKey) {
                this.structLayKey = structLayKey;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAddressUrl() {
                return addressUrl;
            }

            public void setAddressUrl(String addressUrl) {
                this.addressUrl = addressUrl;
            }

            public String getVideoName() {
                return videoName;
            }

            public void setVideoName(String videoName) {
                this.videoName = videoName;
            }

            public String getVideoCreateTime() {
                return videoCreateTime;
            }

            public void setVideoCreateTime(String videoCreateTime) {
                this.videoCreateTime = videoCreateTime;
            }

            public String getSpecialName() {
                return specialName;
            }

            public void setSpecialName(String specialName) {
                this.specialName = specialName;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }
        }
    }
}
