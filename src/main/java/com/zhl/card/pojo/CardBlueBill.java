package com.zhl.card.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 信用卡账单表
 * @author 刘熙
 */
public class CardBlueBill implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 登录ID
	 */
	private String logId;
	
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 对应卡的id
	 */
	private String cardId;
	/**
	 * 银行卡号
	 */
	private String cardNo;
	/**
	 * 币种
	 */
	private String currency;
	/**
	 * 账单开始日期
	 */
	private String statementStartDate;
	/**
	 * 账单截止日期
	 */
	private String statementEndDate;
	/**
	 * 到期还款日
	 */
	private String paymentDueDate;
	/**
	 * 本期应还金额
	 */
	private BigDecimal curPaymentMoney;
	/**
	 * 账单月份
	 */
	private String statementMonth;
	/**
	 * 最低未还金额
	 */
	private BigDecimal miniPaymentMoney;
	/**
	 * 信用额度
	 */
	private BigDecimal creditLimit;
	/**
	 * 可取现额度
	 */
	private BigDecimal withdrawalLimit;
	/**
	 * 可用积分
	 */
	private String ablePoints;
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
	
	/**
	 * 信用卡交易记录表
	 */
	private List<CardBlueBillDetail> cardBlueBillDetailList;
	/**
	 * 账单分期表
	 */
	private List<BillInstallments> billInstallmentsList;
	/**
	 * 账单变动表
	 */
	private List<AccountChangeInfo> accountChangeInfoList;
	
	
	
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
     * 获取币种 currency
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * 设置币种 currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    /**
     * 获取账单开始日期 statementStartDate
     */
    public String getStatementStartDate() {
        return statementStartDate;
    }
    /**
     * 设置账单开始日期 statementStartDate
     */
    public void setStatementStartDate(String statementStartDate) {
        this.statementStartDate = statementStartDate;
    }
    /**
     * 获取账单截止日期 statementEndDate
     */
    public String getStatementEndDate() {
        return statementEndDate;
    }
    /**
     * 设置账单截止日期 statementEndDate
     */
    public void setStatementEndDate(String statementEndDate) {
        this.statementEndDate = statementEndDate;
    }
    /**
     * 获取到期还款日 paymentDueDate
     */
    public String getPaymentDueDate() {
        return paymentDueDate;
    }
    /**
     * 设置到期还款日 paymentDueDate
     */
    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }
    /**
     * 获取本期应还金额 curPaymentMoney
     */
    public BigDecimal getCurPaymentMoney() {
        return curPaymentMoney;
    }
    /**
     * 设置本期应还金额 curPaymentMoney
     */
    public void setCurPaymentMoney(BigDecimal curPaymentMoney) {
        this.curPaymentMoney = curPaymentMoney;
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
    /**
     * 获取最低未还金额 miniPaymentMoney
     */
    public BigDecimal getMiniPaymentMoney() {
        return miniPaymentMoney;
    }
    /**
     * 设置最低未还金额 miniPaymentMoney
     */
    public void setMiniPaymentMoney(BigDecimal miniPaymentMoney) {
        this.miniPaymentMoney = miniPaymentMoney;
    }
    /**
     * 获取信用额度 creditLimit
     */
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }
    /**
     * 设置信用额度 creditLimit
     */
    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
    /**
     * 获取可取现额度 withdrawalLimit
     */
    public BigDecimal getWithdrawalLimit() {
        return withdrawalLimit;
    }
    /**
     * 设置可取现额度 withdrawalLimit
     */
    public void setWithdrawalLimit(BigDecimal withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }
    /**
     * 获取可用积分 ablePoints
     */
    public String getAblePoints() {
        return ablePoints;
    }
    /**
     * 设置可用积分 ablePoints
     */
    public void setAblePoints(String ablePoints) {
        this.ablePoints = ablePoints;
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
     * 获取登录ID logId
     */
    public String getLogId() {
        return logId;
    }
    /**
     * 设置登录ID logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }
    /**
     * 获取用户ID userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置用户ID userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取对应卡的id cardId
     */
    public String getCardId() {
        return cardId;
    }
    /**
     * 设置对应卡的id cardId
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    /**
     * 获取信用卡交易记录表 cardBlueBillDetailList
     */
    public List<CardBlueBillDetail> getCardBlueBillDetailList() {
        return cardBlueBillDetailList;
    }
    /**
     * 设置信用卡交易记录表 cardBlueBillDetailList
     */
    public void setCardBlueBillDetailList(List<CardBlueBillDetail> cardBlueBillDetailList) {
        this.cardBlueBillDetailList = cardBlueBillDetailList;
    }
    /**
     * 获取账单分期表 billInstallmentsList
     */
    public List<BillInstallments> getBillInstallmentsList() {
        return billInstallmentsList;
    }
    /**
     * 设置账单分期表 billInstallmentsList
     */
    public void setBillInstallmentsList(List<BillInstallments> billInstallmentsList) {
        this.billInstallmentsList = billInstallmentsList;
    }
    /**
     * 获取账单变动表 accountChangeInfoList
     */
    public List<AccountChangeInfo> getAccountChangeInfoList() {
        return accountChangeInfoList;
    }
    /**
     * 设置账单变动表 accountChangeInfoList
     */
    public void setAccountChangeInfoList(List<AccountChangeInfo> accountChangeInfoList) {
        this.accountChangeInfoList = accountChangeInfoList;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CardBlueBill [id=" + id + ", logId=" + logId + ", userId=" + userId + ", cardId=" + cardId
                + ", cardNo=" + cardNo + ", currency=" + currency + ", statementStartDate=" + statementStartDate
                + ", statementEndDate=" + statementEndDate + ", paymentDueDate=" + paymentDueDate
                + ", curPaymentMoney=" + curPaymentMoney + ", statementMonth=" + statementMonth + ", miniPaymentMoney="
                + miniPaymentMoney + ", creditLimit=" + creditLimit + ", withdrawalLimit=" + withdrawalLimit
                + ", ablePoints=" + ablePoints + ", createDate=" + createDate + ", updateDate=" + updateDate
                + ", updateUser=" + updateUser + ", type=" + type + ", state=" + state + ", isDel=" + isDel + "]";
    }
    
}
