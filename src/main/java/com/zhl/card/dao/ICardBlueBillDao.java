package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.CardBlueBill;

public interface ICardBlueBillDao {
    
    /**
     * 按条件查询
     * @param map
     * @return
     */
    List<CardBlueBill> queryCardBlueBillCondition2List(Map<String, Object> map);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateCardBlueBill(Map<String, Object> map);
    
    /**
     * 写入
     * @param billInstallments
     * @return
     */
    int insertCardBlueBill(CardBlueBill cardBlueBill);
    
    /**
     * 根据id查询卡下所有账单信息
     * @param map
     * @return
     */
    List<CardBlueBill> queryBillAllById(Map<String, Object> map);

}
