package com.zhl.card.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CardInfo implements Serializable  {
    
    /**
     * id
     */
    private String id;
    /**
     * 用户登录id
     */
    private String logId;
    /**
     * 用户信息id
     */
    private String userId;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 币种
     */
    private String currency;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 卡类型 1、借记卡;2、信用卡;3、借贷卡
     */
    private String cardType;
    /**
     * 账单邮箱
     */
    private String cardEmail;
    /**
     * 账单邮箱验证是否通过
     */
    private String cardEmailAuthiruze;
    /**
     * 出账日（信用卡）
     */
    private Date statementDate;
    /**
     * 还款日（信用卡）
     */
    private Date repaymentDate;
    /**
     * 信用卡级别
     */
    private String cardLevel;
    /**
     * 绑定银行卡时间
     */
    private Date bindDate;
    /**
     * 持卡人姓名
     */
    private String cardHolderName;
    /**
     * 绑定银行卡预留手机号
     */
    private String bindMobile;
    /**
     * 绑定身份证号
     */
    private String bindIdcard;
    /**
     * 绑卡状态
     */
    private String bindState;
    /**
     * 信用卡有效期（年）
     */
    private String validityYear;
    /**
     * 信用卡有效期（月）
     */
    private String validityMonth;
    /**
     * 信用卡cvv
     */
    private String cardCvv;
    /**
     * 所属银行（接口的字段）
     */
    private String bankCode;
    /**
     * 信用额度
     */
    private BigDecimal creditLimit;
    /**
     * 可取现额度
     */
    private BigDecimal withdrawalLimit;
    /**
     * 所属银行logo
     */
    private String bankLog;
    /**
     * 所属银行总行
     */
    private String headBank;
    /**
     * 所属银行总行行联号
     */
    private String headBankNo;
    /**
     * 所属银行支行
     */
    private String branck;
    /**
     * 所属银行支行行联号
     */
    private String branckNo;
    /**
     * 是否通过验证
     */
    private String verified;
    /**
     * 是否授权
     */
    private String authiruze;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
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
     * 最近一期本期应还金额(只用于页面显示)
     */
    private String showCurPaymentMoney;
    
    /**
     * 最近一期账单日期(只用于页面显示)
     */
    private String showStatementMonth;
    
    /**
     * 最近一期账单周期(只用于页面显示)
     */
    private String showBillCyc;
    
    /**
     * 最近一期到期还款日(只用于页面显示)
     */
    private String showPaymentDueDate;
    
    /**
     * 最近一期信用额度(只用于页面显示)
     */
    private String showCreditLimit;
    
    /**
     * 最近一期取现额度(只用于页面显示)
     */
    private String showWithdrawalLimit;
    
    /**
     * 账单list
     */
    private List<CardBlueBill> cardBlueBillList;
    
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
     * 获取用户登录id logId
     */
    public String getLogId() {
        return logId;
    }
    /**
     * 设置用户登录id logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }
    /**
     * 获取用户信息id userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置用户信息id userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取卡号 cardNo
     */
    public String getCardNo() {
        return cardNo;
    }
    /**
     * 设置卡号 cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    /**
     * 获取银行名称 bankName
     */
    public String getBankName() {
        return bankName;
    }
    /**
     * 设置银行名称 bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    /**
     * 获取卡类型 cardType
     */
    public String getCardType() {
        return cardType;
    }
    /**
     * 设置卡类型 cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    /**
     * 获取账单邮箱 cardEmail
     */
    public String getCardEmail() {
        return cardEmail;
    }
    /**
     * 设置账单邮箱 cardEmail
     */
    public void setCardEmail(String cardEmail) {
        this.cardEmail = cardEmail;
    }
    /**
     * 获取账单邮箱验证是否通过 cardEmailAuthiruze
     */
    public String getCardEmailAuthiruze() {
        return cardEmailAuthiruze;
    }
    /**
     * 设置账单邮箱验证是否通过 cardEmailAuthiruze
     */
    public void setCardEmailAuthiruze(String cardEmailAuthiruze) {
        this.cardEmailAuthiruze = cardEmailAuthiruze;
    }
    /**
     * 获取出账日（信用卡） statementDate
     */
    public Date getStatementDate() {
        return statementDate;
    }
    /**
     * 设置出账日（信用卡） statementDate
     */
    public void setStatementDate(Date statementDate) {
        this.statementDate = statementDate;
    }
    /**
     * 获取还款日（信用卡） repaymentDate
     */
    public Date getRepaymentDate() {
        return repaymentDate;
    }
    /**
     * 设置还款日（信用卡） repaymentDate
     */
    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }
    /**
     * 获取信用卡级别 cardLevel
     */
    public String getCardLevel() {
        return cardLevel;
    }
    /**
     * 设置信用卡级别 cardLevel
     */
    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }
    /**
     * 获取绑定银行卡时间 bindDate
     */
    public Date getBindDate() {
        return bindDate;
    }
    /**
     * 设置绑定银行卡时间 bindDate
     */
    public void setBindDate(Date bindDate) {
        this.bindDate = bindDate;
    }
    /**
     * 获取持卡人姓名 cardHolderName
     */
    public String getCardHolderName() {
        return cardHolderName;
    }
    /**
     * 设置持卡人姓名 cardHolderName
     */
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    /**
     * 获取绑定银行卡预留手机号 bindMobile
     */
    public String getBindMobile() {
        return bindMobile;
    }
    /**
     * 设置绑定银行卡预留手机号 bindMobile
     */
    public void setBindMobile(String bindMobile) {
        this.bindMobile = bindMobile;
    }
    /**
     * 获取绑定身份证号 bindIdcard
     */
    public String getBindIdcard() {
        return bindIdcard;
    }
    /**
     * 设置绑定身份证号 bindIdcard
     */
    public void setBindIdcard(String bindIdcard) {
        this.bindIdcard = bindIdcard;
    }
    /**
     * 获取绑卡状态 bindState
     */
    public String getBindState() {
        return bindState;
    }
    /**
     * 设置绑卡状态 bindState
     */
    public void setBindState(String bindState) {
        this.bindState = bindState;
    }
    /**
     * 获取信用卡有效期（年） validityYear
     */
    public String getValidityYear() {
        return validityYear;
    }
    /**
     * 设置信用卡有效期（年） validityYear
     */
    public void setValidityYear(String validityYear) {
        this.validityYear = validityYear;
    }
    /**
     * 获取信用卡有效期（月） validityMonth
     */
    public String getValidityMonth() {
        return validityMonth;
    }
    /**
     * 设置信用卡有效期（月） validityMonth
     */
    public void setValidityMonth(String validityMonth) {
        this.validityMonth = validityMonth;
    }
    /**
     * 获取信用卡cvv cardCvv
     */
    public String getCardCvv() {
        return cardCvv;
    }
    /**
     * 设置信用卡cvv cardCvv
     */
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
    /**
     * 获取所属银行logo bankLog
     */
    public String getBankLog() {
        return bankLog;
    }
    /**
     * 设置所属银行logo bankLog
     */
    public void setBankLog(String bankLog) {
        this.bankLog = bankLog;
    }
    /**
     * 获取所属银行总行 headBank
     */
    public String getHeadBank() {
        return headBank;
    }
    /**
     * 设置所属银行总行 headBank
     */
    public void setHeadBank(String headBank) {
        this.headBank = headBank;
    }
    /**
     * 获取所属银行总行行联号 headBankNo
     */
    public String getHeadBankNo() {
        return headBankNo;
    }
    /**
     * 设置所属银行总行行联号 headBankNo
     */
    public void setHeadBankNo(String headBankNo) {
        this.headBankNo = headBankNo;
    }
    /**
     * 获取所属银行支行 branck
     */
    public String getBranck() {
        return branck;
    }
    /**
     * 设置所属银行支行 branck
     */
    public void setBranck(String branck) {
        this.branck = branck;
    }
    /**
     * 获取所属银行支行行联号 branckNo
     */
    public String getBranckNo() {
        return branckNo;
    }
    /**
     * 设置所属银行支行行联号 branckNo
     */
    public void setBranckNo(String branckNo) {
        this.branckNo = branckNo;
    }
    /**
     * 获取是否通过验证 verified
     */
    public String getVerified() {
        return verified;
    }
    /**
     * 设置是否通过验证 verified
     */
    public void setVerified(String verified) {
        this.verified = verified;
    }
    /**
     * 获取是否授权 authiruze
     */
    public String getAuthiruze() {
        return authiruze;
    }
    /**
     * 设置是否授权 authiruze
     */
    public void setAuthiruze(String authiruze) {
        this.authiruze = authiruze;
    }
    /**
     * 获取省 province
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置省 province
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * 获取市 city
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置市 city
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 获取区 area
     */
    public String getArea() {
        return area;
    }
    /**
     * 设置区 area
     */
    public void setArea(String area) {
        this.area = area;
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
     * 获取所属银行（接口的字段） bankCode
     */
    public String getBankCode() {
        return bankCode;
    }
    /**
     * 设置所属银行（接口的字段） bankCode
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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
     * 获取本期应还金额(只用于页面显示) showCurPaymentMoney
     */
    public String getShowCurPaymentMoney() {
        return showCurPaymentMoney;
    }
    /**
     * 设置本期应还金额(只用于页面显示) showCurPaymentMoney
     */
    public void setShowCurPaymentMoney(String showCurPaymentMoney) {
        this.showCurPaymentMoney = showCurPaymentMoney;
    }
    /**
     * 获取账单日期(只用于页面显示) showStatementMonth
     */
    public String getShowStatementMonth() {
        return showStatementMonth;
    }
    /**
     * 设置账单日期(只用于页面显示) showStatementMonth
     */
    public void setShowStatementMonth(String showStatementMonth) {
        this.showStatementMonth = showStatementMonth;
    }
    
    /**
     * 获取账单周期(只用于页面显示) showBillCyc
     */
    public String getShowBillCyc() {
        return showBillCyc;
    }
    /**
     * 设置账单周期(只用于页面显示) showBillCyc
     */
    public void setShowBillCyc(String showBillCyc) {
        this.showBillCyc = showBillCyc;
    }
    
    /**
     * 获取最近一期到期还款日(只用于页面显示) showPaymentDueDate
     */
    public String getShowPaymentDueDate() {
        return showPaymentDueDate;
    }
    /**
     * 设置最近一期到期还款日(只用于页面显示) showPaymentDueDate
     */
    public void setShowPaymentDueDate(String showPaymentDueDate) {
        this.showPaymentDueDate = showPaymentDueDate;
    }
    /**
     * 获取最近一期信用额度(只用于页面显示) showCreditLimit
     */
    public String getShowCreditLimit() {
        return showCreditLimit;
    }
    /**
     * 设置最近一期信用额度(只用于页面显示) showCreditLimit
     */
    public void setShowCreditLimit(String showCreditLimit) {
        this.showCreditLimit = showCreditLimit;
    }
    /**
     * 获取最近一期取现额度(只用于页面显示) showWithdrawalLimit
     */
    public String getShowWithdrawalLimit() {
        return showWithdrawalLimit;
    }
    /**
     * 设置最近一期取现额度(只用于页面显示) showWithdrawalLimit
     */
    public void setShowWithdrawalLimit(String showWithdrawalLimit) {
        this.showWithdrawalLimit = showWithdrawalLimit;
    }
    /**
     * 获取账单list cardBlueBillList
     */
    public List<CardBlueBill> getCardBlueBillList() {
        return cardBlueBillList;
    }
    /**
     * 设置账单list cardBlueBillList
     */
    public void setCardBlueBillList(List<CardBlueBill> cardBlueBillList) {
        this.cardBlueBillList = cardBlueBillList;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CardInfo [id=" + id + ", logId=" + logId + ", userId=" + userId + ", cardNo=" + cardNo + ", currency="
                + currency + ", bankName=" + bankName + ", cardType=" + cardType + ", cardEmail=" + cardEmail
                + ", cardEmailAuthiruze=" + cardEmailAuthiruze + ", statementDate=" + statementDate
                + ", repaymentDate=" + repaymentDate + ", cardLevel=" + cardLevel + ", bindDate=" + bindDate
                + ", cardHolderName=" + cardHolderName + ", bindMobile=" + bindMobile + ", bindIdcard=" + bindIdcard
                + ", bindState=" + bindState + ", validityYear=" + validityYear + ", validityMonth=" + validityMonth
                + ", cardCvv=" + cardCvv + ", bankCode=" + bankCode + ", creditLimit=" + creditLimit
                + ", withdrawalLimit=" + withdrawalLimit + ", bankLog=" + bankLog + ", headBank=" + headBank
                + ", headBankNo=" + headBankNo + ", branck=" + branck + ", branckNo=" + branckNo + ", verified="
                + verified + ", authiruze=" + authiruze + ", province=" + province + ", city=" + city + ", area="
                + area + ", createDate=" + createDate + ", updateDate=" + updateDate + ", updateUser=" + updateUser
                + ", type=" + type + ", state=" + state + ", isDel=" + isDel + ", showCurPaymentMoney="
                + showCurPaymentMoney + ", showStatementMonth=" + showStatementMonth + ", showBillCyc=" + showBillCyc
                + ", showPaymentDueDate=" + showPaymentDueDate + ", showCreditLimit=" + showCreditLimit
                + ", showWithdrawalLimit=" + showWithdrawalLimit + "]";
    }
    

}
