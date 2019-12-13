package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: TestRecordEntity
 * @Description:
 * @CreateDate: 2019/12/13 11:04
 */
public class TestRecordEntity implements Serializable {

    /**
     * data : {"correctRateVo":{"rate":24.5,"totalCount":49,"rightTotalCount":12,"totalTime":null},"answers":"2019-12-10:0;2019-12-02:15;2019-11-25:0;2019-11-18:9","scopeCorrectRate":"2019-12-10:0;2019-12-02:46.7;2019-11-25:0;2019-11-18:44.4"}
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
         * correctRateVo : {"rate":24.5,"totalCount":49,"rightTotalCount":12,"totalTime":null}
         * answers : 2019-12-10:0;2019-12-02:15;2019-11-25:0;2019-11-18:9
         * scopeCorrectRate : 2019-12-10:0;2019-12-02:46.7;2019-11-25:0;2019-11-18:44.4
         */

        private CorrectRateVoBean correctRateVo;
        private String answers;
        private String scopeCorrectRate;

        public CorrectRateVoBean getCorrectRateVo() {
            return correctRateVo;
        }

        public void setCorrectRateVo(CorrectRateVoBean correctRateVo) {
            this.correctRateVo = correctRateVo;
        }

        public String getAnswers() {
            return answers;
        }

        public void setAnswers(String answers) {
            this.answers = answers;
        }

        public String getScopeCorrectRate() {
            return scopeCorrectRate;
        }

        public void setScopeCorrectRate(String scopeCorrectRate) {
            this.scopeCorrectRate = scopeCorrectRate;
        }

        public static class CorrectRateVoBean {
            /**
             * rate : 24.5
             * totalCount : 49
             * rightTotalCount : 12
             * totalTime : null
             */

            private double rate;
            private int totalCount;
            private int rightTotalCount;
            private String totalTime;

            public double getRate() {
                return rate;
            }

            public void setRate(double rate) {
                this.rate = rate;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public int getRightTotalCount() {
                return rightTotalCount;
            }

            public void setRightTotalCount(int rightTotalCount) {
                this.rightTotalCount = rightTotalCount;
            }

            public String getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(String totalTime) {
                this.totalTime = totalTime;
            }
        }
    }
}
