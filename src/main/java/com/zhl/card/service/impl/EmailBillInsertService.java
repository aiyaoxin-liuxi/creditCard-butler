package com.zhl.card.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.json.JsonUtil;
import org.pub.util.uuid.KeySn;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.IAccountChangeInfoDao;
import com.zhl.card.dao.IBillInstallmentsDao;
import com.zhl.card.dao.ICardBlueBillDao;
import com.zhl.card.dao.ICardBlueBillDetailDao;
import com.zhl.card.dao.ICardInfoDao;
import com.zhl.card.pojo.AccountChangeInfo;
import com.zhl.card.pojo.BillInstallments;
import com.zhl.card.pojo.CardBlueBill;
import com.zhl.card.pojo.CardBlueBillDetail;
import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.enums.CardType;
import com.zhl.card.pojo.enums.billInstallments.BillInstallmentsState;
import com.zhl.card.pojo.enums.billInstallments.BillInstallmentsType;
import com.zhl.card.pojo.enums.cardInfo.BindState;
import com.zhl.card.pojo.enums.cardInfo.CardInfoState;
import com.zhl.card.pojo.enums.cardInfo.CardInfoType;
import com.zhl.card.pojo.enums.cardbluebill.CardBlueBillState;
import com.zhl.card.pojo.enums.cardbluebill.CardBlueBillType;
import com.zhl.card.pojo.enums.cardbluebilldetail.CardBlueBillDetailState;
import com.zhl.card.pojo.enums.cardbluebilldetail.CardBlueBillDetailType;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.email.EmailBankName4BankCode;
import com.zhl.card.service.IEmailBillInsertService;
import com.zhl.card.util.BigDecimalUtil;

@Service("emailBillInsertService")
public class EmailBillInsertService implements IEmailBillInsertService{
    
    
    @Resource
    private IBillInstallmentsDao billInstallmentsDao;
    @Resource
    private ICardBlueBillDao cardBlueBillDao;
    @Resource
    private ICardBlueBillDetailDao cardBlueBillDetailDao;
    @Resource
    private ICardInfoDao cardInfoDao;
    @Resource
    private IAccountChangeInfoDao accountChangeInfoDao;

