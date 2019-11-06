package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: TopicCountEntity
 * @Description:
 * @CreateDate: 2019/11/6 17:28
 */
public class TopicCountEntity implements Serializable {

    /**
     * data : {"single":{"common":3,"difficult":1,"easy":10},"multiple":{"common":3,"difficult":1,"easy":10},"judge":{"common":3,"difficult":1,"easy":10}}
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
         * single : {"common":3,"difficult":1,"easy":10}
         * multiple : {"common":3,"difficult":1,"easy":10}
         * judge : {"common":3,"difficult":1,"easy":10}
         */

        private TopicCountBean single;
        private TopicCountBean multiple;
        private TopicCountBean judge;

        public TopicCountBean getSingle() {
            return single;
        }

        public void setSingle(TopicCountBean single) {
            this.single = single;
        }

        public TopicCountBean getMultiple() {
            return multiple;
        }

        public void setMultiple(TopicCountBean multiple) {
            this.multiple = multiple;
        }

        public TopicCountBean getJudge() {
            return judge;
        }

        public void setJudge(TopicCountBean judge) {
            this.judge = judge;
        }

        public static class TopicCountBean {
            /**
             * common : 3
             * difficult : 1
             * easy : 10
             */

            private int common;
            private int difficult;
            private int easy;

            public int getCommon() {
                return common;
            }

            public void setCommon(int common) {
                this.common = common;
            }

            public int getDifficult() {
                return difficult;
            }

            public void setDifficult(int difficult) {
                this.difficult = difficult;
            }

            public int getEasy() {
                return easy;
            }

            public void setEasy(int easy) {
                this.easy = easy;
            }
        }
    }
}
