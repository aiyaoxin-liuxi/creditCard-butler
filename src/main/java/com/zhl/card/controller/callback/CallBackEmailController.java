package com.zhl.card.controller.callback;

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

import com.zhl.card.controller.common.BaseController;
import com.zhl.card.pojo.EmailInfo;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.email.EmailAcceptState;
import com.zhl.card.service.IEmailBillInsertService;
import com.zhl.card.service.IEmailInfoService;
import com.zhl.card.service.impl.HCBillService;

@Controller
@RequestMapping(value = "/callBackEmail")
public class CallBackEmailController extends BaseController {

    private Logger logs = LoggerFactory.getLogger(CallBackEmailController.class);

    @Resource
    private IEmailInfoService emailInfoService;
    @Resource
    private IEmailBillInsertService emailBillInsertService;

    /**
     * 邮箱回调接口
     *      返回参数和结果需要入库，以后再弄
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/getCallBack")
    @ResponseBody
    public Object getCallBack(String sequence, String code) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        String ybId = sequence;
        logs.info("邮箱异步回调...开始--->keySn = " + keySn + ",ybId = " + ybId + ",code=" + code);
        
        String check = checkGetCallBack(ybId);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            logs.info("邮箱异步回调...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        EmailInfo emailInfo = emailInfoService.queryEmailByybId(ybId);
        if(null == emailInfo){
            resEntity.setResponseEntity(keySn, ReturnCode.EMAIL_NOT_FIND_111114.getCode(), ReturnCode.EMAIL_NOT_FIND_111114.getName());
            logs.debug("获取邮箱信息...结束--->resEntity = " + resEntity);
            return resEntity;
        }
        // 调用接口查询结果service
        logs.info("邮箱异步回调获取接口...准备--->keySn = " + keySn);
        String reStr = new HCBillService().queryBill(emailInfo.getToken(), ""); 
        logs.info("邮箱异步回调获取接口...完成--->keySn = " + keySn);
        logs.debug("返回：reStr = " + reStr);
        if(null == reStr || "".equals(reStr)){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_NULL_FAIL_200009.getCode(), ReturnCode.RETURN_NULL_FAIL_200009.getName());
            logs.info("邮箱异步回调获取接口...结束--->keySn = " + keySn + ",返回：resEntity = " + resEntity);
            return resEntity;
        }
        Map<String, Object> reMap = JsonUtil.getJsonToMap(reStr);
        if(!reMap.containsKey("code")){
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName());
            logs.info("邮箱异步回调获取接口...结束--->keySn = " + keySn + ",返回：resEntity = " + resEntity);
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
        } else {
            emailInfoService.updateEmailInfoAcceptStateByid(emailInfo.getId(), EmailAcceptState.CODE02.getCode());
            resEntity.setResponseEntity(keySn, ReturnCode.RETURN_FAIL_200010.getCode(), ReturnCode.RETURN_FAIL_200010.getName(), reStr);
        }
        
        
        logs.debug("邮箱异步回调...结束--->resEntity = " + resEntity);
        
        return resEntity;
    }
    
    // =========================check==================

    /**
     * 回调返回值
     * @param cid
     * @return
     */
    private String checkGetCallBack(String ybId){
        if(StringUtils.isBlank(ybId)){
            return "回调参数ybId为空";
        }
        return null;
    }

}
