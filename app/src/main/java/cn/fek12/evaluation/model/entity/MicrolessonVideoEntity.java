package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

public class MicrolessonVideoEntity implements Serializable {


    /**
     * data : {"records":[{"videoId":"81b6a901-78fb-4035-b68c-9071eb5a4e23","videoName":"测试视频072005","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fk4mARRT6AAA4_a4BYjE130.jpg?token=abf4721b3c04b8dbc6a2a7b06e1d60ae&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"23cf72a5-1ac7-489c-a34c-089d3c2b980b","videoName":"ces","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8flJ2AQOKyAABkjuf62tc283.jpg?token=affecc9aac1ff9ec1575f0fd96a673d6&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"8ce5cc9b-ec10-4a48-817a-0774a6e9b294","videoName":"测试一下视频","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fmhKAKnCvAABOyzedbF8773.jpg?token=6eecb413d7d13511010a5c1ec532a75c&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"d082b2f7-bf53-4f27-8e30-bf5703f5c162","videoName":"dfg0728","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fofeAREoHAAA4_a4BYjE057.jpg?token=17272c3961752b1c2b2e199f257dd73d&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"7f504a94-cee3-4ba2-9da1-b6e90363c4b8","videoName":"ces1256","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fo12AW45rAAA4_a4BYjE178.jpg?token=732814b3825acd32800c06e5adb49602&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"330fb7bd-4ee1-4a56-9410-f93a810bcebb","videoName":"cdsdfdf","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fzJCAdVPlAABkjuf62tc078.jpg?token=92e58eded6d54207d958a812958f3230&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"8fc75241-55f7-4cb3-b1a2-faecc3b61d3c","videoName":"对对对","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8f0aWAA8L8AAA4_a4BYjE991.jpg?token=a86ac89e6e54cd9813c86560a42706c7&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"94687cb2-c2f5-4ea5-aaf9-ce6b0f8ad425","videoName":"kipppo","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fzZuAekZaAAA4_a4BYjE739.jpg?token=21df6382537911d0f6d4bf44a29acc19&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"aa5fe252-18a0-4548-bb56-3fd4f46704fb","videoName":"xxx","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fzgqALBZwAABkjuf62tc465.jpg?token=7cf66472394e339056371e5dfbdc42eb&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"f73613a3-b493-444a-bc6c-3b34264f746d","videoName":"dd","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8f0AWACVVdAABOyzedbF8990.jpg?token=d4128d5c3064959ff622257dea9d8daa&ts=1595923361","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"}],"total":10,"size":10,"current":1,"searchCount":true,"pages":1}
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
         * records : [{"videoId":"81b6a901-78fb-4035-b68c-9071eb5a4e23","videoName":"测试视频072005","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fk4mARRT6AAA4_a4BYjE130.jpg?token=abf4721b3c04b8dbc6a2a7b06e1d60ae&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"23cf72a5-1ac7-489c-a34c-089d3c2b980b","videoName":"ces","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8flJ2AQOKyAABkjuf62tc283.jpg?token=affecc9aac1ff9ec1575f0fd96a673d6&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"8ce5cc9b-ec10-4a48-817a-0774a6e9b294","videoName":"测试一下视频","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fmhKAKnCvAABOyzedbF8773.jpg?token=6eecb413d7d13511010a5c1ec532a75c&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"d082b2f7-bf53-4f27-8e30-bf5703f5c162","videoName":"dfg0728","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fofeAREoHAAA4_a4BYjE057.jpg?token=17272c3961752b1c2b2e199f257dd73d&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"7f504a94-cee3-4ba2-9da1-b6e90363c4b8","videoName":"ces1256","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fo12AW45rAAA4_a4BYjE178.jpg?token=732814b3825acd32800c06e5adb49602&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"330fb7bd-4ee1-4a56-9410-f93a810bcebb","videoName":"cdsdfdf","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fzJCAdVPlAABkjuf62tc078.jpg?token=92e58eded6d54207d958a812958f3230&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"8fc75241-55f7-4cb3-b1a2-faecc3b61d3c","videoName":"对对对","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8f0aWAA8L8AAA4_a4BYjE991.jpg?token=a86ac89e6e54cd9813c86560a42706c7&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"94687cb2-c2f5-4ea5-aaf9-ce6b0f8ad425","videoName":"kipppo","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fzZuAekZaAAA4_a4BYjE739.jpg?token=21df6382537911d0f6d4bf44a29acc19&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"aa5fe252-18a0-4548-bb56-3fd4f46704fb","videoName":"xxx","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8fzgqALBZwAABkjuf62tc465.jpg?token=7cf66472394e339056371e5dfbdc42eb&ts=1595923360","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"},{"videoId":"f73613a3-b493-444a-bc6c-3b34264f746d","videoName":"dd","subjcetName":"小学数学","createDate":"2020/07/28","imgUrl":"http://218.245.6.132//group1/M00/00/B7/2vUGhF8f0AWACVVdAABOyzedbF8990.jpg?token=d4128d5c3064959ff622257dea9d8daa&ts=1595923361","videoUrl":null,"playScheduleTime":0,"isCollection":0,"playCount":0,"subject":null,"textbookName":"北师大版","gradeName":"一年级"}]
         * total : 10
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
             * videoId : 81b6a901-78fb-4035-b68c-9071eb5a4e23
             * videoName : 测试视频072005
             * subjcetName : 小学数学
             * createDate : 2020/07/28
             * imgUrl : http://218.245.6.132//group1/M00/00/B7/2vUGhF8fk4mARRT6AAA4_a4BYjE130.jpg?token=abf4721b3c04b8dbc6a2a7b06e1d60ae&ts=1595923360
             * videoUrl : null
             * playScheduleTime : 0
             * isCollection : 0
             * playCount : 0
             * subject : null
             * textbookName : 北师大版
             * gradeName : 一年级
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
