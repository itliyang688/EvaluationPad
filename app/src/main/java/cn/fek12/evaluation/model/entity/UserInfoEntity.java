package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: UserInfoEntity
 * @Description:
 * @CreateDate: 2019/12/30 10:16
 */
public class UserInfoEntity implements Serializable {


    /**
     * root : {"account":{"userId":"60AA28D1-5DA4-5DE9-73D7-9FE4658FEE1A","userAccount":1002,"userName":"学生2","userRole":"Student","unitId":"C7311998-F8BF-74B5-BD90-9F7E3A069785","unitName":"未来教育集团","provinceName":"北京","provinceId":110000,"areaName":"东城区","areaId":110101,"cityId":110100,"cityName":"北京市","per_id":"60AA28D1-5DA4-5DE9-73D7-9FE4658FEE1A","per_name":"学生2","per_userid":1002,"class_uid":null,"class_name":null,"cog_id":null,"cog_name":null,"per_level":1,"per_sex":1,"per_birthday":"1970-01-01","per_mobile":"","per_email":"","per_ethnicity":null,"gp_name":null,"gc_name":null,"ga_name":null}}
     */

    private RootBean root;

    public RootBean getRoot() {
        return root;
    }

    public void setRoot(RootBean root) {
        this.root = root;
    }

    public static class RootBean {
        /**
         * account : {"userId":"60AA28D1-5DA4-5DE9-73D7-9FE4658FEE1A","userAccount":1002,"userName":"学生2","userRole":"Student","unitId":"C7311998-F8BF-74B5-BD90-9F7E3A069785","unitName":"未来教育集团","provinceName":"北京","provinceId":110000,"areaName":"东城区","areaId":110101,"cityId":110100,"cityName":"北京市","per_id":"60AA28D1-5DA4-5DE9-73D7-9FE4658FEE1A","per_name":"学生2","per_userid":1002,"class_uid":null,"class_name":null,"cog_id":null,"cog_name":null,"per_level":1,"per_sex":1,"per_birthday":"1970-01-01","per_mobile":"","per_email":"","per_ethnicity":null,"gp_name":null,"gc_name":null,"ga_name":null}
         */

        private AccountBean account;

        public AccountBean getAccount() {
            return account;
        }

        public void setAccount(AccountBean account) {
            this.account = account;
        }

        public static class AccountBean {
            /**
             * userId : 60AA28D1-5DA4-5DE9-73D7-9FE4658FEE1A
             * userAccount : 1002
             * userName : 学生2
             * userRole : Student
             * unitId : C7311998-F8BF-74B5-BD90-9F7E3A069785
             * unitName : 未来教育集团
             * provinceName : 北京
             * provinceId : 110000
             * areaName : 东城区
             * areaId : 110101
             * cityId : 110100
             * cityName : 北京市
             * per_id : 60AA28D1-5DA4-5DE9-73D7-9FE4658FEE1A
             * per_name : 学生2
             * per_userid : 1002
             * class_uid : null
             * class_name : null
             * cog_id : null
             * cog_name : null
             * per_level : 1
             * per_sex : 1
             * per_birthday : 1970-01-01
             * per_mobile : 
             * per_email : 
             * per_ethnicity : null
             * gp_name : null
             * gc_name : null
             * ga_name : null
             */

            private String userId;
            private int userAccount;
            private String userName;
            private String userRole;
            private String unitId;
            private String unitName;
            private String provinceName;
            private int provinceId;
            private String areaName;
            private int areaId;
            private int cityId;
            private String cityName;
            private String per_id;
            private String per_name;
            private int per_userid;
            private String class_uid;
            private String class_name;
            private String cog_id;
            private String cog_name;
            private int per_level;
            private int per_sex;
            private String per_birthday;
            private String per_mobile;
            private String per_email;
            private String per_ethnicity;
            private String gp_name;
            private String gc_name;
            private String ga_name;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getUserAccount() {
                return userAccount;
            }

            public void setUserAccount(int userAccount) {
                this.userAccount = userAccount;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getUserRole() {
                return userRole;
            }

            public void setUserRole(String userRole) {
                this.userRole = userRole;
            }

            public String getUnitId() {
                return unitId;
            }

            public void setUnitId(String unitId) {
                this.unitId = unitId;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public int getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(int provinceId) {
                this.provinceId = provinceId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getPer_id() {
                return per_id;
            }

            public void setPer_id(String per_id) {
                this.per_id = per_id;
            }

            public String getPer_name() {
                return per_name;
            }

            public void setPer_name(String per_name) {
                this.per_name = per_name;
            }

            public int getPer_userid() {
                return per_userid;
            }

            public void setPer_userid(int per_userid) {
                this.per_userid = per_userid;
            }

            public String getClass_uid() {
                return class_uid;
            }

            public void setClass_uid(String class_uid) {
                this.class_uid = class_uid;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getCog_id() {
                return cog_id;
            }

            public void setCog_id(String cog_id) {
                this.cog_id = cog_id;
            }

            public String getCog_name() {
                return cog_name;
            }

            public void setCog_name(String cog_name) {
                this.cog_name = cog_name;
            }

            public int getPer_level() {
                return per_level;
            }

            public void setPer_level(int per_level) {
                this.per_level = per_level;
            }

            public int getPer_sex() {
                return per_sex;
            }

            public void setPer_sex(int per_sex) {
                this.per_sex = per_sex;
            }

            public String getPer_birthday() {
                return per_birthday;
            }

            public void setPer_birthday(String per_birthday) {
                this.per_birthday = per_birthday;
            }

            public String getPer_mobile() {
                return per_mobile;
            }

            public void setPer_mobile(String per_mobile) {
                this.per_mobile = per_mobile;
            }

            public String getPer_email() {
                return per_email;
            }

            public void setPer_email(String per_email) {
                this.per_email = per_email;
            }

            public String getPer_ethnicity() {
                return per_ethnicity;
            }

            public void setPer_ethnicity(String per_ethnicity) {
                this.per_ethnicity = per_ethnicity;
            }

            public String getGp_name() {
                return gp_name;
            }

            public void setGp_name(String gp_name) {
                this.gp_name = gp_name;
            }

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }

            public String getGa_name() {
                return ga_name;
            }

            public void setGa_name(String ga_name) {
                this.ga_name = ga_name;
            }
        }
    }
}
