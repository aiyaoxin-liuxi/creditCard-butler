package com.zhl.card.pojo;

import java.util.Date;

public class EmailInfo {
    
    /**
     * id
     */
    private String id;
    /**
     * 登录id
     */
    private String logId;
    /**
     * 用户信息ID
     */
    private String userId;
    /**
     * 关联银行卡id
     */
    private String cardId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱密码
     */
    private String emailPassword;
    /**
     * 接口返回token
     */
    private String token;
    /**
     * 异步时返回使用id
     */
    private String ybId;
    /**
     * 上游受理状态
     */
    private String acceptState;
    /**
     * 状态
     */
    private String state;
    /**
     * 类型
     */
    private String type;
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
     * 是否删除
     */
    private String isDel;
    
    // get and set
    
    /**
     * 获取id id
     */
    public String getId() {
        return id;
    }
    /**
     * 设置id id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 获取登录id logId
     */
    public String getLogId() {
        return logId;
    }
    /**
     * 设置登录id logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }
    /**
     * 获取用户信息ID userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置用户信息ID userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取关联银行卡id cardId
     */
    public String getCardId() {
        return cardId;
    }
    /**
     * 设置关联银行卡id cardId
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    /**
     * 获取邮箱 email
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置邮箱 email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 获取邮箱密码 emailPassword
     */
    public String getEmailPassword() {
        return emailPassword;
    }
    /**
     * 设置邮箱密码 emailPassword
     */
    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
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
     * 获取是否删除 isDel
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * 设置是否删除 isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    /**
     * 获取接口返回token 接口返回token
     */
    public String getToken() {
        return token;
    }
    /**
     * 设置接口返回token 接口返回token
     */
    public void setToken(String token) {
        this.token = token;
    }
    
    /**
     * 获取异步时返回使用id ybId
     */
    public String getYbId() {
        return ybId;
    }
    /**
     * 设置异步时返回使用id ybId
     */
    public void setYbId(String ybId) {
        this.ybId = ybId;
    }
    /**
     * 获取上游受理状态 acceptState
     */
    public String getAcceptState() {
        return acceptState;
    }
    /**
     * 设置上游受理状态 acceptState
     */
    public void setAcceptState(String acceptState) {
        this.acceptState = acceptState;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EmailInfo [id=" + id + ", logId=" + logId + ", userId=" + userId + ", cardId=" + cardId + ", email="
                + email + ", emailPassword=" + emailPassword + ", token=" + token + ", ybId=" + ybId + ", acceptState="
                + acceptState + ", state=" + state + ", type=" + type + ", createDate=" + createDate + ", updateDate="
                + updateDate + ", updateUser=" + updateUser + ", isDel=" + isDel + "]";
    }
}
