package com.zhl.card.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhl.card.dao.IUserInfoDao;
import com.zhl.card.pojo.UserInfo;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.service.IUserInfoService;
@Service("userInfoService")
public class UserInfoService implements IUserInfoService {

	@Resource
	private IUserInfoDao userInfoDao;

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        paramMap.put("id", userInfo.getId());
        paramMap.put("realName", userInfo.getRealName());
        paramMap.put("idCardType", userInfo.getIdCardType());
        paramMap.put("idCardNo", userInfo.getIdCardNo());
        paramMap.put("userSex", userInfo.getUserSex());
        paramMap.put("nickName", userInfo.getNickName());
        paramMap.put("userMobile", userInfo.getUserMobile());
        paramMap.put("qq", userInfo.getQq());
        paramMap.put("wechatNumber", userInfo.getWechatNumber());
        paramMap.put("wechatName", userInfo.getWechatName());
        paramMap.put("updateDate", new Date());
        paramMap.put("updateUser", userInfo.getId());
        
        return userInfoDao.updateUserInfo(paramMap);
    }

    @Override
    public UserInfo queryUserInfoById(String id) {
        return userInfoDao.queryUserInfoById(id);
    }

    @Override
    public int updateUserHeadImg(String userId, String headImg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", userId);
        paramMap.put("headImg", headImg);
        return userInfoDao.updateUserInfo(paramMap);
    }

    @Override
    public List<UserInfo> queryEmailByLogIdAndUserId(String logId, String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logId", logId);
        map.put("userId", userId);
        map.put("isDel", IsDel.CODE00.getCode());
        return userInfoDao.queryUserInfoCondition2List(map);
    }


}
