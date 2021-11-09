package com.zwedu.rac.sdk.rpo.user;

import com.zwedu.rac.sdk.rpo.base.BaseComplexRpo;

/**
 * 用户基本传输对象
 *
 * @author qingchuan
 * @date 2020/12/10
 */
public class UserComplexRpo extends BaseComplexRpo {

    /**
     * 身份证号
     */
    private String idCardNum;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 分级管控ID
     */
    private Long decentralizedControlId;

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDecentralizedControlId() {
        return decentralizedControlId;
    }

    public void setDecentralizedControlId(Long decentralizedControlId) {
        this.decentralizedControlId = decentralizedControlId;
    }
}
