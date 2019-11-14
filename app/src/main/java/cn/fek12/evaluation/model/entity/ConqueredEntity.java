package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: ConqueredEntity
 * @Description:
 * @CreateDate: 2019/11/14 11:48
 */
public class ConqueredEntity implements Serializable {

    /**
     * data : [{"score":42,"weakReason":"理解、分析","name":"数级与数位","studyLevel":40,"id":28049},{"score":29,"weakReason":"识记","name":"亿以内数的计数单位","studyLevel":0,"id":28044}]
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
         * score : 42
         * weakReason : 理解、分析
         * name : 数级与数位
         * studyLevel : 40
         * id : 28049
         */

        private int score;
        private String weakReason;
        private String name;
        private int studyLevel;
        private int id;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getWeakReason() {
            return weakReason;
        }

        public void setWeakReason(String weakReason) {
            this.weakReason = weakReason;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStudyLevel() {
            return studyLevel;
        }

        public void setStudyLevel(int studyLevel) {
            this.studyLevel = studyLevel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
