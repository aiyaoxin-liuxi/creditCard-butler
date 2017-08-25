package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.card.pojo.UserInfo;


public interface IUserInfoDao {
	
    /**
     * 写入
     * @param userInfo
     * @return
     */
    int insertUserInfo(UserInfo userInfo);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateUserInfo(Map<String, Object> map);
    
    /**
     * 按条件查询返回list
     * @param map
     * @return
     */
    List<UserInfo> queryUserInfoCondition2List(Map<String, Object> map);
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserInfo queryUserInfoById(@Param("id") String id);
    
    
	
}
