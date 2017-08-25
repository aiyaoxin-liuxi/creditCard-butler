package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.CardBlueBillDetail;

public interface ICardBlueBillDetailDao {
    
    /**
     * 按条件查询
     * @param map
     * @return
     */
    List<CardBlueBillDetail> queryCardBlueBillDetailCondition2List(Map<String, Object> map);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateCardBlueBillDetail(Map<String, Object> map);
    
    /**
     * 写入
     * @param insertCardBlueBillDetail
     * @return
     */
    int insertCardBlueBillDetail(CardBlueBillDetail cardBlueBillDetail);
    
    /**
     * 批量写入
     * @param insertCardBlueBillDetail
     * @return
     */
    int insertCardBlueBillDetailList(List<CardBlueBillDetail> list);

}
