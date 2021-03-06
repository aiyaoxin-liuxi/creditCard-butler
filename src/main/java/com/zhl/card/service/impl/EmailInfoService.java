package com.zhl.card.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.IEmailInfoDao;
import com.zhl.card.pojo.EmailInfo;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.email.EmailAcceptState;
import com.zhl.card.pojo.enums.email.EmailState;
import com.zhl.card.pojo.enums.email.EmailType;
import com.zhl.card.service.IEmailInfoService;
import com.zhl.card.util.RSAUtil;

@Service("emailInfoService")
public class EmailInfoService implements IEmailInfoService {
	
    private Logger logs = LoggerFactory.getLogger(EmailInfoService.class);
    
    @Resource
    private IEmailInfoDao emailInfoDao;

    @Override
    public Map<String, Object> addEmailInfo(String keySn, EmailInfo emailInfo){
        
        Map<String, Object> returnMap = new HashMap<String, Object>();
        
        // 检查此用户是否添加过此邮箱
        List<EmailInfo> emailList = queryEmailInfoByIdAndEmail(emailInfo);
        if(null != emailList && emailList.size() > 0){
            logs.info("存在email信息...准备恢复删除邮箱--->keySn = " + keySn);
            // 恢复之前删除的
            EmailInfo eInfo = emailList.get(0);
            if(IsDel.CODE01.getCode().equals(eInfo.getIsDel())){
//                eInfo.setIsDel(IsDel.CODE00.getCode());// 成功后返回用
                int reInt = reDelEmailInfo(eInfo);
                if(reInt == 1){
                    returnMap.put("returnCode", ReturnCode.SUCCESS_000000.getCode());
                    returnMap.put("reason", ReturnCode.SUCCESS_000000.getName());
                    returnMap.put("data", eInfo.getId());
//                    returnMap.put("emailInfo", eInfo);
                } else {
                    returnMap.put("returnCode", ReturnCode.INSERT_FAIL_200007.getCode());
                    returnMap.put("reason", ReturnCode.INSERT_FAIL_200007.getName());
                }
            } else {
                returnMap.put("returnCode", ReturnCode.EMAIL_EXIST_2222223.getCode());
                returnMap.put("reason", ReturnCode.EMAIL_EXIST_2222223.getName());
            }
            
        } else {
            logs.info("存在email信息...准备添加email--->keySn = " + keySn);
            // 添加
            int reInt = insertEmailInfo(keySn, emailInfo);
            if(reInt == 1){
                returnMap.put("returnCode", ReturnCode.SUCCESS_000000.getCode());
                returnMap.put("reason", ReturnCode.SUCCESS_000000.getName());
                returnMap.put("data", keySn);
//                returnMap.put("emailInfo", emailInfo);
            } else {
                returnMap.put("returnCode", ReturnCode.INSERT_FAIL_200007.getCode());
                returnMap.put("reason", ReturnCode.INSERT_FAIL_200007.getName());
            }
        }
        logs.info("添加or恢复email...结束--->keySn = " + keySn + ",returnMap = " + returnMap);
        return returnMap;
    }
    
    @Override
    public List<EmailInfo> queryEmailInfoByIdAndEmail(EmailInfo emailInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", emailInfo.getUserId());
        paramMap.put("email", emailInfo.getEmail());
        return emailInfoDao.queryEmailCondition2List(paramMap);
    }
    
    @Override
    public int insertEmailInfo(String keySn, EmailInfo emailInfo) {
        emailInfo.setEmailPassword(emailInfo.getEmailPassword());
        // 密码base64
//        emailInfo.setEmailPassword(RSAUtil.base64Encode(emailInfo.getEmailPassword().getBytes()));
        emailInfo.setId(keySn);
        emailInfo.setState(EmailState.CODE00.getCode());
        emailInfo.setType(EmailType.CODE00.getCode());
        emailInfo.setCreateDate(new Date());
        emailInfo.setIsDel(IsDel.CODE00.getCode());
        return emailInfoDao.insertEmailInfo(emailInfo);
    }

    @Override
    public int reDelEmailInfo(EmailInfo emailInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", emailInfo.getId());
        emailInfo.setEmailPassword(emailInfo.getEmailPassword());
        // 密码base64
//        emailInfo.setEmailPassword(RSAUtil.base64Encode(emailInfo.getEmailPassword().getBytes()));
        paramMap.put("isDel", IsDel.CODE00.getCode());
        return emailInfoDao.updateEmailInfo(paramMap);
    }

    @Override
    public EmailInfo queryEmailInfoById(String id) {
        EmailInfo emailInfo = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("isDel", IsDel.CODE00.getCode());
        List<EmailInfo> emailList = emailInfoDao.queryEmailCondition2List(paramMap);
        if(null != emailList && emailList.size() == 1){
            emailInfo = emailList.get(0);
        }
        return emailInfo;
    }

    @Override
    public EmailInfo queryEmailByybId(String ybId) {
        EmailInfo emailInfo = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("ybId", ybId);
        paramMap.put("isDel", IsDel.CODE00.getCode());
        List<EmailInfo> emailList = emailInfoDao.queryEmailCondition2List(paramMap);
        if(null != emailList && emailList.size() == 1){
            emailInfo = emailList.get(0);
        }
        return emailInfo;
    }

    @Override
    public int updateEmailInfoTokenAndybIdByid(String token, String ybId, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("token", token);
        paramMap.put("ybId", ybId);
        paramMap.put("id", id);
        paramMap.put("acceptState", EmailAcceptState.CODE00.getCode());
        return emailInfoDao.updateEmailInfo(paramMap);
    }
    
    @Override
    public int updateEmailInfoAcceptStateByid(String id, String acceptState) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("acceptState", acceptState);
        return emailInfoDao.updateEmailInfo(paramMap);
    }

    @Override
    public List<EmailInfo> getBindEmail(String logId, String userId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("logId", logId);
        paramMap.put("userId", userId);
        paramMap.put("isDel", IsDel.CODE00.getCode());
        return emailInfoDao.queryEmailCondition2ListNoPassword(paramMap);
    }

    @Override
    public int updateEmailPassWord(String emailId, String email, String emailPassword) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("email", email);
        paramMap.put("emailPassword", emailPassword);
        paramMap.put("id", emailId);
        return emailInfoDao.updateEmailInfo(paramMap);
    }

    @Override
    public List<EmailInfo> queryEmailInfoByIdAndUserId(String logId, String userId, String emailId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("logId", logId);
        paramMap.put("userId", userId);
        paramMap.put("id", emailId);
        return emailInfoDao.queryEmailCondition2List(paramMap);
    }

   
}
