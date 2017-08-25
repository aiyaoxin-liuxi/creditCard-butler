package com.zhl.card.service;

import java.util.List;

import com.zhl.card.pojo.CardBlueBill;
import com.zhl.card.pojo.CardInfo;


public interface ICardBlueBillService {
    
    /**
     * 按条件查询用户信用卡账单
     * @param cardBlueBill
     * @return
     */
	List<CardBlueBill> queryBillConditionByUserId(CardBlueBill cardBlueBill);
	
	/**
	 * 查询所有账单信息
     * 查询用户下卡、账单、账单详情、账单明细
     * @param logId
     * @param userId
     * @return
     */
    List<CardInfo> queryCardInfoAndBillInfoAll(String logId, String userId);
    
    /**
     * 查询所有账单信息————详情
     * 根据卡id查询用户下账单、账单详情、账单明细
     * @param logId
     * @param userId
     * @return
     */
    List<CardInfo> queryCardInfoAndBillInfoAll(String logId, String userId, String cardId);

}
