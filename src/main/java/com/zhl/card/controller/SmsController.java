package com.zhl.card.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.card.controller.common.BaseController;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.common.SmsCode;
import com.zhl.card.service.ISmsService;
import com.zhl.card.util.Stringer;

@Controller
@RequestMapping(value = "/sms")
public class SmsController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(SmsController.class);
	
	@Resource
	private ISmsService smsService;
	
	/**
     * 
      * 生成验证码
      *
      * @Title: verifycode
      * @param @param model
      * @param @param request    设定文件
      * @return void    返回类型
      * @throws
     */
//    @RequestMapping(value = "/sendSMS",method={RequestMethod.POST})
//    @ResponseBody
//    public Object sendSMS(String logMobile) {
//        logs.info("生成验证码");
//        if(!Stringer.isMobile(logMobile)){
//            return error("请填写正确的手机号");
//        }
//        return sendSMSService.sendVerifyCode(mobile,randomDigital);
//    }
	
	/**
     * 验证码对比
     * @return
     */
    @RequestMapping(value = "/compareSmsCode")
    @ResponseBody
    public Object compareSmsCode(String mobile, String smsCode){
        
        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("对比短信验证码开始--->keySn=" + keySn + ",mobile=" + mobile + ",smsCode=" + smsCode);
        
        String check = checkCompareSmsCode(mobile, smsCode);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        String reStr = smsService.compareSmsCode(keySn, mobile, smsCode);
        if(null == reStr){
            resEntity.setResponseEntity(keySn, SmsCode.CODE000000.getCode(), SmsCode.CODE000000.getName());
        } else {
            resEntity.setResponseEntity(keySn, SmsCode.CODE200001.getCode(), reStr);
        }
        logs.info("对比短信验证码结束--->返回参数=" + resEntity.toString());
        return resEntity;
    }
    
    /**
     * 检查传入数据
     * @param mobile
     * @param smsCode
     * @return
     */
    private String checkCompareSmsCode(String mobile, String smsCode){
        if(StringUtils.isBlank(mobile)){
            return "手机号不能为空";
        }
        if(!Stringer.isMobile(mobile)){
            return "请填写正确的手机号";
        }
        if(StringUtils.isBlank(smsCode)){
            return "验证码不能为空";
        }
        return null;
    }
	
}