    @Override
    public Map<String, Object> addEmailAndInsertBill(String logId, String userId, String reStr) {
        
        Map<String, Object> retMap = new HashMap<String, Object>();
        
        // 获取接口返回参数
//        reStr = "{\"code\":\"0000\",\"msg\":\"成功\",\"data\":{\"cards\":[{\"cardNo\":\"3523\",\"realName\":\"李俊锋\",\"currency\":\"CNY\",\"creditLimit\":\"1.00\",\"withdrawalLimit\":\"\",\"bankCode\":\"ICBC\",\"bills\":[{\"statementMonth\":\"2016-01\",\"statementStartDate\":\"\",\"statementEndDate\":\"\",\"paymentDueDate\":\"2016-2-25\",\"generalLedgerInfo\":{\"curPaymentBal\":\"7034.00\",\"minPaymentBal\":\"703.40\",\"creditLimit\":\"1.00\",\"withdrawalLimit\":\"\"},\"accountChangeInfos\":[],\"details\":[],\"installments\":[]},{\"statementMonth\":\"2016-02\",\"statementStartDate\":\"\",\"statementEndDate\":\"\",\"paymentDueDate\":\"2016-3-25\",\"generalLedgerInfo\":{\"curPaymentBal\":\"7034.00\",\"minPaymentBal\":\"703.40\",\"creditLimit\":\"1.00\",\"withdrawalLimit\":\"\"},\"accountChangeInfos\":[],\"details\":[],\"installments\":[]},{\"statementMonth\":\"2016-03\",\"statementStartDate\":\"\",\"statementEndDate\":\"\",\"paymentDueDate\":\"2016-4-25\",\"generalLedgerInfo\":{\"curPaymentBal\":\"7034.00\",\"minPaymentBal\":\"703.40\",\"creditLimit\":\"1.00\",\"withdrawalLimit\":\"\"},\"accountChangeInfos\":[],\"details\":[],\"installments\":[]}]}]}}";

        if(null == reStr && "".equals(reStr)){
            retMap.put("returnCode", ReturnCode.SUCCESS_000000.getCode());
            retMap.put("reason", ReturnCode.SUCCESS_000000.getName());
            return retMap;
        }
        // 开始解析
        Map<String, Object> allMap = JsonUtil.getJsonToMap(reStr);
        if("0000".equals(allMap.get("code"))){
            
            CardInfo cardInfo = null;
            // 获取账单表list
            Map<String, Object> dataMap = JsonUtil.getJsonToMap(allMap.get("data").toString());
            List<Object> cardList = (List<Object>) dataMap.get("cards");
            Map<String, Object> cardInfoMap = null;
            for(int i = 0; i < cardList.size(); i++){
                
                String cardId = KeySn.getKey();
                
                cardInfo = new CardInfo();
                cardInfoMap = new HashMap<String, Object>();
                cardInfoMap = (Map<String, Object>) cardList.get(i);
                
                int reInt = 0;
                String cardNo = (String)cardInfoMap.get("cardNo");
                Map<String, Object> paramMap = new HashMap<String, Object>();
                if(null != cardNo && !"".equals(cardNo)){
                    paramMap.put("logId", logId);
                    paramMap.put("userId", userId);
                    cardNo = getCardNo4(cardNo);
                    paramMap.put("cardNo", cardNo);
                    cardInfo = cardInfoDao.queryCardInfoByLikeCardNo(paramMap);
                    if(null == cardInfo){
                        
                        cardInfo = new CardInfo();
                        cardInfo.setId(cardId);
                        cardInfo.setLogId(logId);
                        cardInfo.setUserId(userId);
                        cardInfo.setBankName(EmailBankName4BankCode.getText((String)cardInfoMap.get("bankCode")));
                        cardInfo.setCardNo(cardNo);
                        cardInfo.setCurrency((String)cardInfoMap.get("currency"));
                        cardInfo.setCardHolderName((String)cardInfoMap.get("realName"));
                        cardInfo.setBankCode((String)cardInfoMap.get("bankCode"));
                        cardInfo.setCreditLimit(BigDecimalUtil.EmptyToNullStr((String)cardInfoMap.get("creditLimit")));
                        cardInfo.setWithdrawalLimit(BigDecimalUtil.EmptyToNullStr((String)cardInfoMap.get("withdrawalLimit")));
                        cardInfo.setCreateDate(new Date());
                        cardInfo.setCardType(CardType.CODE02.getCode());
                        cardInfo.setState(CardInfoState.CODE00.getCode());
                        cardInfo.setType(CardInfoType.CODE00.getCode());
                        cardInfo.setBindState(BindState.CODE03.getCode());
                        cardInfo.setIsDel(IsDel.CODE00.getCode());
                        
                        reInt = cardInfoDao.insertCardInfo(cardInfo);
                    } else {
                        cardNo = cardInfo.getCardNo();
                    }
                }
                
                CardBlueBill cardBlueBill = null;
                List<AccountChangeInfo> accountChangeInfoList = null;
                List<CardBlueBillDetail> cardBlueBillDetailList = null;
                List<BillInstallments> billInstallmentsList = null;
                
                Map<String, Object> billsMap = null;
                List<Object> billsList = (List<Object>) cardInfoMap.get("bills");
                for(int j = 0; j < billsList.size(); j++){
                    
                    String billId = KeySn.getKey();
                    // 账单表
                    billsMap = new HashMap<String, Object>();
                    
                    cardBlueBill = new CardBlueBill();
                    accountChangeInfoList = new ArrayList<AccountChangeInfo>();
                    cardBlueBillDetailList = new ArrayList<CardBlueBillDetail>();
                    billInstallmentsList = new ArrayList<BillInstallments>();
                    
                    
                    billsMap = (Map<String, Object>) billsList.get(j);
                    
                    String statementMonth = (String)billsMap.get("statementMonth");// 账单月份
                    
                    cardBlueBill.setId(billId);
                    cardBlueBill.setLogId(logId);
                    cardBlueBill.setUserId(userId);
                    cardBlueBill.setCardId(cardId);
                    cardBlueBill.setCardNo(cardNo);
                    cardBlueBill.setCurrency((String)cardInfoMap.get("currency"));
                    cardBlueBill.setStatementMonth(statementMonth);
                    cardBlueBill.setStatementStartDate((String)billsMap.get("statementStartDate"));
                    cardBlueBill.setStatementEndDate((String)billsMap.get("statementEndDate"));
                    cardBlueBill.setPaymentDueDate((String)billsMap.get("paymentDueDate"));
                    
                    if(null != billsMap.get("generalLedgerInfo") && !"".equals(billsMap.get("generalLedgerInfo"))){
                        Map<String, Object> generalLedgerInfoMap = JsonUtil.getJsonToMap(billsMap.get("generalLedgerInfo").toString());
                        cardBlueBill.setCurPaymentMoney(BigDecimalUtil.EmptyToNullStr((String)generalLedgerInfoMap.get("curPaymentBal")));
                        cardBlueBill.setMiniPaymentMoney(BigDecimalUtil.EmptyToNullStr((String)generalLedgerInfoMap.get("miniPaymentBal")));
                        cardBlueBill.setCreditLimit(BigDecimalUtil.EmptyToNullStr((String)generalLedgerInfoMap.get("creditLimit")));
                        cardBlueBill.setWithdrawalLimit(BigDecimalUtil.EmptyToNullStr((String)generalLedgerInfoMap.get("withdrawalLimit")));
                    }
                    cardBlueBill.setCreateDate(new Date());
                    cardBlueBill.setType(CardBlueBillState.CODE00.getCode());
                    cardBlueBill.setState(CardBlueBillType.CODE00.getCode());
                    cardBlueBill.setIsDel(IsDel.CODE00.getCode());
                    // cardNo = card4No
                    
                    // 变动表
                    accountChangeInfoList = pojByAccountChangeInfo(billId, statementMonth, billsMap);
                   
                    // 交易记录
                    cardBlueBillDetailList = pojoByCardBlueBillDetail(billId, statementMonth, billsMap);
                    
                    // 分期信息封装
                    billInstallmentsList = pojoBybillInstallments(billId, statementMonth, billsMap);
                    
                    // 参数入库
                    reInt = 0;
                    if(null != cardBlueBill){
                        paramMap = new HashMap<String, Object>();
                        paramMap.put("logId", logId);
                        paramMap.put("userId", userId);
                        paramMap.put("cardNo", cardBlueBill.getCardNo());
                        paramMap.put("statementMonth", cardBlueBill.getStatementMonth());
                        List<CardBlueBill> cardBlueBillList = cardBlueBillDao.queryCardBlueBillCondition2List(paramMap);
                        if(null == cardBlueBillList || cardBlueBillList.size() == 0){
                            reInt = cardBlueBillDao.insertCardBlueBill(cardBlueBill);
                            
                            paramMap = new HashMap<String, Object>();
                            paramMap.put("cardNo", cardBlueBill.getCardNo());
                            paramMap.put("statementMonth", cardBlueBill.getStatementMonth());
                            
                            if(accountChangeInfoList.size() > 0){
                                reInt = accountChangeInfoDao.insertAccountChangeInfoList(accountChangeInfoList);
                            }
                            
                            if(billInstallmentsList.size() > 0){
                                reInt = billInstallmentsDao.insertBillInstallmentsList(billInstallmentsList);
                            }
                            
                            if(cardBlueBillDetailList.size() > 0){
                                reInt = cardBlueBillDetailDao.insertCardBlueBillDetailList(cardBlueBillDetailList);
                            }
                        }
                    }
                }
            }
            retMap.put("returnCode", ReturnCode.SUCCESS_000000.getCode());
            retMap.put("reason", ReturnCode.SUCCESS_000000.getName());
        } else {
            retMap.put("returnCode", ReturnCode.OBJECT_NOT_FIND_111111.getCode());
            retMap.put("reason", ReturnCode.OBJECT_NOT_FIND_111111.getName());
        }
        
        return retMap;
    }
    
    
    
