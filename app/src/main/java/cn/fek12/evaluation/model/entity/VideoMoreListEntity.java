package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: VideoMoreListEntity
 * @Description:
 * @CreateDate: 2019/11/24 12:22
 */
public class VideoMoreListEntity implements Serializable {

    /**
     * data : [{"videoId":104,"cacheKey":"special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:22*:video:104","structLayKey":"special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:22*","type":2,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-HWAaZ97AIjznj-xCYw118.mp4","videoName":"专题视频","playNum":43,"videoCreateTime":"2019/11/22","isCollection":0,"specialName":"一年级语文测试测试11111","introduction":"专题视频","textbookName":"部编版","subjectName":"语文"}]
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
         * videoId : 104
         * cacheKey : special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:22*:video:104
         * structLayKey : special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:22*
         * type : 2
         * addressUrl : http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-HWAaZ97AIjznj-xCYw118.mp4
         * videoName : 专题视频
         * playNum : 43
         * videoCreateTime : 2019/11/22
         * isCollection : 0
         * specialName : 一年级语文测试测试11111
         * introduction : 专题视频
         * textbookName : 部编版
         * subjectName : 语文
         */

        private int videoId;
        private String cacheKey;
        private String structLayKey;
        private int type;
        private String addressUrl;
        private String videoName;
        private int playNum;
        private String videoCreateTime;
        private String imgUrl;
        private int isCollection;
        private long playScheduleTime;
        private String specialName;
        private String introduction;
        private String textbookName;
        private String subjectName;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public long getPlayScheduleTime() {
            return playScheduleTime;
        }

        public void setPlayScheduleTime(long playScheduleTime) {
            this.playScheduleTime = playScheduleTime;
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

        public int getPlayNum() {
            return playNum;
        }

        public void setPlayNum(int playNum) {
            this.playNum = playNum;
        }

        public String getVideoCreateTime() {
            return videoCreateTime;
        }

        public void setVideoCreateTime(String videoCreateTime) {
            this.videoCreateTime = videoCreateTime;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
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
    }
}
