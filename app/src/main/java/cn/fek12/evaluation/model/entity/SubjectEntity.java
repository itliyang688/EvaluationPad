package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: SubjectEntity
 * @Description:
 * @CreateDate: 2019/11/4 19:01
 */
public class SubjectEntity implements Serializable {

    /**
     * data : [{"textbook":[{"name":"部编版","semester":[{"name":"上学期","id":16},{"name":"下学期","id":17}],"id":63}],"name":"语文","id":13},{"textbook":[{"name":"人教版","semester":[{"name":"上学期","id":16},{"name":"下学期","id":17}],"id":18},{"name":"北师大版","semester":[{"name":"上学期","id":16},{"name":"下学期","id":17}],"id":64}],"name":"数学","id":14},{"textbook":[{"name":"牛津上海版","semester":[{"name":"上学期","id":16},{"name":"下学期","id":17}],"id":77}],"name":"英语","id":15}]
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
         * textbook : [{"name":"部编版","semester":[{"name":"上学期","id":16},{"name":"下学期","id":17}],"id":63}]
         * name : 语文
         * id : 13
         */

        private String name;
        private int id;
        private List<TextbookChildEntity> textbook;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<TextbookChildEntity> getTextbook() {
            return textbook;
        }

        public void setTextbook(List<TextbookChildEntity> textbook) {
            this.textbook = textbook;
        }

    }
}
