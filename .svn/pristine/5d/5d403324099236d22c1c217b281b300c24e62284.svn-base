package com.zhl.card.service;

import java.util.List;

import com.zhl.card.pojo.EmailInfo;
import com.zhl.card.pojo.LogUser;
import com.zhl.card.pojo.UserInfo;


public interface IUserInfoService {
    
    /**
     * 完善用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfo(UserInfo userInfo);
    
    /**
     * 修改头像
     * @param userId
     * @param headUrl
     * @return
     */
    int updateUserHeadImg(String userId, String headImg);
    
    /**
     * 根据id查询用户详细信息
     * @param id
     * @return
     */
    UserInfo queryUserInfoById(String id);
    
    /**
     * 根据用户登录id和用户id查询用户信息是否存在
     * @param logId
     * @param userId
     * @return
     */
    List<UserInfo> queryEmailByLogIdAndUserId(String logId, String userId);
    
    
    
}
