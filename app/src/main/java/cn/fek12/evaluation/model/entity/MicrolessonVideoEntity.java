package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

public class MicrolessonVideoEntity implements Serializable {


    /**
     * data : {"records":[{"videoId":"2d532712-2182-4dba-95b7-3cb45a2a07a2","videoName":"测试视频2","subjcetName":"小学数学","createDate":"2020-07-10T12:19:49.000+0000","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IXOaAVDsTAAA5Nhd3I5E058.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IXOWAKy0xALXuAGQXBck930.mp4","playScheduleTime":null,"isCollection":0,"playCount":0,"subject":null,"textbook":null},{"videoId":"9559ab59-1984-41d4-9be6-4537b781bb3b","videoName":"测试","subjcetName":"小学数学","createDate":"2020-07-10T12:15:35.000+0000","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IW-eAf-KvAAA5Nhd3I5E919.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IW-eAPoeiALXuAGQXBck491.mp4","playScheduleTime":null,"isCollection":0,"playCount":0,"subject":null,"textbook":null}],"total":2,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"videoId":"2d532712-2182-4dba-95b7-3cb45a2a07a2","videoName":"测试视频2","subjcetName":"小学数学","createDate":"2020-07-10T12:19:49.000+0000","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IXOaAVDsTAAA5Nhd3I5E058.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IXOWAKy0xALXuAGQXBck930.mp4","playScheduleTime":null,"isCollection":0,"playCount":0,"subject":null,"textbook":null},{"videoId":"9559ab59-1984-41d4-9be6-4537b781bb3b","videoName":"测试","subjcetName":"小学数学","createDate":"2020-07-10T12:15:35.000+0000","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IW-eAf-KvAAA5Nhd3I5E919.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8IW-eAPoeiALXuAGQXBck491.mp4","playScheduleTime":null,"isCollection":0,"playCount":0,"subject":null,"textbook":null}]
         * total : 2
         * size : 10
         * current : 1
         * searchCount : true
         * pages : 1
         */

        private int total;
        private int size;
        private int current;
        private boolean searchCount;
        private int pages;
        private List<RecordsBean> records;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public boolean isSearchCount() {
            return searchCount;
        }

        public void setSearchCount(boolean searchCount) {
            this.searchCount = searchCount;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<RecordsBean> getRecords() {
            return records;
        }

        public void setRecords(List<RecordsBean> records) {
            this.records = records;
        }

        public static class RecordsBean {
            /**
             * videoId : 2d532712-2182-4dba-95b7-3cb45a2a07a2
             * videoName : 测试视频2
             * subjcetName : 小学数学
             * createDate : 2020-07-10T12:19:49.000+0000
             * imgUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8IXOaAVDsTAAA5Nhd3I5E058.jpg
             * videoUrl : http://218.245.6.132/group1/M00/00/B1/2vUGhF8IXOWAKy0xALXuAGQXBck930.mp4
             * playScheduleTime : null
             * isCollection : 0
             * playCount : 0
             * subject : null
             * textbook : null
             */

            private String videoId;
            private String videoName;
            private String subjcetName;
            private String createDate;
            private String imgUrl;
            private String videoUrl;
            private long playScheduleTime;
            private int isCollection;
            private int playCount;
            private String subject;
            private String textbook;

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

            public Object getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public Object getTextbook() {
                return textbook;
            }

            public void setTextbook(String textbook) {
                this.textbook = textbook;
            }
        }
    }
}
