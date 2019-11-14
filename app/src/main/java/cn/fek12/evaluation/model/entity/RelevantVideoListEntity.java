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
     * data : {"video":{"id":20,"createDate":null,"modifyDate":null,"name":"样本平均数的价值","addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4","userId":null,"length":null},"relatedVideo":[]}
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
         * video : {"id":20,"createDate":null,"modifyDate":null,"name":"样本平均数的价值","addressUrl":"http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4","userId":null,"length":null}
         * relatedVideo : []
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
             * id : 20
             * createDate : null
             * modifyDate : null
             * name : 样本平均数的价值
             * addressUrl : http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4
             * userId : null
             * length : null
             */

            private int id;
            private Object createDate;
            private Object modifyDate;
            private String name;
            private String addressUrl;
            private Object userId;
            private Object length;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getCreateDate() {
                return createDate;
            }

            public void setCreateDate(Object createDate) {
                this.createDate = createDate;
            }

            public Object getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(Object modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddressUrl() {
                return addressUrl;
            }

            public void setAddressUrl(String addressUrl) {
                this.addressUrl = addressUrl;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getLength() {
                return length;
            }

            public void setLength(Object length) {
                this.length = length;
            }
        }
    }
}
