package com.zhl.card.service;

import java.util.Map;

import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.CardValidRes;


public interface ICardValidResService {
    
    /**
     * 写入
     * @param cardValidRes
     * @return
     */
    int insertOne(CardValidRes cardValidRes);
    
    /**
     * 银行卡有效性验证
     * @param keySn
     * @param cardInfo
     * @return
     */
    Map<String, Object> invokeInterfaceValidCard(String keySn, CardInfo cardInfo);
    
    
    
}
