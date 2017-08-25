package com.zhl.card.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.ICardValidResDao;
import com.zhl.card.pay.IPay;
import com.zhl.card.pay.common.PayFactory;
import com.zhl.card.pay.common.PayFactoryEnum;
import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.CardValidRes;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.service.ICardValidResService;
import com.zhl.card.util.JsonUtil;

@Service("cardValidResService")
public class CardValidResService implements ICardValidResService {
	
    private Logger logs = LoggerFactory.getLogger(CardValidResService.class);
    
    @Resource
    private ICardValidResDao cardValidResDao;
    
    @Override
    public int insertOne(CardValidRes cardValidRes){
        return cardValidResDao.insertOne(cardValidRes);
    }
    
    @Override
    public Map<String, Object> invokeInterfaceValidCard(String keySn, CardInfo cardInfo) {
        logs.info("进入卡有效性验证...开始--->keySn=" + keySn + "cardInfo=" + JsonUtil.toJson(cardInfo));
        // ***************调用银行加密方法组装银行参数***********************
        IPay payInstance = new PayFactory().getInstance(PayFactoryEnum.CARDVALID_KAOLA.getCode());
        Map<String, Object> map = payInstance.validCard(cardInfo);
        //save db-------------------
        saveValidRes2Db(keySn, cardInfo, map);
        logs.info("进入卡有效性验证...结束--->keySn=" + keySn + "map=" + JsonUtil.toJson(map));
        return map;
    }
    
    /**
     * 写入银行卡有效性验证表
     * @param keySn
     * @param cardInfo
     * @param map
     */
    private void saveValidRes2Db(String keySn, CardInfo cardInfo, Map<String, Object> map) {
        logs.info("卡有效性验证...接口结果入库开始--->keySn=" + keySn + "cardInfo=" + JsonUtil.toJson(cardInfo) + "接口返回map=" + JsonUtil.toJson(map));
        CardValidRes cardValidRes = new CardValidRes();
        cardValidRes.setCid(KeySn.getKey());
        cardValidRes.setCreatedate(new Date());
        cardValidRes.setUserId(cardInfo.getUserId());
        cardValidRes.setIsDel(IsDel.CODE00.getCode());
        if(map.isEmpty() || !map.containsKey("returnCode")){
            cardValidRes.setRes(0);
        } else {
            if(ReturnCode.SUCCESS_000000.getCode().equals(map.get("returnCode"))){
                cardValidRes.setRes(1);
            } else {
                cardValidRes.setRes(0);
            }
        }
        cardValidRes.setUploadStr((String)map.get("uploadStr"));
        cardValidRes.setReturnStr((String)map.get("returnStr"));
        String str = "";
        int reInt = cardValidResDao.insertOne(cardValidRes);
        if(reInt == 1){
            str = "写入成功";
        } else {
            str = "写入失败";
        }
        logs.info("卡有效性验证...接口结果入库结束--->keySn=" + keySn + "写入结果：" + str);
    }

}
