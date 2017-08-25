package com.zhl.card.service;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.CardInfo;


public interface ICardInfoService {
    
    /**
     * 根据用户id和卡号查询
     * @param cardInfo
     * @return
     */
    List<CardInfo> getCardByUserIdAndCardNo(CardInfo cardInfo);
    
    /**
     * 根据用户id查询卡列表
     * @param cardInfo
     * @return
     */
    List<CardInfo> getCardByUserId(String userId, String cardType);
    
    /**
     * 正常绑卡写入
     * @param cardInfo
     * @return
     */
    int insetCardInfo(CardInfo cardInfo);
    
    /**
     * 修改
     * @param cardInfo
     * @return
     */
    int updateCardInfo(String userId, String id);
    
    /**
     * 绑卡补全修改
     * @param cardInfo
     * @return
     */
    int updateCardInfoByBindSupply(CardInfo cardInfo);
    

}
