package com.zhl.card.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 信用卡账单动表
 * @author 刘熙
 */
public class AccountChangeInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	private String id;
	/**
	 * 登录ID
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
     * 本期账单金额
     */
    private BigDecimal curStatementMoney;
    /**
     * 本期消费金额
     */
    private BigDecimal curDebitsMoney;
    /**
     * 上期已还金额
     */
    private BigDecimal prePoaymentMoney;
    /**
     * 本期调整金额
     */
    private BigDecimal curAdjustmentMoney;
    /**
     * 循环利息
     */
    private BigDecimal cycleInterest;
    /**
     * 上期账单金额
     */
    private BigDecimal preStatementMoney;
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
     * 获取登录ID cardBlueBillId
     */
    public String getCardBlueBillId() {
        return cardBlueBillId;
    }
    /**
     * 设置登录ID cardBlueBillId
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
     * 获取本期账单金额 curStatementMoney
     */
    public BigDecimal getCurStatementMoney() {
        return curStatementMoney;
    }
    /**
     * 设置本期账单金额 curStatementMoney
     */
    public void setCurStatementMoney(BigDecimal curStatementMoney) {
        this.curStatementMoney = curStatementMoney;
    }
    /**
     * 获取本期消费金额 curDebitsMoney
     */
    public BigDecimal getCurDebitsMoney() {
        return curDebitsMoney;
    }
    /**
     * 设置本期消费金额 curDebitsMoney
     */
    public void setCurDebitsMoney(BigDecimal curDebitsMoney) {
        this.curDebitsMoney = curDebitsMoney;
    }
    /**
     * 获取上期已还金额 prePoaymentMoney
     */
    public BigDecimal getPrePoaymentMoney() {
        return prePoaymentMoney;
    }
    /**
     * 设置上期已还金额 prePoaymentMoney
     */
    public void setPrePoaymentMoney(BigDecimal prePoaymentMoney) {
        this.prePoaymentMoney = prePoaymentMoney;
    }
    /**
     * 获取本期调整金额 curAdjustmentMoney
     */
    public BigDecimal getCurAdjustmentMoney() {
        return curAdjustmentMoney;
    }
    /**
     * 设置本期调整金额 curAdjustmentMoney
     */
    public void setCurAdjustmentMoney(BigDecimal curAdjustmentMoney) {
        this.curAdjustmentMoney = curAdjustmentMoney;
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
     * 获取循环利息 cycleInterest
     */
    public BigDecimal getCycleInterest() {
        return cycleInterest;
    }
    /**
     * 设置循环利息 cycleInterest
     */
    public void setCycleInterest(BigDecimal cycleInterest) {
        this.cycleInterest = cycleInterest;
    }
    /**
     * 获取上期账单金额 preStatementMoney
     */
    public BigDecimal getPreStatementMoney() {
        return preStatementMoney;
    }
    /**
     * 设置上期账单金额 preStatementMoney
     */
    public void setPreStatementMoney(BigDecimal preStatementMoney) {
        this.preStatementMoney = preStatementMoney;
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
