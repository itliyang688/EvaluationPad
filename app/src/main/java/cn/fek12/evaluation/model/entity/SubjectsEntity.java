package cn.fek12.evaluation.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: SubjectsEntity
 * @Description:
 * @CreateDate: 2019/12/13 16:21
 */
public class SubjectsEntity implements Serializable {


    /**
     * data : [{"isdel":0,"subId":"95301ab4-1e5f-11e5-b161-1c4bd611580c","subName":"小学语文","secId":"18abe8fa-1698-11e6-af00-00163e022218","parent":"2cbd3ad7-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"5bd26d54-1d8c-11e6-85b3-00163e022218","subName":"小学英语","secId":"18abe8fa-1698-11e6-af00-00163e022218","parent":"71fde1a1-af97-11ea-a8b4-0242ac110003","startGrade":3},{"isdel":0,"subId":"2b923c2c-2d0b-11ea-85e1-00155d79743b","subName":"小学数学","secId":"18abe8fa-1698-11e6-af00-00163e022218","parent":"71fab6b1-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"33268067-64f4-4ecc-98ea-1cff58fa3fcf","subName":"初中语文","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"2cbd3ad7-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"271bdcf0-42e0-40af-9772-1aabf1063afe","subName":"初中英语","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"71fde1a1-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"4055714d-99ca-11ea-8a98-fa163e3f02e7","subName":"初中科学","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"721d48ac-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"b364f20d-9738-11ea-8a98-fa163e3f02e7","subName":"初中生物","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"7210126c-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"cb3004af-0c07-4bfb-9faf-98d4f5e31c6d","subName":"初中物理","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"7200a39d-af97-11ea-a8b4-0242ac110003","startGrade":8},{"isdel":0,"subId":"b361742b-9738-11ea-8a98-fa163e3f02e7","subName":"初中歷史","secId":"3d1dad8c-1698-11e6-af00-00163e022218"},{"isdel":0,"subId":"e122ffa3-0093-4fc2-9e90-c456270acd70","subName":"初中数学","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"71fab6b1-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"54c0f861-7e8c-43a2-b060-a7f3086cf0d1","subName":"初中思想品德","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"722304ec-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"b3632cff-9738-11ea-8a98-fa163e3f02e7","subName":"初中地理","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"7215429d-af97-11ea-a8b4-0242ac110003"},{"isdel":0,"subId":"320f13f9-f7ca-47ff-8cb6-3fa8c3f3ccee","subName":"初中化学","secId":"3d1dad8c-1698-11e6-af00-00163e022218","parent":"720408f5-af97-11ea-a8b4-0242ac110003","startGrade":9}]
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
         * isdel : 0
         * subId : 95301ab4-1e5f-11e5-b161-1c4bd611580c
         * subName : 小学语文
         * secId : 18abe8fa-1698-11e6-af00-00163e022218
         * parent : 2cbd3ad7-af97-11ea-a8b4-0242ac110003
         * startGrade : 3
         */

        private int isdel;
        private String subId;
        private String subName;
        private String secId;
        private String parent;
        private int startGrade;

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getSubId() {
            return subId;
        }

        public void setSubId(String subId) {
            this.subId = subId;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }

        public String getSecId() {
            return secId;
        }

        public void setSecId(String secId) {
            this.secId = secId;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public int getStartGrade() {
            return startGrade;
        }

        public void setStartGrade(int startGrade) {
            this.startGrade = startGrade;
        }
    }
}
