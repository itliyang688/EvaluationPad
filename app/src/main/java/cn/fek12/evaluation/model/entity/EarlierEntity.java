package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: EarlierEntity
 * @Description:
 * @CreateDate: 2019/11/4 10:27
 */
public class EarlierEntity implements Serializable {

    /**
     * data : {"page_info":{"totalCount":4,"totalPage":1,"pageSize":30,"currentPage":1},"papers":[{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11430,"paperResultDate":"2019-10-14 16:32:28.0"},{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11431,"paperResultDate":"2019-10-13 16:32:50.0"},{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11432,"paperResultDate":"2019-10-12 16:50:47.0"},{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11433,"paperResultDate":"2019-10-11 18:07:27.0"}]}
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
         * page_info : {"totalCount":4,"totalPage":1,"pageSize":30,"currentPage":1}
         * papers : [{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11430,"paperResultDate":"2019-10-14 16:32:28.0"},{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11431,"paperResultDate":"2019-10-13 16:32:50.0"},{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11432,"paperResultDate":"2019-10-12 16:50:47.0"},{"id":15962,"name":"test0924","description":"测试","score":0,"second":12600,"courseName":"","isanswered":0,"grade":1,"subject":13,"imageUrl":"http://192.168.0.60:8084/noc_basicedu/upload/image/201909/dcacae97-80de-47ce-8580-0024f87fb5aa.jpg","paperResultId":11433,"paperResultDate":"2019-10-11 18:07:27.0"}]
         */

        private PageInfoBean page_info;
        private List<PresentationEntity> papers;

        public PageInfoBean getPage_info() {
            return page_info;
        }

        public void setPage_info(PageInfoBean page_info) {
            this.page_info = page_info;
        }

        public List<PresentationEntity> getPapers() {
            return papers;
        }

        public void setPapers(List<PresentationEntity> papers) {
            this.papers = papers;
        }

        public static class PageInfoBean {
            /**
             * totalCount : 4
             * totalPage : 1
             * pageSize : 30
             * currentPage : 1
             */

            private int totalCount;
            private int totalPage;
            private int pageSize;
            private int currentPage;

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }
        }
    }
}
