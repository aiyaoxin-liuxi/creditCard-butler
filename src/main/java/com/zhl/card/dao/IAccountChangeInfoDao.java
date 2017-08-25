package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.AccountChangeInfo;

public interface IAccountChangeInfoDao {
    
    /**
     * 按条件查询
     * @param map
     * @return
     */
    List<AccountChangeInfo> queryAccountChangeInfoCondition2List(Map<String, Object> map);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateAccountChangeInfo(Map<String, Object> map);
    
    /**
     * 写入
     * @param billInstallments
     * @return
     */
    int insertAccountChangeInfo(AccountChangeInfo accountChangeInfo);
    
    /**
     * 批量写入
     * @param billInstallments
     * @return
     */
    int insertAccountChangeInfoList(List<AccountChangeInfo> list);

}
