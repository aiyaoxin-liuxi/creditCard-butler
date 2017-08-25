package com.zhl.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.pub.util.json.JsonUtil;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.zhl.card.controller.common.BaseController;
import com.zhl.card.pojo.EmailInfo;
import com.zhl.card.pojo.UserInfo;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.email.EmailAcceptState;
import com.zhl.card.pojo.enums.trans.State;
import com.zhl.card.service.IEmailBillInsertService;
import com.zhl.card.service.IEmailInfoService;
import com.zhl.card.service.IHCBillService;
import com.zhl.card.service.IUserInfoService;
import com.zhl.card.service.impl.HCBillService;

@Controller
@RequestMapping(value = "/emailInfo")
public class EmailInfoController extends BaseController {

    private Logger logs = LoggerFactory.getLogger(EmailInfoController.class);

    @Resource
    private IEmailInfoService emailInfoService;
    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private IEmailBillInsertService emailBillInsertService;

    /**
     * 增加邮箱,并直接获取邮箱账单
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/addAndQueryEmailInfo")
    @ResponseBody
    public Object addAndQueryEmailInfo(EmailInfo emailInfo) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("添加邮箱...开始--->keySn = " + keySn + ",logId = " + emailInfo.getLogId() + 
                ",userId = " + emailInfo.getUserId() + ",email = " + emailInfo.getEmail() + 
                ",emailPassword = " + emailInfo.getEmailPassword());
        
        // 必填项检查，姓名、身份证信息、卡号、手机号、银行号
        String check = checkAddAndQueryEmailInfo(emailInfo);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("添加邮箱...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        List<UserInfo> list = userInfoService.queryEmailByLogIdAndUserId(emailInfo.getLogId(), emailInfo.getUserId());
        if(null == list && list.size() == 0){
            resEntity.setResponseEntity(keySn, ReturnCode.OBJECT_NOT_FIND_111111.getCode(), ReturnCode.OBJECT_NOT_FIND_111111.getName());
            logs.info("添加邮箱...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        
//        BASE64Encoder encoder = new BASE64Encoder();
//        emailInfo.setEmailPassword(encoder.encode(emailInfo.getEmailPassword().getBytes()));
        
        logs.info("添加邮箱...准备组装数据入库--->keySn = " + keySn + ",emailInfo = " + emailInfo);
        // email信息入库
        Map<String, Object> map = emailInfoService.addEmailInfo(keySn, emailInfo);
        if(map.isEmpty()){
            resEntity.setResponseEntity(keySn, ReturnCode.INSERT_FAIL_200007.getCode(), ReturnCode.INSERT_FAIL_200007.getName());
            logs.info("添加邮箱...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        if(!ReturnCode.SUCCESS_000000.getCode().equals(map.get("returnCode"))){
            resEntity.setResponseEntity(keySn, (String)map.get("returnCode"), (String)map.get("reason"));
            logs.info("添加邮箱...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        
        // token和ybid入库
        String id = (String) map.get("data");
        String token = "";
        String ybId = KeySn.getKey();
        
        // 直接调用获取邮箱账单
        logs.info("调用邮箱账单获取接口...准备--->keySn = " + keySn);
//        hs.getBillByEmail("12345678909","feng283669854@163.com", "xw112126", "email", "ICBC|CCB|CMB","","");
        String reStr = new HCBillService().getBillByEmail(ybId, emailInfo.getEmail(), emailInfo.getEmailPassword(), "email", "ALL", "", ""); 
        //{"code":"0010","msg":"受理成功","token":"2883b653b914489f8a66bab7894f7366","data":""}
        logs.info("调用邮箱账单获取接口...完成--->keySn = " + keySn + "返回：reStr = " + reStr);
        
        if(null == reStr || "".equals(reStr)){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_NULL_FAIL_200009.getCode(), ReturnCode.RETURN_NULL_FAIL_200009.getName());
            logs.info("添加邮箱...结束--->keySn = " + keySn + "返回：resEntity = " + resEntity);
            return resEntity;
        }
        Map<String, Object> reMap = JsonUtil.getJsonToMap(reStr);
        if(!reMap.containsKey("code") || !reMap.containsKey("token")){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName());
            logs.info("添加邮箱...结束--->keySn = " + keySn + "返回：resEntity = " + resEntity);
            return resEntity;
        }
        
        if("0010".equals(reMap.get("code")) && !"".equals(reMap.get("token"))){
            token = (String) reMap.get("token");
            int reInt = emailInfoService.updateEmailInfoTokenAndybIdByid(token, ybId, id);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.UPDATE_FAIL_200008.getCode(), ReturnCode.UPDATE_FAIL_200008.getName());
            }
        } else {
            emailInfoService.updateEmailInfoAcceptStateByid(id, EmailAcceptState.CODE01.getCode());
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName(), reStr);
        }
        
        logs.debug("添加邮箱...结束--->resEntity = " + resEntity);
        
        return resEntity;
    }
    
    /**
     * 获取邮箱账单（根据id）
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/queryEmailInfo")
    @ResponseBody
    public Object queryEmailInfo(EmailInfo emailInfo) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("获取邮箱信息...开始--->keySn = " + keySn + ",id = " + emailInfo.getId());
         
        String check = checkQueryEmailInfo(emailInfo);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("获取邮箱信息...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        // 根据ID获取邮箱信息
        EmailInfo eInfo = emailInfoService.queryEmailInfoById(emailInfo.getId());
        if(null == eInfo){
            resEntity.setResponseEntity(keySn, ReturnCode.EMAIL_NOT_FIND_111114.getCode(), ReturnCode.EMAIL_NOT_FIND_111114.getName());
            logs.info("获取邮箱信息...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        
        // token和ybid入库
        String id = eInfo.getId();
        String token = "";
        String ybId = KeySn.getKey();
        
        // 直接调用获取邮箱账单
        logs.info("调用邮箱账单获取接口...准备--->keySn = " + keySn);
//        hs.getBillByEmail("12345678909","feng283669854@163.com", "xw112126", "email", "ICBC|CCB|CMB","","");
        String reStr = new HCBillService().getBillByEmail(ybId, eInfo.getEmail(), eInfo.getEmailPassword(), "email", "ALL", "", ""); 
        //{"code":"0010","msg":"受理成功","token":"2883b653b914489f8a66bab7894f7366","data":""}
        logs.info("调用邮箱账单获取接口...完成--->keySn = " + keySn + "返回：reStr = " + reStr);
        
        if(null == reStr || "".equals(reStr)){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_NULL_FAIL_200009.getCode(), ReturnCode.RETURN_NULL_FAIL_200009.getName());
            logs.info("添加邮箱...结束--->keySn = " + keySn + "返回：resEntity = " + resEntity);
            return resEntity;
        }
        Map<String, Object> reMap = JsonUtil.getJsonToMap(reStr);
        if(!reMap.containsKey("code") || !reMap.containsKey("token")){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName());
            logs.info("添加邮箱...结束--->keySn = " + keySn + "返回：resEntity = " + resEntity);
            return resEntity;
        }
        
        
        if("0010".equals(reMap.get("code")) || !"".equals(reMap.get("token"))){
            token = (String) reMap.get("token");
            int reInt = emailInfoService.updateEmailInfoTokenAndybIdByid(token, ybId, id);
            if(reInt == 1){
                emailInfoService.updateEmailInfoAcceptStateByid(id, EmailAcceptState.CODE00.getCode());
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.UPDATE_FAIL_200008.getCode(), ReturnCode.UPDATE_FAIL_200008.getName());
            }
        } else {
            emailInfoService.updateEmailInfoAcceptStateByid(id, EmailAcceptState.CODE01.getCode());
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName(), reStr);
        }
        
        logs.debug("添加邮箱...结束--->resEntity = " + resEntity);
        
        return resEntity;
    }
    
    /**
     * 获取已绑定邮箱
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/getBindEmail")
    @ResponseBody
    public Object getBindEmail(String logId, String userId) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.debug("获取已绑定邮箱...开始--->keySn = " + keySn + ",logId = " + logId + ",userId=" + userId);
         
        String check = checkGetBindEmail(logId, userId);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.debug("获取已绑定邮箱...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        
        List<EmailInfo> emailList = emailInfoService.getBindEmail(logId, userId);
        if(null != emailList && emailList.size() > 0){
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("EmailInfoList", emailList);
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
        } else {
            resEntity.setResponseEntity(keySn, ReturnCode.EMAIL_NOT_FIND_111114.getCode(), ReturnCode.EMAIL_NOT_FIND_111114.getName());
        }
        logs.debug("获取已绑定邮箱...结束--->resEntity = " + resEntity);
        
        return resEntity;
    }
    
    
    /**
     * 邮箱账单信息主动查询接口
     *      返回参数和结果需要入库，以后再弄
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/getEmailBill")
    @ResponseBody
    public Object getEmailBill(String emailId) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("邮箱账单结果查询...开始--->keySn = " + keySn + ",emailId = " + emailId);
        
        String check = checkGetEmailBill(emailId);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getName(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("邮箱账单结果查询...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        EmailInfo emailInfo = emailInfoService.queryEmailInfoById(emailId);
        if(null == emailInfo){
            resEntity.setResponseEntity(keySn, ReturnCode.EMAIL_NOT_FIND_111114.getName(), ReturnCode.EMAIL_NOT_FIND_111114.getName());
            logs.debug("邮箱账单结果查询...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        // 调用接口查询结果service
        logs.info("邮箱账单结果查询接口...准备--->keySn = " + keySn);
        String reStr = new HCBillService().queryBill(emailInfo.getToken(), ""); 
        logs.info("邮箱账单结果查询接口...完成--->keySn = " + keySn);
        logs.debug(",返回：reStr = " + reStr);
        if(null == reStr || "".equals(reStr)){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_NULL_FAIL_200009.getCode(), ReturnCode.RETURN_NULL_FAIL_200009.getName());
            logs.info("邮箱账单结果查询接口...结束--->keySn = " + keySn + ",返回：resEntity = " + resEntity);
            return resEntity;
        }
        Map<String, Object> reMap = JsonUtil.getJsonToMap(reStr);
        if(!reMap.containsKey("code")){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName());
            logs.info("邮箱账单结果查询接口...结束--->keySn = " + keySn + ",返回：resEntity = " + resEntity);
            return resEntity;
        }
        
        // 解析返回
        if("0000".equals(reMap.get("code"))){
            Map<String, Object> retMap = emailBillInsertService.addEmailAndInsertBill(emailInfo.getLogId(), emailInfo.getUserId(), reStr);
            if(!retMap.isEmpty() && ReturnCode.SUCCESS_000000.getCode().equals(retMap.get("returnCode"))){
                emailInfoService.updateEmailInfoAcceptStateByid(emailInfo.getId(), EmailAcceptState.CODE04.getCode());
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
            } else {
                resEntity.setResponseEntity(keySn, (String)retMap.get("returnCode"), (String)retMap.get("reason"));
            }
        } else if("1101".equals(reMap.get("code")) || "1102".equals(reMap.get("code")) || "1103".equals(reMap.get("code"))){
            emailInfoService.updateEmailInfoAcceptStateByid(emailInfo.getId(), EmailAcceptState.CODE03.getCode());
            resEntity.setResponseEntity(keySn, ReturnCode.EMAIL_LOGIN_FAIL_200011.getCode(), ReturnCode.EMAIL_LOGIN_FAIL_200011.getName());
        } else if ("1115".equals(reMap.get("code"))){
            // 主动查询的查询结果如果是1115则不作处理，等待异步通知
            resEntity.setResponseEntity(keySn, ReturnCode.WAIT_BILL_111117.getCode(), ReturnCode.WAIT_BILL_111117.getName(), reStr);
        }else {
            emailInfoService.updateEmailInfoAcceptStateByid(emailInfo.getId(), EmailAcceptState.CODE02.getCode());
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName(), reStr);
        }
        
        
        logs.debug("邮箱账单结果查询...结束--->resEntity = " + resEntity);
        
        return resEntity;
    }
    
    @RequestMapping(value="/updateEmailPassWord")
    @ResponseBody
    public Object updateEmailPassWord(String logId, String userId, String emailId, String email, String emailPassword){
        
        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("修改邮箱密码...开始--->keySn="+keySn+",logId="+logId+",userId="+userId+",emailId="+emailId+",emailPassword="+emailPassword);
        
        String check = checkUpdateEmailPassWord(logId, userId, emailId, email, emailPassword);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getName(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("修改邮箱密码...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        List<EmailInfo> emailList= emailInfoService.queryEmailInfoByIdAndUserId(logId, userId, emailId);
        if(null != emailList && emailList.size() == 1){
            
            int reInt = emailInfoService.updateEmailPassWord(emailId, email, emailPassword);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.UPDATE_FAIL_200008.getCode(), ReturnCode.UPDATE_FAIL_200008.getName());
            }
        } else {
            // 邮箱数错误
            resEntity.setResponseEntity(keySn, ReturnCode.EMAIL_INFO_FAIL_200012.getCode(), ReturnCode.EMAIL_INFO_FAIL_200012.getName());
        }
        logs.info("修改邮箱密码...结束--->keySn="+keySn+",resEntity="+resEntity);
        return resEntity;
    }
    
    
    // =========================check==================

    /**
     * 
     * 
     * @param cardNo
     * @return
     */
    private String checkAddAndQueryEmailInfo(EmailInfo emailInfo) {
        
        if(null == emailInfo){
            return "参数不能为空";
        }
        if (StringUtils.isBlank(emailInfo.getLogId())) {
            return "登录id不能为空";
        }
        if (StringUtils.isBlank(emailInfo.getUserId())) {
            return "userId不能为空";
        }
        if (StringUtils.isBlank(emailInfo.getEmail())) {
            return "邮箱不能为空";
        }
        if (StringUtils.isBlank(emailInfo.getEmailPassword())) {
            return "邮箱密码不能为空";
        }
        return null;
    }

