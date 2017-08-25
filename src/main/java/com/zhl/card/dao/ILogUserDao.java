package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zhl.card.pojo.LogUser;


public interface ILogUserDao {

    /**
     * 写入
     * @param logUser
     * @return
     */
    int insertLogUser(LogUser logUser);
    
    /**
     * 修改
     * @param map
     * @return
     */
    int updateLogUser(Map<String, Object> map);
    
    /**
     * 按条件查询返回list
     * @param map
     * @return
     */
    List<LogUser> queryLogUserCondition2List(Map<String, Object> map);
    
    /**
     * 根据登录手机号查询用户
     * @param logMobile
     * @return
     */
    LogUser queryLogUserByLogMobile(@Param("logMobile") String logMobile);
    
    /**
     * 根据id查询用户
     * @param logMobile
     * @return
     */
    LogUser queryLogUserById(@Param("id") String id);
    
    
}
