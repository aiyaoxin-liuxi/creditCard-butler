package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.card.pojo.SmsInfo;


public interface ISmsInfoDao {
	
    /**
     * 写入
     * @param userInfo
     * @return
     */
    int insertSmsInfo(SmsInfo smsInfo);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateSmsInfo(Map<String, Object> map);
    
    /**
     * 按条件查询返回list
     * @param map
     * @return
     */
    List<SmsInfo> querySmsInfoCondition2List(Map<String, Object> map);
    
    /**
     * 根据手机号查询
     * @param id
     * @return
     */
    SmsInfo queryUserInfoByMobile(@Param("mobile") String mobile);
	
}
