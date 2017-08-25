package com.zhl.card.service;

import java.util.Map;

import com.zhl.card.pojo.common.ResponseEntity;



public interface ISmsService {
    
    /**
     * 发送短信
     * @param logMobile
     * @return
     */
    ResponseEntity sendSmsCode(String keySn, String mobile);
    
    /**
     * 对比验证码是否正确
     * @param keySn
     * @param logMobile
     * @param smsCode
     * @return
     */
    String compareSmsCode(String keySn, String mobile, String smsCode);
    
    
}
