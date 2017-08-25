package com.zhl.card.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.pub.util.memcached.MemcacheManager;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.card.controller.common.BaseController;
import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.CardType;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.pojo.enums.trans.State;
import com.zhl.card.service.ICardBinService;
import com.zhl.card.service.ICardInfoService;
import com.zhl.card.service.ICardValidResService;
import com.zhl.card.util.Constant;
import com.zhl.card.util.Stringer;

@Controller
@RequestMapping(value = "/cardInfo")
public class CardInfoController extends BaseController {

    private Logger logs = LoggerFactory.getLogger(CardInfoController.class);

    @Resource
    private ICardBinService cardBinService;
    @Resource
    private ICardInfoService cardInfoService;
    @Resource
    private ICardValidResService cardValidResService;

    /**
     * 绑定银行卡，并验证
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/bindCard")
    @ResponseBody
    public Object bindCard(CardInfo cardInfo) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.debug("##>>>bindcardPost get Request OK!");
        
        // 必填项检查，姓名、身份证信息、卡号、手机号、银行号、银行名称
        String check = checkbindCard(cardInfo);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        try {
            // 中文转码
            cardInfo.setCardHolderName(new String(cardInfo.getCardHolderName().getBytes("iso-8859-1"),"utf-8"));
            cardInfo.setBankName(new String(cardInfo.getBankName().getBytes("iso-8859-1"),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        List<CardInfo> CardInfoList = cardInfoService.getCardByUserIdAndCardNo(cardInfo);// unImpl
        if (!Stringer.isNullOrEmpty(CardInfoList)) {
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + "此卡已经绑定");
            return resEntity;
        }
        
        // 绑卡校验
        // 开发者模式不验证
        if (Stringer.isNullOrEmpty(Constant.DEV_SWITCH) || !Constant.DEV_SWITCH.equals("1")) {// 测试的时候可以用这个，不真正发短信，验证码看log
            logs.debug("##>>>bindcardPost 开发者模式不验证");
            int reInt = cardInfoService.insetCardInfo(cardInfo);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.BIND_CARD_FAIL_200004.getCode(), ReturnCode.BIND_CARD_FAIL_200004.getName());
            }
            return resEntity;
        }
        // 非开发者模式验证卡的有效性
        logs.debug("##>>>bindcardPost 非开发者模式验证卡的有效性");

        // 卡有效性验证
        Map<String, Object> returnMap = cardValidResService.invokeInterfaceValidCard(keySn, cardInfo);
        if(!returnMap.isEmpty() && ReturnCode.SUCCESS_000000.getCode().equals(returnMap.get("returnCode"))){
            int reInt = cardInfoService.insetCardInfo(cardInfo);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.BIND_CARD_DB_FAIL_200005.getCode(), ReturnCode.BIND_CARD_DB_FAIL_200005.getName(), returnMap);
            }
        } else {
            resEntity.setResponseEntity(keySn, State.ERROR_INTERFACE_05.getCode(), State.ERROR_INTERFACE_05.getName(), returnMap);
        }
        
        return resEntity;
    }
    
    /**
     * 绑定银行卡，并验证----补全卡信息（更改）
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/updateBindCard")
    @ResponseBody
    public Object updateBindCard(CardInfo cardInfo) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.debug("##>>>bindcardPost get Request OK!");
        
        // 必填项检查，id、姓名、身份证信息、卡号、手机号、银行号、银行名称
        String check = checkUpdateBindCard(cardInfo);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        
        List<CardInfo> CardInfoList = cardInfoService.getCardByUserIdAndCardNo(cardInfo);// unImpl
        if (!Stringer.isNullOrEmpty(CardInfoList)) {
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + "此卡已经绑定");
            return resEntity;
        }
        
        // 绑卡校验
        // 开发者模式不验证
        if (Stringer.isNullOrEmpty(Constant.DEV_SWITCH) || !Constant.DEV_SWITCH.equals("1")) {// 测试的时候可以用这个，不真正发短信，验证码看log
            logs.debug("##>>>bindcardPost 开发者模式不验证");
            int reInt = cardInfoService.updateCardInfoByBindSupply(cardInfo);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.BIND_CARD_FAIL_200004.getCode(), ReturnCode.BIND_CARD_FAIL_200004.getName());
            }
            return resEntity;
        }
        // 非开发者模式验证卡的有效性
        logs.debug("##>>>bindcardPost 非开发者模式验证卡的有效性");

        // 卡有效性验证
        Map<String, Object> returnMap = cardValidResService.invokeInterfaceValidCard(keySn, cardInfo);
        if(!returnMap.isEmpty() && ReturnCode.SUCCESS_000000.getCode().equals(returnMap.get("returnCode"))){
            int reInt = cardInfoService.updateCardInfoByBindSupply(cardInfo);
            if(reInt == 1){
                resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
            } else {
                resEntity.setResponseEntity(keySn, ReturnCode.BIND_CARD_DB_FAIL_200005.getCode(), ReturnCode.BIND_CARD_DB_FAIL_200005.getName(), returnMap);
            }
        } else {
            resEntity.setResponseEntity(keySn, State.ERROR_INTERFACE_05.getCode(), State.ERROR_INTERFACE_05.getName(), returnMap);
        }
        
        return resEntity;
    }
    
    /**
     * 解绑银行卡
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/unbindCard")
    @ResponseBody
    public Object unbindCard(String userId, String id) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.debug("解绑银行卡... 开始--->keySn = "+keySn);
        
        int reInt = cardInfoService.updateCardInfo(userId, id);
        if(reInt == 1){
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName());
        } else {
            resEntity.setResponseEntity(keySn, ReturnCode.FAIL_110110.getCode(), ReturnCode.FAIL_110110.getName());
        }
        logs.debug("解绑银行卡... 结束--->resEntity=" + resEntity);
        return resEntity;
    }
    
    /**
     * 查询用户卡列表
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/getCardList")
    @ResponseBody
    public Object getCardList(String userId, String cardType) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("查询用户卡列表...开始--->keySn = " + keySn + ",userId = " + userId);
        String check = checkGetCardList(userId);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        
        List<CardInfo> CardInfoList = cardInfoService.getCardByUserId(userId, cardType);
        if (Stringer.isNullOrEmpty(CardInfoList)) {
            resEntity.setResponseEntity(keySn, ReturnCode.CARD_NOT_FIND_111113.getCode(), ReturnCode.CARD_NOT_FIND_111113.getName());
        } else {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("CardInfoList", CardInfoList);
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
        }
        logs.info("查询用户卡列表...结束--->resEntity = " + resEntity);
        return resEntity;
    }
    
    
    /**
     * 查询用户下卡、账单、账单详情、账单明细
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/queryCardInfoAndBillInfoAll")
    @ResponseBody
    public Object queryCardInfoAndBillInfoAll(String logId, String userId) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.info("查询用户下卡、账单、账单详情、账单明细...开始--->keySn = " + keySn + ",userId = " + userId);
        String check = checkGetCardList(userId);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        
        List<CardInfo> CardInfoList = cardInfoService.getCardByUserId(userId, "02");
        if (Stringer.isNullOrEmpty(CardInfoList)) {
            resEntity.setResponseEntity(keySn, ReturnCode.CARD_NOT_FIND_111113.getCode(), ReturnCode.CARD_NOT_FIND_111113.getName());
        } else {
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("CardInfoList", CardInfoList);
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
        }
        logs.info("查询用户卡列表...结束--->resEntity = " + resEntity);
        return resEntity;
    }
    
    

    // =========================check==================

    /**
     * 检查绑卡参数
     * 
     * @param cardNo
     * @return
     */
    private String checkbindCard(CardInfo cardInfo) {
        
        if(null == cardInfo){
            return "请求参数不能为空";
        }
        if (StringUtils.isBlank(cardInfo.getCardHolderName())) {
            return "姓名不能为空";
        }
        if (StringUtils.isBlank(cardInfo.getBankName())){
            return "银行名称不能为空";
        }
        return checkBindCardUtil(cardInfo);
    }
    /**
     * 检查补全绑卡参数
     * 
     * @param cardNo
     * @return
     */
    private String checkUpdateBindCard(CardInfo cardInfo) {
        if(null == cardInfo){
            return "请求参数不能为空";
        }
        if (StringUtils.isBlank(cardInfo.getId())) {
            return "id不能为空";
        }
        return checkBindCardUtil(cardInfo);
    }
    /**
     * 绑卡公用检查类
     * @param cardInfo
     * @return
     */
    private String checkBindCardUtil(CardInfo cardInfo){
        if (StringUtils.isBlank(cardInfo.getCardType())) {
            return "卡类型不能为空";
        } else {
            if(CardType.CODE02.getCode().equals(cardInfo.getCardType())){
                
                if (StringUtils.isBlank(cardInfo.getCardCvv())) {
                    return "信用卡cvv不能为空";
                }
                if (StringUtils.isBlank(cardInfo.getValidityYear()) 
                        || !Stringer.isDigit(cardInfo.getValidityYear())
                        || cardInfo.getValidityYear().length() != 2) {
                    return "有效期（年）不能为空且为两位数字";
                }
                if (StringUtils.isBlank(cardInfo.getValidityMonth())
                        || !Stringer.isDigit(cardInfo.getValidityMonth())
                        || cardInfo.getValidityMonth().length() != 2) {
                    return "有效期（月）不能为空且为两位数字";
                }
            }
        }
       
        if (StringUtils.isBlank(cardInfo.getUserId())) {
            return "userId不能为空";
        }
        if (StringUtils.isBlank(cardInfo.getLogId())) {
            return "logId不能为空";
        }
        if (StringUtils.isBlank(cardInfo.getHeadBankNo())){
            return "银行号不能为空";
        }
        if (StringUtils.isBlank(cardInfo.getBindMobile())){
            return "手机号不能为空";
        } else {
            if(!Stringer.isMobile(cardInfo.getBindMobile())) {
                return "请填写正确手机号";
            }
        }
        if(StringUtils.isBlank(cardInfo.getCardNo())){
            return "银行卡号不能为空";
        } else {
            if (!Stringer.isDigit(cardInfo.getCardNo())) {
                return "请填写正确银行卡号";
            }
        }
//        if (!Stringer.isIDNo(cardInfo.getBindIdcard())) {
//            return "请填写正确身份证号";
//        }
        return null;
        
    }
    
    /**
     * 检查获取用户列表参数
     * @return
     */
    private String checkGetCardList(String userId){
        if(StringUtils.isBlank(userId)){
            return "userId不能为空";
        }
        return null;
    }

}
