package cn.fek12.evaluation.model.entity;

import java.util.List;

public class PromoteRecommedVideoEntity {


    /**
     * data : {"records":[{"videoId":"6be37d1f-c339-4d93-985f-d827a94c7017","videoName":"斯蒂芬森吃V型吃v","subjcetName":"小学数学","createDate":"2020/08/14","imgUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SuWAW5PFAAA4mgJ1utw001.jpg?token=d2d809d54f8ddcafc695e64b54b89748&ts=1598253824","videoUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SuWABOAvAIjznj-xCYw025.mp4?token=6837ca6148a9d8f0aa44d5ef2f0ab688&ts=1598253824","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null,"classMateCount":null},{"videoId":"b11e72c5-a74d-4746-b28f-8f32efd35362","videoName":"士大夫士大夫","subjcetName":"小学数学","createDate":"2020/08/14","imgUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SsyAD7AxAAA4mgJ1utw182.jpg?token=a98deb1dcf5b4d469b39ddc7bca8dcba&ts=1598253824","videoUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SsuATWqYAIjznj-xCYw807.mp4?token=5c10c38eeb8e9111133a7434437f27de&ts=1598253824","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null,"classMateCount":null},{"videoId":"1e4f08f0-3b28-4312-871f-2dc0bdbc47b1","videoName":"测试了看得开","subjcetName":"小学数学","createDate":"2020/08/14","imgUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SgaAYUaiAAA4mgJ1utw945.jpg?token=1175dfee38c1816882734ffbcba19f54&ts=1598253824","videoUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SgWAEjb3AIjznj-xCYw351.mp4?token=adeb35a73b14f75d4bde49f05adce35b&ts=1598253824","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null,"classMateCount":null}],"total":51,"size":3,"current":1,"searchCount":true,"pages":17}
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
         * records : [{"videoId":"6be37d1f-c339-4d93-985f-d827a94c7017","videoName":"斯蒂芬森吃V型吃v","subjcetName":"小学数学","createDate":"2020/08/14","imgUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SuWAW5PFAAA4mgJ1utw001.jpg?token=d2d809d54f8ddcafc695e64b54b89748&ts=1598253824","videoUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SuWABOAvAIjznj-xCYw025.mp4?token=6837ca6148a9d8f0aa44d5ef2f0ab688&ts=1598253824","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null,"classMateCount":null},{"videoId":"b11e72c5-a74d-4746-b28f-8f32efd35362","videoName":"士大夫士大夫","subjcetName":"小学数学","createDate":"2020/08/14","imgUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SsyAD7AxAAA4mgJ1utw182.jpg?token=a98deb1dcf5b4d469b39ddc7bca8dcba&ts=1598253824","videoUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SsuATWqYAIjznj-xCYw807.mp4?token=5c10c38eeb8e9111133a7434437f27de&ts=1598253824","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null,"classMateCount":null},{"videoId":"1e4f08f0-3b28-4312-871f-2dc0bdbc47b1","videoName":"测试了看得开","subjcetName":"小学数学","createDate":"2020/08/14","imgUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SgaAYUaiAAA4mgJ1utw945.jpg?token=1175dfee38c1816882734ffbcba19f54&ts=1598253824","videoUrl":"http://218.245.6.132//group1/M00/01/05/2vUGhF82SgWAEjb3AIjznj-xCYw351.mp4?token=adeb35a73b14f75d4bde49f05adce35b&ts=1598253824","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null,"classMateCount":null}]
         * total : 51
         * size : 3
         * current : 1
         * searchCount : true
         * pages : 17
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
             * videoId : 6be37d1f-c339-4d93-985f-d827a94c7017
             * videoName : 斯蒂芬森吃V型吃v
             * subjcetName : 小学数学
             * createDate : 2020/08/14
             * imgUrl : http://218.245.6.132//group1/M00/01/05/2vUGhF82SuWAW5PFAAA4mgJ1utw001.jpg?token=d2d809d54f8ddcafc695e64b54b89748&ts=1598253824
             * videoUrl : http://218.245.6.132//group1/M00/01/05/2vUGhF82SuWABOAvAIjznj-xCYw025.mp4?token=6837ca6148a9d8f0aa44d5ef2f0ab688&ts=1598253824
             * playScheduleTime : 0
             * isCollection : 0
             * playCount : 0
             * subject : null
             * textbookName : null
             * gradeName : null
             * classMateCount : null
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
            private Object subject;
            private Object textbookName;
            private Object gradeName;
            private int classMateCount;

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

            public Object getTextbookName() {
                return textbookName;
            }

            public void setTextbookName(Object textbookName) {
                this.textbookName = textbookName;
            }

            public Object getGradeName() {
                return gradeName;
            }

            public void setGradeName(Object gradeName) {
                this.gradeName = gradeName;
            }

            public int getClassMateCount() {
                return classMateCount;
            }

            public void setClassMateCount(int classMateCount) {
                this.classMateCount = classMateCount;
            }
        }
    }
}
