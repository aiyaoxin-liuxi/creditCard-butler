package com.zhl.card.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 信用卡还款记录表
 * @author 刘熙
 */
public class CardBlueRepayment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
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
	 * 币种
	 */
	private String currency;
	/**
	 * 还款金额
	 */
	private BigDecimal repaymentMoney;
	/**
	 * 还款日
	 */
	private String repaymentDay;
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
	
	
	// GET AND SET
	
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
     * 获取还款金额 repaymentMoney
     */
    public BigDecimal getRepaymentMoney() {
        return repaymentMoney;
    }
    /**
     * 设置还款金额 repaymentMoney
     */
    public void setRepaymentMoney(BigDecimal repaymentMoney) {
        this.repaymentMoney = repaymentMoney;
    }
    /**
     * 获取还款日 repaymentDay
     */
    public String getRepaymentDay() {
        return repaymentDay;
    }
    /**
     * 设置还款日 repaymentDay
     */
    public void setRepaymentDay(String repaymentDay) {
        this.repaymentDay = repaymentDay;
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
}
