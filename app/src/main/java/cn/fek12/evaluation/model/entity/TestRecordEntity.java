package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: TestRecordEntity
 * @Description:
 * @CreateDate: 2019/12/13 11:04
 */
public class TestRecordEntity implements Serializable {

    /**
     * data : {"correctRateVo":{"rate":24.5,"totalCount":49,"rightTotalCount":12,"totalTime":84},"answers":"2019-12-13:0;2019-12-09:25;2019-12-02:15;2019-11-25:0","scopeCorrectRate":"2019-12-13:0;2019-12-09:4.0;2019-12-02:46.7;2019-11-25:0","more":[{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":1},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":18,"count":5,"rightAmount":2},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":18,"count":5,"rightAmount":2},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":18,"count":5,"rightAmount":3},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":10,"count":4,"rightAmount":2},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":10,"count":5,"rightAmount":2}]}
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
         * correctRateVo : {"rate":24.5,"totalCount":49,"rightTotalCount":12,"totalTime":84}
         * answers : 2019-12-13:0;2019-12-09:25;2019-12-02:15;2019-11-25:0
         * scopeCorrectRate : 2019-12-13:0;2019-12-09:4.0;2019-12-02:46.7;2019-11-25:0
         * more : [{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":1},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":1,"count":5,"rightAmount":0},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":18,"count":5,"rightAmount":2},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":18,"count":5,"rightAmount":2},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":18,"count":5,"rightAmount":3},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":10,"count":4,"rightAmount":2},{"subject":"数学","grade":"四年级","textBook":"人教版","knowledgePoint":"数级与数位","time":10,"count":5,"rightAmount":2}]
         */

        private CorrectRateVoBean correctRateVo;
        private String answers;
        private String scopeCorrectRate;
        private List<RecordInfoEntity> more;

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

        public List<RecordInfoEntity> getMore() {
            return more;
        }

        public void setMore(List<RecordInfoEntity> more) {
            this.more = more;
        }

        public static class CorrectRateVoBean {
            /**
             * rate : 24.5
             * totalCount : 49
             * rightTotalCount : 12
             * totalTime : 84
             */

            private double rate;
            private int totalCount;
            private int rightTotalCount;
            private int totalTime;

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

            public int getTotalTime() {
                return totalTime;
            }

            public void setTotalTime(int totalTime) {
                this.totalTime = totalTime;
            }
        }
    }
}
