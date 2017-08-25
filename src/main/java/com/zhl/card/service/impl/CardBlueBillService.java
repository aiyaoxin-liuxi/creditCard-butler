package com.zhl.card.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.zhl.card.dao.ICardBlueBillDao;
import com.zhl.card.dao.ICardInfoDao;
import com.zhl.card.pojo.CardBlueBill;
import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.enums.CardType;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.service.ICardBlueBillService;
import com.zhl.card.util.BigDecimalUtil;
import com.zhl.card.util.Stringer;

import freemarker.template.utility.StringUtil;

@Service("cardBlueBillService")
public class CardBlueBillService implements ICardBlueBillService {
	
    private Logger logs = LoggerFactory.getLogger(CardBlueBillService.class);
    
    @Resource
    private ICardBlueBillDao cardBlueBillDao;
    @Resource
    private ICardInfoDao cardInfoDao;

    @Override
    public List<CardBlueBill> queryBillConditionByUserId(CardBlueBill cardBlueBill) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("logId", cardBlueBill.getLogId());
        paramMap.put("userId", cardBlueBill.getUserId());
        paramMap.put("cardNo", cardBlueBill.getCardNo());
        paramMap.put("isDel", IsDel.CODE00.getCode());
        return cardBlueBillDao.queryCardBlueBillCondition2List(paramMap);
    }
    

    
    @Override
    public List<CardInfo> queryCardInfoAndBillInfoAll(String logId, String userId) {
        return queryCardInfoAndBillInfoAll(logId, userId, null);
    }
    @Override
    public List<CardInfo> queryCardInfoAndBillInfoAll(String logId, String userId, String cardId) {
        
        List<CardInfo> CardInfoList = null;
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("logId", logId);
        paramMap.put("userId", userId);
        if(null != cardId){
            paramMap.put("id", cardId);
        }
        paramMap.put("cardType", CardType.CODE02.getCode()); 
        paramMap.put("idDel", IsDel.CODE00.getCode());
        CardInfoList = cardInfoDao.queryCardInfoCondition2List(paramMap);
        if(null != CardInfoList && CardInfoList.size() > 0){
            List<CardBlueBill> cardBlueBillList = null;
            for(CardInfo cardInfo : CardInfoList){
                
                cardBlueBillList = new ArrayList<CardBlueBill>();
                // 根据id查询账单
                paramMap = new HashMap<String, Object>();
                paramMap.put("logId", logId);
                paramMap.put("userId", userId);
                paramMap.put("cardId", cardInfo.getId());
                paramMap.put("idDel", IsDel.CODE00.getCode());
                cardBlueBillList = cardBlueBillDao.queryBillAllById(paramMap);
                if(null != cardBlueBillList && cardBlueBillList.size() > 0){
                    CardBlueBill cardBlueBill = cardBlueBillList.get(0);
                    // 获取页面显示所需要的最近一期的相关数据
                    getHtmlShowData(cardInfo, cardBlueBill);
                }
                cardInfo.setCardBlueBillList(cardBlueBillList);
            }
        }
        return CardInfoList;
    }
    
    public static void main(String[] args) {
        String statementStartDate = "2017-01-02";
        statementStartDate = StringUtil.replace(statementStartDate, "-", "/");
        System.out.println(statementStartDate);
    }
    
    /**
     * 获取页面显示所需要的最近一期的相关数据
     */
    private void getHtmlShowData(CardInfo cardInfo ,CardBlueBill cardBlueBill){
        
        // 用于页面显示最近一期到期还款日
        String showPaymentDueDate = cardBlueBill.getPaymentDueDate();
        cardInfo.setShowPaymentDueDate(showPaymentDueDate == null ? "" : showPaymentDueDate);
        // 用于页面显示最近一期信用额度
        BigDecimal creditLimitB = cardBlueBill.getCreditLimit();
        if(null != creditLimitB){
            cardInfo.setShowCreditLimit(String.valueOf(creditLimitB));
        }
        // 用于页面显示最近一期取现额度
        BigDecimal withdrawalLimitB = cardBlueBill.getWithdrawalLimit();
        if(null != withdrawalLimitB){
            cardInfo.setShowWithdrawalLimit(String.valueOf(withdrawalLimitB));
        }
        
        // 用于页面显示最近一期账单周期
        String showBillCyc = "";
        String statementStartDate = cardBlueBill.getStatementStartDate();// 账单起始日
        String statementEndDate = cardBlueBill.getStatementEndDate();// 账单截止日
        if(!Stringer.isNullOrEmpty(statementStartDate) && !Stringer.isNullOrEmpty(statementEndDate)){
            StringBuilder sb = new StringBuilder();
            sb.append(StringUtil.replace(statementStartDate.substring(5, statementStartDate.length()), "-", "/"));
            sb.append("-");
            sb.append(StringUtil.replace(statementEndDate.substring(5, statementEndDate.length()), "-", "/"));
            showBillCyc = sb.toString();
        }
        cardInfo.setShowBillCyc(showBillCyc);
        // 用于页面显示最近一期本期应还金额
        String strMoney = String.valueOf(BigDecimalUtil.EmptyToNull(cardBlueBill.getCurPaymentMoney()));
        cardInfo.setShowCurPaymentMoney(strMoney == null ? "" : strMoney);
        // 用于页面显示最近一期账单日期
        String strMonth = cardBlueBill.getStatementMonth();
        cardInfo.setShowStatementMonth(strMonth == null ? "" : strMonth);
        cardBlueBill.getCurPaymentMoney();
        
    }
}
