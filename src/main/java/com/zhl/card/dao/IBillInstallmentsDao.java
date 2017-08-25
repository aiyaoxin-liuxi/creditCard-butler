package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.BillInstallments;

public interface IBillInstallmentsDao {
    
    /**
     * 按条件查询
     * @param map
     * @return
     */
    List<BillInstallments> queryBillInstallmentsCondition2List(Map<String, Object> map);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateBillInstallments(Map<String, Object> map);
    
    /**
     * 写入
     * @param billInstallments
     * @return
     */
    int insertBillInstallments(BillInstallments billInstallments);
    
    /**
     * 批量写入
     * @param billInstallments
     * @return
     */
    int insertBillInstallmentsList(List<BillInstallments> list);

}
