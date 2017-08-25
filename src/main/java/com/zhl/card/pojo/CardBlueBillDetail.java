package com.zhl.card.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 信用卡交易记录表
 * @author 刘熙
 */
public class CardBlueBillDetail implements Serializable {
	
    /**
     * ID
     */
    private String id;
    /**
     * CARD_BLUE_BILL_ID
     */
    private String cardBlueBillId;
    /**
     * 银行卡号
     */
    private String cardNo;
    /**
     * 账单月份
     */
    private String statementMonth;
    /**
     * 交易日期
     */
    private String trdDate;
    /**
     * 记账日期
     */
    private String accDate;
    /**
     * 卡号后4位
     */
    private String card4No;
    /**
     * 交易金额
     */
    private BigDecimal payMoney;
    /**
     * 交易币种
     */
    private String currency;
    /**
     * 入账金额
     */
    private BigDecimal accMoney;
    /**
     * 交易备注
     */
    private String summary;
    /**
     * 交易地址
     */
    private String billAddr;
    /**
     * 交易名称
     */
    private String billName;
    /**
     * 对方账户名
     */
    private String otherAccountName;
    /**
     * 对方账号
     */
    private String otherAccountNo;
    /**
     * 对方开户行
     */
    private String otherOpeningBanek;
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
     * 类型
     */
    private String type;
    /**
     * 状态
     */
    private String state;
    /**
     * 逻辑删除标识
     */
    private String isDel;
    
    
    // get and set 
    
    /**
     * 获取ID id
     */
    public String getId() {
        return id;
    }
    /**
     * 设置ID id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 获取CARD_BLUE_BILL_ID cardBlueBillId
     */
    public String getCardBlueBillId() {
        return cardBlueBillId;
    }
    /**
     * 设置CARD_BLUE_BILL_ID cardBlueBillId
     */
    public void setCardBlueBillId(String cardBlueBillId) {
        this.cardBlueBillId = cardBlueBillId;
    }
    /**
     * 获取银行卡号 cardNo
     */
    public String getCardNo() {
        return cardNo;
    }
    /**
     * 设置银行卡号 cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    /**
     * 获取交易日期 trdDate
     */
    public String getTrdDate() {
        return trdDate;
    }
    /**
     * 设置交易日期 trdDate
     */
    public void setTrdDate(String trdDate) {
        this.trdDate = trdDate;
    }
    /**
     * 获取记账日期 accDate
     */
    public String getAccDate() {
        return accDate;
    }
    /**
     * 设置记账日期 accDate
     */
    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }
    /**
     * 获取卡号后4位 card4No
     */
    public String getCard4No() {
        return card4No;
    }
    /**
     * 设置卡号后4位 card4No
     */
    public void setCard4No(String card4No) {
        this.card4No = card4No;
    }
    /**
     * 获取交易金额 payMoney
     */
    public BigDecimal getPayMoney() {
        return payMoney;
    }
    /**
     * 设置交易金额 payMoney
     */
    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }
    /**
     * 获取交易币种 currency
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * 设置交易币种 currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    /**
     * 获取入账金额 accMoney
     */
    public BigDecimal getAccMoney() {
        return accMoney;
    }
    /**
     * 设置入账金额 accMoney
     */
    public void setAccMoney(BigDecimal accMoney) {
        this.accMoney = accMoney;
    }
    /**
     * 获取交易备注 summary
     */
    public String getSummary() {
        return summary;
    }
    /**
     * 设置交易备注 summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
    /**
     * 获取交易地址 billAddr
     */
    public String getBillAddr() {
        return billAddr;
    }
    /**
     * 设置交易地址 billAddr
     */
    public void setBillAddr(String billAddr) {
        this.billAddr = billAddr;
    }
    /**
     * 获取交易名称 billName
     */
    public String getBillName() {
        return billName;
    }
    /**
     * 设置交易名称 billName
     */
    public void setBillName(String billName) {
        this.billName = billName;
    }
    /**
     * 获取对方账户名 otherAccountName
     */
    public String getOtherAccountName() {
        return otherAccountName;
    }
    /**
     * 设置对方账户名 otherAccountName
     */
    public void setOtherAccountName(String otherAccountName) {
        this.otherAccountName = otherAccountName;
    }
    /**
     * 获取对方账号 otherAccountNo
     */
    public String getOtherAccountNo() {
        return otherAccountNo;
    }
    /**
     * 设置对方账号 otherAccountNo
     */
    public void setOtherAccountNo(String otherAccountNo) {
        this.otherAccountNo = otherAccountNo;
    }
    /**
     * 获取对方开户行 otherOpeningBanek
     */
    public String getOtherOpeningBanek() {
        return otherOpeningBanek;
    }
    /**
     * 设置对方开户行 otherOpeningBanek
     */
    public void setOtherOpeningBanek(String otherOpeningBanek) {
        this.otherOpeningBanek = otherOpeningBanek;
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
     * 获取逻辑删除标识 isDel
     */
    public String getIsDel() {
        return isDel;
    }
    /**
     * 设置逻辑删除标识 isDel
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    /**
     * 获取账单月份 statementMonth
     */
    public String getStatementMonth() {
        return statementMonth;
    }
    /**
     * 设置账单月份 statementMonth
     */
    public void setStatementMonth(String statementMonth) {
        this.statementMonth = statementMonth;
    }
    
    
    
}
