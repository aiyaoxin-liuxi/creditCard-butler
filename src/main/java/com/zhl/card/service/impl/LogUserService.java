package com.zhl.card.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhl.card.controller.LogUserController;
import com.zhl.card.dao.ILogUserDao;
import com.zhl.card.dao.IUserInfoDao;
import com.zhl.card.pojo.LogUser;
import com.zhl.card.pojo.UserInfo;
import com.zhl.card.pojo.enums.UserState;
import com.zhl.card.pojo.enums.UserType;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.service.ILogUserService;
import com.zhl.card.util.Stringer;
@Service("logUserService")
public class LogUserService implements ILogUserService {
    
    private Logger logs = LoggerFactory.getLogger(LogUserService.class);

    @Resource
    private ILogUserDao logUserDao;
	@Resource
	private IUserInfoDao userInfoDao;

	@Transactional
    @Override
    public int setRegister(String logUserId, String userId, LogUser logUser) {
        
        int reInt = 0;
        // 生成登录实体
        logUser.setId(logUserId);
        logUser.setUserId(userId);
        logUser.setUserType(UserType.CODE01.getCode());
        logUser.setUserState(UserState.CODE01.getCode());
        logUser.setCreateDate(new Date());
        logUser.setIsDel(IsDel.CODE00.getCode());
        Stringer.encryptLogPwd(logUser);
        
        // 生成信息实体
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        userInfo.setLogId(logUserId);
        userInfo.setUserType(UserType.CODE01.getCode());
        userInfo.setUserState(UserState.CODE01.getCode());
        userInfo.setCreateDate(new Date());
        userInfo.setIsDel(IsDel.CODE00.getCode());
        
        reInt = logUserDao.insertLogUser(logUser);
        reInt = userInfoDao.insertUserInfo(userInfo);
        
        return reInt;
    }
    
    @Override
    public List<LogUser> queryLogUserByCondition(String logMobile){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logMobile", logMobile);
        map.put("idDel", IsDel.CODE00.getCode());
        return logUserDao.queryLogUserCondition2List(map);
    }

    @Override
    public Map<String, Object> login(String keySn, String logMobile, String logPassword) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        
        paramMap.put("logMobile", logMobile);
        paramMap.put("idDel", IsDel.CODE00.getCode());
        LogUser logUser = logUserDao.queryLogUserByLogMobile(logMobile);
        if(null != logUser){
            if(logUser.getLogPassword().equals(logPassword)){
                UserInfo userInfo = userInfoDao.queryUserInfoById(logUser.getUserId());
                if(null != userInfo){
                    logUser.setUserInfo(userInfo);
                    returnMap.put("LogUser", logUser);
                }
            }
        }
        return returnMap;
    }

    @Override
    public String updatePassword(String keySn, String id, String logPassword, String newLogPassword) {
        
        String reStr = "";
        LogUser oldLogUser = logUserDao.queryLogUserById(id);
        if(null != oldLogUser){
            if(logPassword.equals(oldLogUser.getLogPassword())){
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("id", id);
                paramMap.put("logPassword", newLogPassword);
                int reInt = logUserDao.updateLogUser(paramMap);
                if(reInt == 1){
                    reStr = "1";
                }
            } else {
                // 旧密码错误
                reStr = "旧密码错误";
            }
        } else {
            // 没找到对象
            reStr = "没找到对象";
        }
        return reStr;
    }
    
    @Override
    public int forgotPassword(String keySn, String id, String newLogPassword) {
        newLogPassword = Stringer.encryptLogPwd(newLogPassword);
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("id", id);
        paraMap.put("logPassword", newLogPassword);
        return logUserDao.updateLogUser(paraMap);
    }

}
