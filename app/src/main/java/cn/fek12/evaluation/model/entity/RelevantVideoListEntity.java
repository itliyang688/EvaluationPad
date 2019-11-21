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
     * data : {"video":{"videoId":21,"type":0,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl2yqzuAD3DaATnuuNJ5UHk845.mp4","videoName":"输错数据的平均数","videoCreateTime":1574049034260,"playScheduleTime":60,"count":0,"collect":0,"subject":"数学","textbook":"人教版"},"key":"*grade:4*:subject:14*:textbook:18*:semester:16*:subjectCategory:28049*","relatedVideo":[{"videoId":20,"type":0,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4","videoName":"样本平均数的价值","videoCreateTime":1574049004953,"playScheduleTime":60,"count":0,"collect":0,"subject":"数学","textbook":"人教版"}]}
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
         * video : {"videoId":21,"type":0,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl2yqzuAD3DaATnuuNJ5UHk845.mp4","videoName":"输错数据的平均数","videoCreateTime":1574049034260,"playScheduleTime":60,"count":0,"collect":0,"subject":"数学","textbook":"人教版"}
         * key : *grade:4*:subject:14*:textbook:18*:semester:16*:subjectCategory:28049*
         * relatedVideo : [{"videoId":20,"type":0,"addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4","videoName":"样本平均数的价值","videoCreateTime":1574049004953,"playScheduleTime":60,"count":0,"collect":0,"subject":"数学","textbook":"人教版"}]
         */

        private VideoBean video;
        private List<VideoBean> relatedVideo;

        public VideoBean getVideo() {
            return video;
        }

        public void setVideo(VideoBean video) {
            this.video = video;
        }

        public List<VideoBean> getRelatedVideo() {
            return relatedVideo;
        }

        public void setRelatedVideo(List<VideoBean> relatedVideo) {
            this.relatedVideo = relatedVideo;
        }

        public static class VideoBean {
            /**
             * videoId : 21
             * type : 0
             * addressUrl : http://192.168.0.46/group1/M00/00/00/wKgALl2yqzuAD3DaATnuuNJ5UHk845.mp4
             * videoName : 输错数据的平均数
             * videoCreateTime : 1574049034260
             * playScheduleTime : 60
             * count : 0
             * collect : 0
             * subject : 数学
             * textbook : 人教版
             */

            private int videoId;
            private int type;
            private String addressUrl;
            private String videoName;
            private String cacheKey;
            private long videoCreateTime;
            private int playScheduleTime;
            private int count;
            private int collect;
            private String subject;
            private String textbook;

            public String getCacheKey() {
                return cacheKey;
            }

            public void setCacheKey(String cacheKey) {
                this.cacheKey = cacheKey;
            }

            public int getVideoId() {
                return videoId;
            }

            public void setVideoId(int videoId) {
                this.videoId = videoId;
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

            public long getVideoCreateTime() {
                return videoCreateTime;
            }

            public void setVideoCreateTime(long videoCreateTime) {
                this.videoCreateTime = videoCreateTime;
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

            public int getCollect() {
                return collect;
            }

            public void setCollect(int collect) {
                this.collect = collect;
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
        }
    }
}
