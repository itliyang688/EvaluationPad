package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

public class UpdateApkEntity implements Serializable {

    /**
     * data : {"id":2,"versionCode":"2","versionName":"test2","versionContent":"test2","resourceUrl":null,"isMust":0,"isOld":1,"createDate":"2020-08-05T16:00:00.000+0000"}
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
         * id : 2
         * versionCode : 2
         * versionName : test2
         * versionContent : test2
         * resourceUrl : null
         * isMust : 0
         * isOld : 1
         * createDate : 2020-08-05T16:00:00.000+0000
         */

        private int id;
        private String versionCode;
        private String versionName;
        private String versionContent;
        private String resourceUrl;
        private int isMust;
        private int isOld;
        private String createDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionContent() {
            return versionContent;
        }

        public void setVersionContent(String versionContent) {
            this.versionContent = versionContent;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public int getIsMust() {
            return isMust;
        }

        public void setIsMust(int isMust) {
            this.isMust = isMust;
        }

        public int getIsOld() {
            return isOld;
        }

        public void setIsOld(int isOld) {
            this.isOld = isOld;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