    /**
     * 变动表实体封装
     * @param billId
     * @param statementMonth
     * @param billsMap
     * @return
     */
    private List<AccountChangeInfo> pojByAccountChangeInfo(String billId, String statementMonth, Map<String, Object> billsMap){
        
        List<AccountChangeInfo> accountChangeInfoList = new ArrayList<AccountChangeInfo>();
        
        Map<String, Object> accountChangeInfoMap = null;
        AccountChangeInfo accountChangeInfo = null;
        List<Object> accountChangeInfosList = (List<Object>) billsMap.get("accountChangeInfos");
        for (int m = 0; m < accountChangeInfosList.size(); m++) {
            // 变动表
            accountChangeInfoMap = new HashMap<String, Object>();
            accountChangeInfoMap = (Map<String, Object>) accountChangeInfosList.get(m);
            accountChangeInfo = new AccountChangeInfo();
            
            accountChangeInfo.setId(KeySn.getKey());
            accountChangeInfo.setCardBlueBillId(billId);
            accountChangeInfo.setStatementMonth(statementMonth);
            
            String cardNo = getCardNo4((String)accountChangeInfoMap.get("cardNo"));
            accountChangeInfo.setCardNo(cardNo);
            accountChangeInfo.setCurStatementMoney(BigDecimalUtil.EmptyToNullStr((String)accountChangeInfoMap.get("curStatementBal")));
            accountChangeInfo.setCurDebitsMoney(BigDecimalUtil.EmptyToNullStr((String)accountChangeInfoMap.get("curDebitsBal")));
            accountChangeInfo.setPreStatementMoney(BigDecimalUtil.EmptyToNullStr((String)accountChangeInfoMap.get("preStatementBal")));
            accountChangeInfo.setPrePoaymentMoney(BigDecimalUtil.EmptyToNullStr((String)accountChangeInfoMap.get("prePoaymentBal")));
            accountChangeInfo.setCurAdjustmentMoney(BigDecimalUtil.EmptyToNullStr((String)accountChangeInfoMap.get("curAdjustmentBal")));
            accountChangeInfo.setCycleInterest(BigDecimalUtil.EmptyToNullStr((String)accountChangeInfoMap.get("cycleInterest")));
            accountChangeInfo.setCreateDate(new Date());
            accountChangeInfo.setType(CardBlueBillState.CODE00.getCode());
            accountChangeInfo.setState(CardBlueBillType.CODE00.getCode());
            accountChangeInfo.setIsDel(IsDel.CODE00.getCode());
            accountChangeInfoList.add(accountChangeInfo);
        }
        
        return accountChangeInfoList;
    }
    
