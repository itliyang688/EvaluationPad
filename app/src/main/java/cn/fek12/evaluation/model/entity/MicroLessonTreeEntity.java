package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: MicroLessonTreeEntity
 * @Description:
 * @CreateDate: 2019/11/24 18:23
 */
public class MicroLessonTreeEntity implements Serializable {


    /**
     * data : [{"videoId":106,"cacheKey":"special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:25*:video:106","structLayKey":"special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:25*","type":2,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-qmAMRqGAY8UIoY8rMM016.mp4","videoName":"测试专题视频","playNum":0,"videoCreateTime":"2019/11/22","isCollection":0,"specialName":"一年级语文数一数概念","introduction":"测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频","textbookName":"语文","subjectName":"语文"},{"videoId":104,"cacheKey":"special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:22*:video:104","structLayKey":"special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:22*","type":2,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-HWAaZ97AIjznj-xCYw118.mp4","videoName":"专题视频","playNum":46,"videoCreateTime":"2019/11/22","isCollection":0,"specialName":"一年级语文测试测试11111","introduction":"专题视频","textbookName":"语文","subjectName":"语文"},{"videoId":109,"cacheKey":"synchro:*grade:4*:subject:14*:textbook:18*:semester:16*:subjectCategory:28049*:video:109","structLayKey":"synchro:*grade:4*:subject:14*:textbook:18*:semester:16*:subjectCategory:28049*","type":1,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3aA1SATr_QAIjznj-xCYw301.mp4","videoName":"同步书记与数位","playNum":0,"videoCreateTime":"2019/11/23","isCollection":0,"textbookName":"数学","subjectName":"数学"},{"videoId":107,"cacheKey":"synchro:*grade:1*:subject:13*:textbook:63*:semester:16*:subjectCategory:50629*:video:107","structLayKey":"synchro:*grade:1*:subject:13*:textbook:63*:semester:16*:subjectCategory:50629*","type":1,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-wCAQ1LDAAgiTlvIWFk836.mp4","videoName":"1111测试同步视频222","playNum":0,"videoCreateTime":"2019/11/22","isCollection":0,"textbookName":"语文","subjectName":"语文"},{"videoId":105,"cacheKey":"synchro:*grade:1*:subject:13*:textbook:63*:semester:16*:subjectCategory:50593*:video:105","structLayKey":"synchro:*grade:1*:subject:13*:textbook:63*:semester:16*:subjectCategory:50593*","type":1,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-NCAdiKHAAgiTlvIWFk489.mp4","videoName":"同步视频","playNum":0,"videoCreateTime":"2019/11/22","isCollection":0,"textbookName":"语文","subjectName":"语文"},{"videoId":108,"cacheKey":"synchro:*grade:4*:subject:14*:textbook:18*:semester:16*:subjectCategory:28049*:video:108","structLayKey":"synchro:*grade:4*:subject:14*:textbook:18*:semester:16*:subjectCategory:28049*","type":1,"addressUrl":"http://218.245.6.132/group1/M00/00/00/2vUGhF3aAy6ARvCzAIjznj-xCYw660.mp4","videoName":"测试数级","playNum":3,"videoCreateTime":"2019/11/23","isCollection":0,"textbookName":"数学","subjectName":"数学"}]
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
         * videoId : 106
         * cacheKey : special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:25*:video:106
         * structLayKey : special:*grade:1*:subject:13*:textbook:63*:semester:16*:chapter:25*
         * type : 2
         * addressUrl : http://218.245.6.132/group1/M00/00/00/2vUGhF3Y-qmAMRqGAY8UIoY8rMM016.mp4
         * videoName : 测试专题视频
         * playNum : 0
         * videoCreateTime : 2019/11/22
         * isCollection : 0
         * specialName : 一年级语文数一数概念
         * introduction : 测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频测试专题视频
         * textbookName : 语文
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
        private int isCollection;
        private String specialName;
        private String introduction;
        private String textbookName;
        private String subjectName;

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
