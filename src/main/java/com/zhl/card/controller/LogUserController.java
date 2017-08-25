package com.zhl.card.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.card.controller.common.BaseController;
import com.zhl.card.pojo.LogUser;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.common.SmsCode;
import com.zhl.card.service.ILogUserService;
import com.zhl.card.service.ISmsService;
import com.zhl.card.util.Stringer;

@Controller
@RequestMapping(value = "/logUser")
public class LogUserController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(LogUserController.class);
	
	@Resource
	private ILogUserService logUserService;
	@Resource
	private ISmsService smsService;
	
	/**
     * 注册--生成短信验证码前查询此手机号是否注册过
     * @param model
     * @param request
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/createSmsCodeCheck")
    @ResponseBody
    public Object createSmsCodeCheck(String logMobile){
        
        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("--->发送短信---->开始...");
        logs.info("--->发送短信---->请求参数：keySn:" + keySn + ",mobile:" + logMobile);
        String check = checkCreateSmsCode(logMobile);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), check);
            logs.info("发送短信---->返回参数：" + resEntity.toString());
            return resEntity;
        }
        // 数据库验重,判断手机号是否注册过  
        List<LogUser> list = logUserService.queryLogUserByCondition(logMobile);
        if(null != list && list.size() > 0){
            resEntity.setResponseEntity(keySn, ReturnCode.OBJECT_ALREADY_EXIST_222222.getCode(), "手机号已经注册");
            logs.info("发送短信---->返回参数：" + resEntity.toString());
            return resEntity;
        }
        resEntity = smsService.sendSmsCode(keySn, logMobile);
        logs.info("发送短信---->返回参数：" + resEntity.toString());
        return resEntity;
    }
    
	/**
	 * 注册---设置密码，完成注册
	 * @param model
	 * @param request
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/register")
    @ResponseBody
    public Object register(LogUser logUser){
	    ResponseEntity resEntity = new ResponseEntity();
	    logs.info("--->注册生成用户信息---->开始...");
	    String keySn = KeySn.getKey();
	    logs.debug("请求参数：keySn=" + keySn + ",logMobile=" +logUser.getLogMobile()+ ",logPassword" + logUser.getLogPassword());
	    String keySnId = KeySn.getKey();
	    
	    String check = checkRegister(logUser);
	    if(null != check){
	        resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getName(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
	    }
	    // 数据库验重,判断手机号是否注册过  
        List<LogUser> list = logUserService.queryLogUserByCondition(logUser.getLogMobile());
        if(null != list && list.size() > 0){
            resEntity.setResponseEntity(keySn, ReturnCode.OBJECT_ALREADY_EXIST_222222.getCode(), "手机号已经注册");
            logs.info("发送短信---->返回参数：" + resEntity.toString());
            return resEntity;
        }
        String logUserId = new StringBuilder().append("L_").append(keySnId).toString();
        String userId = new StringBuilder().append("U_").append(keySnId).toString();
	    
	    int reInt = logUserService.setRegister(logUserId, userId, logUser);
	    if(reInt == 1){
	        resEntity.setResponseEntity(keySnId, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
	    } else {
	        resEntity.setResponseEntity(keySnId, ReturnCode.REGISTER_FAIL_200001.getCode(), ReturnCode.REGISTER_FAIL_200001.getName());
	    }
	    logs.info("--->注册生成用户信息---->结束...");
        return resEntity;
    }
	
	/**
	 * 登录
	 * @param logMobile
	 * @param logPassword
	 * @return
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(String logMobile, String logPassword){
	    ResponseEntity resEntity = new ResponseEntity();
	    String keySn = KeySn.getKey();
	    logs.info("用户登录开始.....");
	    logs.debug("请求参数：keySn=" + keySn + ",logMobile=" +logMobile+ ",logPassword" + logPassword);
	    
	    String check = checkLogin(logMobile, logPassword);
	    if(null != check){
	        resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getName(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
	        logs.info("发送短信---->返回参数：" + resEntity.toString());
	        return resEntity;
	    }
	    // 密码加密
	    logPassword = Stringer.encryptLogPwd(logPassword);
	    // 登录业务
	    Map<String, Object> map = logUserService.login(keySn, logMobile, logPassword);
	    if(map.isEmpty()){
	        resEntity.setResponseEntity(keySn, ReturnCode.LOGIN_FAIL_200002.getCode(), 
                    ReturnCode.LOGIN_FAIL_200002.getName(), map);
	    } else {
	        resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), 
	                ReturnCode.SUCCESS_000000.getName(), map);
	    }
	    logs.info("用户登录结束.....返回参数：" + resEntity);
	    return resEntity;
	}
	
	/**
     * 修改登录密码
     * @param logMobile
     * @param logPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Object updatePassword(String id, String logPassword, String newLogPassword, String reNewLogPassword){
        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("修改登录密码开始.....");
        logs.debug("修改登录密码请求参数：keySn=" + keySn + ",id=" +id+ ",logPassword" + logPassword+ ",newLogPassword" + newLogPassword+ ",reNewLogPassword" + reNewLogPassword);
        
        String check = checkUpdatePassword(id, logPassword, newLogPassword, reNewLogPassword);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getName(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("修改登录密码---->返回参数：" + resEntity.toString());
            return resEntity;
        }
        // 密码加密
        logPassword = Stringer.encryptLogPwd(logPassword);
        newLogPassword = Stringer.encryptLogPwd(newLogPassword);
        
        String returnStr = logUserService.updatePassword(keySn, id, logPassword, newLogPassword);
        if(null != returnStr && "1".equals(returnStr)){
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
        } else {
            resEntity.setResponseEntity(keySn, ReturnCode.FAIL_110110.getName(), ReturnCode.FAIL_110110.getName() + returnStr);
        }
        logs.info("修改登录密码结束.....返回参数：" + resEntity);
        return resEntity;
    }
	
    /**
     * 忘记密码--手机找回---发送短信
     * @param id
     * @return
     */
    @RequestMapping(value = "/sendSMSForgotPassword")
    @ResponseBody
    public Object sendSMSForgotPassword(String logMobile){
        
        String keySn = KeySn.getKey();
        logs.info("忘记密码--手机找回---->请求参数：keySn:" + keySn + ",logMobile=:" + logMobile);
        ResponseEntity resEntity = new ResponseEntity();
        
        String check = checkSendSMSForgotPassword(logMobile);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getName(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("忘记密码--手机找回---->返回参数：" + resEntity.toString());
            return resEntity;
        }
        
        // 数据库验重,判断手机号是否注册过  
        List<LogUser> list = logUserService.queryLogUserByCondition(logMobile);
        if(null != list && list.size() > 0){
            resEntity = smsService.sendSmsCode(keySn, logMobile);
        } else {
            resEntity.setResponseEntity(keySn, ReturnCode.MOBILE_NOT_FIND_111115.getCode(), ReturnCode.MOBILE_NOT_FIND_111115.getName());
        }
        logs.info("忘记密码--手机找回---->返回参数：" + resEntity.toString());
        return resEntity;
    }
    
    /**
     * 忘记密码--手机找回---对比验证码更改密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/forgotPassword")
    @ResponseBody
    public Object forgotPassword(String logMobile, String smsCode, String newLogPassword){
        
        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("忘记密码————修改登录密码...开始--->keySn=" + keySn + ",logMobile=" + logMobile + ",smsCode=" + smsCode + ",newPassword=" + newLogPassword);
        
        String check = checkForgotPassword(logMobile, smsCode, newLogPassword);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
       
        String reStr = smsService.compareSmsCode(keySn, logMobile, smsCode);
        if(null == reStr){
            // 数据库验重,判断手机号是否注册过  
            List<LogUser> list = logUserService.queryLogUserByCondition(logMobile);
            if(null != list && list.size() > 0){
                int returnInt = logUserService.forgotPassword(keySn, list.get(0).getId(), newLogPassword);
                if(returnInt == 1){
                    resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
                } else {
                    resEntity.setResponseEntity(keySn, ReturnCode.FAIL_110110.getName(), ReturnCode.FAIL_110110.getName());
                }
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.MOBILE_NOT_FIND_111115.getCode(), ReturnCode.MOBILE_NOT_FIND_111115.getName());
            }
        } else {
            resEntity.setResponseEntity(keySn, SmsCode.CODE200001.getCode(), reStr);
        }
        logs.info("忘记密码————修改登录密码...结束--->返回参数=" + resEntity.toString());
        return resEntity;
    }
	
	
	
	/*
	 * ==============================check==============================
	 */
	
	/**
	 * 注册发送验证码参数判断
	 * @return
	 */
	private String checkCreateSmsCode(String logMobile){
	    
	    if(null == logMobile || "".equals(logMobile.trim())){
	        return "手机号不能为空";
	    }
	    if(!Stringer.isMobile(logMobile)){
	        return "请填写正确的手机号";
	    }
	    return null;
	}
	
	/**
	 * 检查注册传入参数
	 * @return
	 */
	private String checkRegister(LogUser logUser){
	    
	    if(null != logUser){
	        if(null == logUser.getLogMobile() || "".equals(logUser.getLogMobile().trim())){
	            return "手机号不能为空";
	        }
	        if(null == logUser.getLogPassword() || "".equals(logUser.getLogPassword().trim())){
                return "密码不能为空";
            }
	    }
	    return null;
	}
	
	/**
	 * 登录参数验证
	 * @param logMobile       登录名
	 * @param logPassword     用户输入密码
	 * @return
	 */
	private String checkLogin(String logMobile, String logPassword){
	    if(StringUtils.isBlank(logMobile)){
	        return "用户名不能为空";
	    }
	    if(StringUtils.isBlank(logPassword)){
            return "密码不能为空";
        }
	    return null;
	}
	
	/**
	 * 修改密码参数验证
	 * @param id
	 * @param logPassword
	 * @param newLogPassword
	 * @param reNewLogPassword
	 * @return
	 */
	private String checkUpdatePassword(String id, String logPassword, String newLogPassword, String reNewLogPassword){
	    
	    if(StringUtils.isBlank(id)){
            return "id不能为空";
        }
	    if(StringUtils.isBlank(logPassword)){
            return "旧密码不能为空";
        }
	    if(StringUtils.isBlank(newLogPassword)){
            return "新密码不能为空";
        }
	    if(StringUtils.isBlank(reNewLogPassword)){
            return "新密码第二次输入不能为空";
        }
	    if(!newLogPassword.equals(reNewLogPassword)){
            return "新密码两次输入不同";
        }
	    return null;
	}
	
	/**
     * 检查忘记密码手机找回--发短信
     * @param id
     * @return
     */
    private String checkSendSMSForgotPassword(String logMobile){
        if(StringUtils.isBlank(logMobile)){
            return "手机号不能为空";
        }
        return null;
    }
    /**
     * 检查忘记密码手机找回--验证更改
     * @param id
     * @return
     */
    private String checkForgotPassword(String logMobile, String smsCode, String newPassword){
        if(StringUtils.isBlank(logMobile)){
            return "手机号不能为空";
        }
        if(StringUtils.isBlank(smsCode)){
            return "验证码不能为空";
        }
        if(StringUtils.isBlank(newPassword)){
            return "新密码不能为空";
        }
        return null;
    }
}