    /**
     * 交易记录实体封装
     * @param billId
     * @param statementMonth
     * @param billsMap
     * @return
     */
    private List<CardBlueBillDetail> pojoByCardBlueBillDetail(String billId, String statementMonth, Map<String, Object> billsMap){
        
        List<CardBlueBillDetail> cardBlueBillDetailList = new ArrayList<CardBlueBillDetail>();
        
        Map<String, Object> detailsMap = null;
        CardBlueBillDetail cardBlueBillDetail = null;
        List<Object> detailsList = (List<Object>) billsMap.get("details");
        for (int k = 0; k < detailsList.size(); k++) {
            // 详细信息表
            detailsMap = new HashMap<String, Object>();
            detailsMap = (Map<String, Object>) detailsList.get(k);
            cardBlueBillDetail = new CardBlueBillDetail();
            
            cardBlueBillDetail.setId(KeySn.getKey());
            cardBlueBillDetail.setCardBlueBillId(billId);
            cardBlueBillDetail.setStatementMonth(statementMonth);
            String cardNo = getCardNo4((String)detailsMap.get("cardNo"));
            cardBlueBillDetail.setCardNo((String)detailsMap.get("cardNo"));
            cardBlueBillDetail.setTrdDate((String)detailsMap.get("trdDate"));
            cardBlueBillDetail.setAccDate((String)detailsMap.get("accDate"));
            cardBlueBillDetail.setCard4No(cardNo);
            cardBlueBillDetail.setPayMoney(BigDecimalUtil.EmptyToNullStr((String)detailsMap.get(" ")));
            cardBlueBillDetail.setCurrency((String)detailsMap.get("currency"));
            cardBlueBillDetail.setAccMoney(BigDecimalUtil.EmptyToNullStr((String)detailsMap.get("accAmt")));
            cardBlueBillDetail.setSummary((String)detailsMap.get("summary"));
            cardBlueBillDetail.setCreateDate(new Date());
            cardBlueBillDetail.setType(CardBlueBillDetailState.CODE00.getCode());
            cardBlueBillDetail.setState(CardBlueBillDetailType.CODE00.getCode());
            cardBlueBillDetail.setIsDel(IsDel.CODE00.getCode());
            cardBlueBillDetailList.add(cardBlueBillDetail);
        }
        return cardBlueBillDetailList;
    } 
    
    
    /**
     * 分期实体封装
     * @param billId
     * @param statementMonth
     * @param billsMap
     * @return
     */
    private List<BillInstallments> pojoBybillInstallments(String billId, String statementMonth, Map<String, Object> billsMap){
        
        List<BillInstallments> billInstallmentsList = new ArrayList<BillInstallments>();
        
        BillInstallments billInstallments = null;
        Map<String, Object> installmentsMap = null;
        List<Object> installmentsList = (List<Object>) billsMap.get("installments");
        for (int i = 0; i < installmentsList.size(); i++) {
            // 分期信息表
            installmentsMap = new HashMap<String, Object>();
            installmentsMap = (Map<String, Object>) installmentsList.get(i);
            billInstallments = new BillInstallments();
            
            billInstallments.setId(KeySn.getKey());
            billInstallments.setCardBlueBillId(billId);
            billInstallments.setStatementMonth(statementMonth);
            String cardNo = getCardNo4((String)installmentsMap.get("cardNo"));
            billInstallments.setCardNo((String)installmentsMap.get("cardNo"));
            billInstallments.setCard4No(cardNo);
            billInstallments.setInstalmentType((String)installmentsMap.get("instalmentType"));
            billInstallments.setInstalmentDate((String)installmentsMap.get("instalmentDate"));
            billInstallments.setInstalmentMoney(BigDecimalUtil.EmptyToNullStr((String)installmentsMap.get("instalmentBal")));
            billInstallments.setInstalmentCount((Integer)installmentsMap.get("instalmentCount"));
            billInstallments.setResiduaInstalmentCount((Integer)installmentsMap.get("residuaInstalmentCount"));
            billInstallments.setCrlInstalmentMoney(BigDecimalUtil.EmptyToNullStr((String)installmentsMap.get("curInstalmentAmt")));
            billInstallments.setCrlInstalmentFeeMoney(BigDecimalUtil.EmptyToNullStr((String)installmentsMap.get("curInstalmentFeeAmt")));
            billInstallments.setCrlRepaymentMoney(BigDecimalUtil.EmptyToNullStr((String)installmentsMap.get("curRepaymentAmt")));
            billInstallments.setResidualPrincipal(BigDecimalUtil.EmptyToNullStr((String)installmentsMap.get("residualPrincipal")));
            billInstallments.setCreateDate(new Date());
            billInstallments.setType(BillInstallmentsState.CODE00.getCode());
            billInstallments.setState(BillInstallmentsType.CODE00.getCode());
            billInstallments.setIsDel(IsDel.CODE00.getCode());
            billInstallmentsList.add(billInstallments);
        }
        return billInstallmentsList;
    }
    
    public static void main(String[] args) {
//        new EmailBillInsertService().addEmailAndInsertBill("logid", "userid","123123");
        String cardNo = "02031";
        if(cardNo.length() > 4){
            cardNo = cardNo.substring(cardNo.length()-4, cardNo.length());
        }
        System.out.println(cardNo);
    }
    
    private String getCardNo4(String cardNo){
        if(null != cardNo && !"".equals(cardNo)){
            if(cardNo.length() > 4){
                cardNo = cardNo.substring(cardNo.length()-4, cardNo.length());
            }
        }
        return cardNo;
    }

}