    private String checkQueryEmailInfo(EmailInfo emailInfo) {
        
        if(null == emailInfo){
            return "参数不能为空";
        }
        if (StringUtils.isBlank(emailInfo.getId())) {
            return "id不能为空";
        }
        return null;
    }
    
    private String checkGetBindEmail(String LogId, String userId) {
        
        if(StringUtils.isBlank(LogId)){
            return "logId不能为空";
        }
        if (StringUtils.isBlank(userId)) {
            return "userId不能为空";
        }
        return null;
    }
    
    /**
     * 查询
     * @param cid
     * @return
     */
    private String checkGetEmailBill(String emailId){
        if(StringUtils.isBlank(emailId)){
            return "emailId不能为空";
        }
        return null;
    }
    /**
     * 修改邮箱密码参数判定
     * @param cid
     * @return
     */
    private String checkUpdateEmailPassWord(String logId, String userId, String emailId, String email, String passWord){
        if(StringUtils.isBlank(logId)){
            return "logId不能为空";
        }
        if(StringUtils.isBlank(userId)){
            return "userId不能为空";
        }
        if(StringUtils.isBlank(emailId)){
            return "emailId不能为空";
        }
        if(StringUtils.isBlank(email)){
            return "email不能为空";
        }
        if(StringUtils.isBlank(passWord)){
            return "passWord不能为空";
        }
        return null;
    }
    

}
