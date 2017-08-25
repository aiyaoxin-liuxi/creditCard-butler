package com.zhl.card.pojo;

import java.io.Serializable;
import java.util.Date;

public class SmsInfo implements Serializable  {
    
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 验证码
     */
    private String smsCode;
    /**
     * 操作（暂不使用）
     */
    private String operation;
    /**
     * 状态
     */
    private String state;
    /**
     * 类型
     */
    private String type;
    /**
     * 预留字段1
     */
    private String rev1;
    /**
     * 预留字段2
     */
    private String rev2;
    /**
     * 预留字段3
     */
    private String rev3;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 逻辑删除
     */
    private String isDel;
    
    
    
    // get and set
    
    /**
     * 获取手机号 mobile
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置手机号 mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取验证码 smsCode
     */
    public String getSmsCode() {
        return smsCode;
    }
    /**
     * 设置验证码 smsCode
     */
    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
    /**
     * 获取操作（暂不使用） operation
     */
    public String getOperation() {
        return operation;
    }
    /**
     * 设置操作（暂不使用） operation
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
    /**
     * 获取状态 state
     */
    public String getState() {
        return state;
    }
    /**
     * 设置状态 state
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * 获取类型 type
     */
    public String getType() {
        return type;
    }
    /**
     * 设置类型 type
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * 获取预留字段1 rev1
     */
    public String getRev1() {
        return rev1;
    }
    /**
     * 设置预留字段1 rev1
     */
    public void setRev1(String rev1) {
        this.rev1 = rev1;
    }
    /**
     * 获取预留字段2 rev2
     */
    public String getRev2() {
        return rev2;
    }
    /**
     * 设置预留字段2 rev2
     */
    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }
    /**
     * 获取预留字段3 rev3
     */
    public String getRev3() {
        return rev3;
    }
    /**
     * 设置预留字段3 rev3
     */
    public void setRev3(String rev3) {
        this.rev3 = rev3;
    }
    /**
     * 获取创建时间 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    /**
     * 设置创建时间 createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * 获取修改时间 updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    /**
     * 设置修改时间 updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * 获取修改人 updateUser
     */
    public String getUpdateUser() {
        return updateUser;
    }
    /**
     * 设置修改人 updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * 获取逻辑删除 isDel
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * 设置逻辑删除 isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

}
