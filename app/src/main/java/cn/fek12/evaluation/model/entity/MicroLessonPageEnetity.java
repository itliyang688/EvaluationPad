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
public class MicroLessonPageEnetity implements Serializable {


    /**
     * data : {"records":[{"videoId":"9ff519a5-a5fa-48c2-962e-47f1c029e88b","videoName":"test-gaowx","subjcetName":"初中语文","createDate":"2020/07/15","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OePSASbo1AAGDcBU2MFE776.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OePSARkZQACSeHrS87oE038.mp4","playScheduleTime":0,"isCollection":0,"playCount":2,"subject":null,"textbookName":null,"gradeName":null},{"videoId":"7c6b6f76-d774-41d9-8c88-4de813b66fba","videoName":"test-gaowx","subjcetName":"初中化学","createDate":"2020/07/15","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeGSAWInOAAGDcBU2MFE945.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeGKACk8zACSeHrS87oE651.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null},{"videoId":"bd13a7be-3fa1-4f39-8195-caeba345a5ed","videoName":"test-gaowx","subjcetName":"初中英语","createDate":"2020/07/15","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeEyAGzClAABZ3Y9eOlU928.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeEyAdS1bAKUvhNDG1nk823.mp4","playScheduleTime":0,"isCollection":0,"playCount":1,"subject":null,"textbookName":null,"gradeName":null},{"videoId":"5d45d386-50e5-4a04-bc27-7af29586bcb5","videoName":"测试一下","subjcetName":"初中英语","createDate":"2020/07/11","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JWiqAHrg5AAA5Nhd3I5E687.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JWimAfzm4ALXuAGQXBck505.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null}],"total":4,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"videoId":"9ff519a5-a5fa-48c2-962e-47f1c029e88b","videoName":"test-gaowx","subjcetName":"初中语文","createDate":"2020/07/15","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OePSASbo1AAGDcBU2MFE776.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OePSARkZQACSeHrS87oE038.mp4","playScheduleTime":0,"isCollection":0,"playCount":2,"subject":null,"textbookName":null,"gradeName":null},{"videoId":"7c6b6f76-d774-41d9-8c88-4de813b66fba","videoName":"test-gaowx","subjcetName":"初中化学","createDate":"2020/07/15","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeGSAWInOAAGDcBU2MFE945.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeGKACk8zACSeHrS87oE651.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null},{"videoId":"bd13a7be-3fa1-4f39-8195-caeba345a5ed","videoName":"test-gaowx","subjcetName":"初中英语","createDate":"2020/07/15","imgUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeEyAGzClAABZ3Y9eOlU928.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B2/2vUGhF8OeEyAdS1bAKUvhNDG1nk823.mp4","playScheduleTime":0,"isCollection":0,"playCount":1,"subject":null,"textbookName":null,"gradeName":null},{"videoId":"5d45d386-50e5-4a04-bc27-7af29586bcb5","videoName":"测试一下","subjcetName":"初中英语","createDate":"2020/07/11","imgUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JWiqAHrg5AAA5Nhd3I5E687.jpg","videoUrl":"http://218.245.6.132/group1/M00/00/B1/2vUGhF8JWimAfzm4ALXuAGQXBck505.mp4","playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":null,"gradeName":null}]
         * total : 4
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
             * videoId : 9ff519a5-a5fa-48c2-962e-47f1c029e88b
             * videoName : test-gaowx
             * subjcetName : 初中语文
             * createDate : 2020/07/15
             * imgUrl : http://218.245.6.132/group1/M00/00/B2/2vUGhF8OePSASbo1AAGDcBU2MFE776.jpg
             * videoUrl : http://218.245.6.132/group1/M00/00/B2/2vUGhF8OePSARkZQACSeHrS87oE038.mp4
             * playScheduleTime : 0
             * isCollection : 0
             * playCount : 2
             * subject : null
             * textbookName : null
             * gradeName : null
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
