package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: YearMonthEntity
 * @Description:
 * @CreateDate: 2019/12/16 14:34
 */
public class YearMonthEntity implements Serializable {


    /**
     * data : [{"month":"2019.12","days":"12月13日,12月16日"}]
     * state : 0
     * message : 成功
     */

    private String state;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * month : 2019.12
         * days : 12月13日,12月16日
         */

        private String month;
        private String days;

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }
    }
}
