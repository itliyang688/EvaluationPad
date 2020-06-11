package cn.fek12.evaluation.model.entity;

import java.io.Serializable;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.model.entity
 * @ClassName: StudentInfoEntity
 * @Description:
 * @CreateDate: 2020/6/9 14:46
 */
public class StudentInfoEntity implements Serializable {


    /**
     * class_name : 测试班级
     * class_uid : fdd17198-1c3c-4acd-886e-b5533dea2d04
     * per_birthday : 2020-06-10
     * per_email : 88999746634@163.com
     * per_id : 17E5A4E4-B77E-A588-363D-0423024174AB
     * per_level : 2
     * per_mobile : 13645678912
     * per_name : 加惠丽
     * per_pic : 1d344e5c-8ba8-4d77-b78a-1d56d04c1869
     * per_qq : 88993377
     * per_sex : 1
     * per_userid : 9014005
     * unit_id : 0004af7e-8bd3-11e8-b64e-000c297116c8
     * unit_name : 兴义市三江口镇斯林小学
     */

    private String class_name;
    private String class_uid;
    private String per_birthday;
    private String per_email;
    private String per_id;
    private String per_level;
    private String per_mobile;
    private String per_name;
    private String per_pic;
    private String per_qq;
    private String per_sex;
    private String per_userid;
    private String unit_id;
    private String unit_name;

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_uid() {
        return class_uid;
    }

    public void setClass_uid(String class_uid) {
        this.class_uid = class_uid;
    }

    public String getPer_birthday() {
        return per_birthday;
    }

    public void setPer_birthday(String per_birthday) {
        this.per_birthday = per_birthday;
    }

    public String getPer_email() {
        return per_email;
    }

    public void setPer_email(String per_email) {
        this.per_email = per_email;
    }

    public String getPer_id() {
        return per_id;
    }

    public void setPer_id(String per_id) {
        this.per_id = per_id;
    }

    public String getPer_level() {
        return per_level;
    }

    public void setPer_level(String per_level) {
        this.per_level = per_level;
    }

    public String getPer_mobile() {
        return per_mobile;
    }

    public void setPer_mobile(String per_mobile) {
        this.per_mobile = per_mobile;
    }

    public String getPer_name() {
        return per_name;
    }

    public void setPer_name(String per_name) {
        this.per_name = per_name;
    }

    public String getPer_pic() {
        return per_pic;
    }

    public void setPer_pic(String per_pic) {
        this.per_pic = per_pic;
    }

    public String getPer_qq() {
        return per_qq;
    }

    public void setPer_qq(String per_qq) {
        this.per_qq = per_qq;
    }

    public String getPer_sex() {
        return per_sex;
    }

    public void setPer_sex(String per_sex) {
        this.per_sex = per_sex;
    }

    public String getPer_userid() {
        return per_userid;
    }

    public void setPer_userid(String per_userid) {
        this.per_userid = per_userid;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }
}
