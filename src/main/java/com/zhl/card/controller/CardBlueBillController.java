package com.zhl.card.controller;

import java.util.HashMap;
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
import com.zhl.card.pojo.CardBlueBill;
import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.service.ICardBlueBillService;

@Controller
@RequestMapping(value = "/cardBlueBill")
public class CardBlueBillController extends BaseController {

    private Logger logs = LoggerFactory.getLogger(CardBlueBillController.class);

    @Resource
    private ICardBlueBillService cardBlueBillService;

    /**
     * 查询用户下所有账单————按条件
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/queryBill")
    @ResponseBody
    public Object queryBill(CardBlueBill cardBlueBill) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.debug("查询用户账单...开始--->keySn=" + keySn + ",参数=" + cardBlueBill.toString());
        
        String check = checkQueryBill(cardBlueBill);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        List<CardBlueBill> cardBlueBillList = cardBlueBillService.queryBillConditionByUserId(cardBlueBill);
        if(null != cardBlueBillList && cardBlueBillList.size() > 0){
            
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("cardBlueBillList", cardBlueBillList);
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
            
        } else {
            resEntity.setResponseEntity(keySn, ReturnCode.BLUE_BILL_NOT_FIND_111116.getCode(), ReturnCode.BLUE_BILL_NOT_FIND_111116.getName());
        }
        logs.debug("查询用户账单...结束--->keySn=" + keySn + ",返回=" + resEntity);
        return resEntity;
    }
    
    /**
     * 查询用户下所有卡的所有账单
     * @param cardInfo
     * @return
     */
    @RequestMapping(value = "/queryBillByCardAll")
    @ResponseBody
    public Object queryBillByCardAll(String logId, String userId, String cardId) {

        ResponseEntity resEntity = new ResponseEntity();
        String keySn = KeySn.getKey();
        logs.debug("查询用户下所有卡的所有账单...开始--->keySn=" + keySn + ",logId=" + logId + ",userId=" + userId);
        
        String check = checkQueryBillByCardAll(logId, userId);
        if(null != check){
            resEntity.setResponseEntity(keySn, ReturnCode.DATA_VALID_FAIL_200000.getCode(), ReturnCode.DATA_VALID_FAIL_200000.getName() + check);
            return resEntity;
        }
        List<CardInfo> cardInfoList = cardBlueBillService.queryCardInfoAndBillInfoAll(logId, userId, cardId);
        if(null != cardInfoList && cardInfoList.size() > 0){
            Map<String, Object> returnMap = new HashMap<String, Object>();
            returnMap.put("cardInfoList", cardInfoList);
            resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), returnMap);
        } else {
            resEntity.setResponseEntity(keySn, ReturnCode.CARD_NOT_FIND_111113.getCode(), ReturnCode.CARD_NOT_FIND_111113.getName());
        }
        logs.debug("查询用户下所有卡的所有账单...结束--->keySn=" + keySn + ",返回=" + resEntity);
        return resEntity;
    }
    
    /**
     * 检查获取用户列表参数
     * @return
     */
    private String checkQueryBill(CardBlueBill cardBlueBill){
        
        if(null == cardBlueBill){
            return "获取不到查询参数";
        }
        if(StringUtils.isBlank(cardBlueBill.getLogId())){
            return "userId不能为空";
        }
        if(StringUtils.isBlank(cardBlueBill.getUserId())){
            return "userId不能为空";
        }
        return null;
    }
    /**
     * 检查查询用户下所有卡的所有账单参数
     * @return
     */
    private String checkQueryBillByCardAll(String logId, String userId){
        
        if(StringUtils.isBlank(logId)){
            return "logId不能为空";
        }
        if(StringUtils.isBlank(userId)){
            return "userId不能为空";
        }
        return null;
    }

}
