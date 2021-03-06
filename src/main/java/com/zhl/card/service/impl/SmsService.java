package com.zhl.card.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.date.DateConverter;
import org.pub.util.json.JsonUtil;
import org.pub.util.security.MessageSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.ISmsInfoDao;
import com.zhl.card.pojo.SmsInfo;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.pojo.enums.common.SmsCode;
import com.zhl.card.service.ISmsService;
import com.zhl.card.util.Constant;
import com.zhl.card.util.RandomGenerator;
import com.zhl.card.util.Stringer;
import com.zhl.card.util.Utilc;
import com.zhl.card.util.http.HttpClientHelper;

@Service("smsService")
public class SmsService implements ISmsService {
    
    private Logger logs = LoggerFactory.getLogger(SmsService.class);
    
    @Resource
    private ISmsInfoDao smsInfoDao;

    @Override
    public ResponseEntity sendSmsCode(String keySn, String mobile) {
        // 生成验证码
        String smsCode = createSmsCode(6);
        logs.info("生成短信验证码:" + smsCode);
        return sendVerifyCode(keySn, mobile, smsCode);
    }
    
    @Override
    public String compareSmsCode(String keySn, String mobile, String smsCode) {
        String reStr = null;
        SmsInfo smsInfo = smsInfoDao.queryUserInfoByMobile(mobile);
        if(null == smsInfo || !smsInfo.getSmsCode().equals(smsCode)){
            reStr = "验证码错误";
        } else {
            Date updateDate = smsInfo.getUpdateDate();
            if(null != updateDate){
                long l = DateConverter.getSecsDiff(new Date(), updateDate);
                if(l > 60){
                    reStr = "验证码过期";
                }
            }
        }
        return reStr;
    }
    
    /**
     * 生成短信验证码
     * @param len
     * @return
     */
    private String createSmsCode(int len){
        return RandomGenerator.randomDigital(6);
    }
    
    /**
     * 发送验证码短信
     * @param keySn
     * @param mobile
     * @param smsCode
     * @return
     */
    private ResponseEntity sendVerifyCode(String keySn, String mobile, String smsCode){
        
        logs.info("短信验证码发送--->开始");
        ResponseEntity resEntity = new ResponseEntity();
        
        if(Stringer.isNullOrEmpty(Constant.DEV_SWITCH)  || !Constant.DEV_SWITCH.equals("1")){//测试的时候可以用这个，不真正发短信，验证码看log
            logs.debug(">>>>>simulation模拟  发送验证码，mobile：" + mobile + ",验证码：" + smsCode);
            // 入库记录，用于对比
            int reInt = inSmsCodeDB(mobile, smsCode);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, SmsCode.CODE000000.getCode(), SmsCode.CODE000000.getName(), smsCode);
            }
            return resEntity;
        }
        logs.debug("发送验证码，mobile：" + mobile + ",验证码:" + smsCode);
        
        Map<String,Object> paramMap = getParamMap(mobile, smsCode);
        //**************************************
        String json = JsonUtil.getMapToJson(paramMap);
        String url = Constant.SEND_SMS_URL;
        logs.info(url+" <--上送的url，【发送短信验证码.】上送的param："+json);
        String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json, "60000","application/json","");
        logs.info(" 【发送短信验证码.】返回的结果" + o);
        if(!Stringer.isNullOrEmpty(o)){
            Map<String, Object> map = JsonUtil.getJsonToMap(o);
            Object returnCodeObj = map.get("returnCode");
            if(!Stringer.isNullOrEmpty(returnCodeObj)){
                String returnCode = returnCodeObj.toString();
                if(SmsCode.CODE111111.getCode().equals(returnCode)){
                    
                    // 入库记录，用于对比
                    int reInt = inSmsCodeDB(mobile, smsCode);
                    if(reInt == 1){
                        resEntity.setResponseEntity(keySn, SmsCode.CODE000000.getCode(), SmsCode.CODE000000.getName());
                    }
                    return resEntity;
                } else {
                    resEntity.setResponseEntity(keySn, (String)map.get("returnCode"), (String)map.get("returnCode"));
                    return resEntity;
                }
            } else {
                logs.info(" 【发送短信验证码.】返回的结果：为空");
                resEntity.setResponseEntity(keySn, SmsCode.CODE200008.getCode(), SmsCode.CODE200008.getName());
                return resEntity;
            }
        } else {
            logs.info(" 【发送短信验证码.】返回的结果：为空");
            resEntity.setResponseEntity(keySn, SmsCode.CODE200008.getCode(), SmsCode.CODE200008.getName());
            return resEntity;
        }
//        try {
//            SmsCode parseOf = SmsCode.parseOf(returnCode);
//            logs.info(" 【发送短信验证码.】返回的结果"+parseOf.getName());
//            resEntity.setResponseEntity(keySn, SmsCode.CODE410041.getCode(), SmsCode.CODE410041.getName());
//            return resEntity;
//            
//        } catch (Exception e) {
//            logs.info(" 返回结果异常-未知的返回结果"+o);
//            resEntity.setResponseEntity(keySn, SmsCode.CODE410041.getCode(), SmsCode.CODE410041.getName());
//            return resEntity;
//        }
    }
    
    /**
     * 写入/更新数据库记录的验证码
     * @return
     */
    private int inSmsCodeDB(String mobile, String smsCode){
        
        SmsInfo smsInfo = smsInfoDao.queryUserInfoByMobile(mobile);
        int reInt = 0;
        if(null == smsInfo){
            smsInfo = new SmsInfo();
            smsInfo.setMobile(mobile);
            smsInfo.setSmsCode(smsCode);
            smsInfo.setState("00");
            smsInfo.setType("00");
            smsInfo.setCreateDate(new Date());
            smsInfo.setUpdateDate(new Date());
            smsInfo.setIsDel(IsDel.CODE00.getCode());
            
            reInt = smsInfoDao.insertSmsInfo(smsInfo);
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("mobile", mobile);
            paramMap.put("smsCode", smsCode);
            paramMap.put("updateDate", new Date());
            reInt = smsInfoDao.updateSmsInfo(paramMap);
        }
        return reInt;
    }
    
    
    /**
     * 短信验证码数据拼接
     * @param mobile
     * @param smsCode
     * @return
     */
    private Map<String, Object> getParamMap(String mobile, String smsCode) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("applyNo", "0000006");// 商户编号
        map.put("mobile", mobile);// 接收手机号
        map.put("outSmsNo", "0000001");// 下游消息编号
        map.put("content", "您的短信验证码是：" + smsCode);// 发送内容（验证码）01
        map.put("type", "01");
        map = Utilc.sortMapByKey(map);// 排序
        String json = JsonUtil.getMapToJson(map);
        String sign = MessageSecurity.getMessageSecurity("", json, "PARTSJOB");
        map.put("sign", sign);
        return map;
    }
    


}
