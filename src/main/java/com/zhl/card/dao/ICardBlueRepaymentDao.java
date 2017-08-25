package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.CardBlueRepayment;

public interface ICardBlueRepaymentDao {
    
    /**
     * 按条件查询
     * @param map
     * @return
     */
    List<CardBlueRepayment> queryCardBlueRepaymentCondition2List(Map<String, Object> map);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateCardBlueRepayment(Map<String, Object> map);
    
    /**
     * 写入
     * @param billInstallments
     * @return
     */
    int insertCardBlueRepayment(CardBlueRepayment cardBlueRepayment);

}
