package com.zhl.card.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.memcached.MemcacheManager;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.ICardBlueBillDao;
import com.zhl.card.dao.ICardInfoDao;
import com.zhl.card.pojo.CardBin;
import com.zhl.card.pojo.CardInfo;
import com.zhl.card.pojo.enums.cardInfo.BindState;
import com.zhl.card.pojo.enums.cardInfo.CardInfoState;
import com.zhl.card.pojo.enums.cardInfo.CardInfoType;
import com.zhl.card.pojo.enums.common.IsDel;
import com.zhl.card.service.ICardBinService;
import com.zhl.card.service.ICardInfoService;
import com.zhl.card.util.Constant;
import com.zhl.card.util.Stringer;

@Service("cardInfoService")
public class CardInfoService implements ICardInfoService {
	
    private Logger logs = LoggerFactory.getLogger(CardInfoService.class);
    
    @Resource
    private ICardInfoDao cardInfoDao;
    @Resource
    private ICardBlueBillDao cardBlueBillDao;
    @Resource
    private ICardBinService cardBinService;

    @Override
    public List<CardInfo> getCardByUserIdAndCardNo(CardInfo cardInfo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", cardInfo.getUserId());
        paramMap.put("cardNo", cardInfo.getCardNo());
        paramMap.put("idDel", IsDel.CODE00.getCode());
        return cardInfoDao.queryCardInfoCondition2List(paramMap);
    }
    
    @Override
    public List<CardInfo> getCardByUserId(String userId, String cardType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        if(null != cardType && !"".equals(cardType)){
            paramMap.put("cardType", cardType);
        }
        paramMap.put("idDel", IsDel.CODE00.getCode());
        return cardInfoDao.queryCardInfoCondition2List(paramMap);
    }
    
    @Override
    public int insetCardInfo(CardInfo cardInfo) {
        Date now = new Date();
        cardInfo.setId(KeySn.getKey());
        cardInfo.setBindDate(now);
        cardInfo.setCreateDate(now);
        cardInfo.setBindState(BindState.CODE00.getCode());
        cardInfo.setType(CardInfoType.CODE00.getCode());
        cardInfo.setState(CardInfoState.CODE00.getCode());
        cardInfo.setIsDel(IsDel.CODE00.getCode());
        return cardInfoDao.insertCardInfo(cardInfo);
    }

    @Override
    public int updateCardInfo(String userId, String id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);
        paramMap.put("userId", userId);
        paramMap.put("idDel", IsDel.CODE00.getCode());
        List<CardInfo> list = cardInfoDao.queryCardInfoCondition2List(paramMap);
        if(null != list && list.size() == 1){
            paramMap.put("id", id);
            paramMap.put("isDel", IsDel.CODE01.getCode());
            return cardInfoDao.updateCardInfo(paramMap);
        } else {
            return 0;
        }
    }
    
    @Override
    public int updateCardInfoByBindSupply(CardInfo cardInfo) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", cardInfo.getId());
        List<CardInfo> list = cardInfoDao.queryCardInfoCondition2List(paramMap);
        if(null != list && list.size() == 1){
            
            Object object = MemcacheManager.getInstance().get(Constant.CARD_BIN);
            if(Stringer.isNullOrEmpty(object)){
                object = cardBinService.cardBinInit();
            }
            CardBin cardBin = cardBinService.getBankInfo(cardInfo.getCardNo(), (Map<String, Object>) object);
            paramMap.put("cardNo", cardInfo.getCardNo());
            paramMap.put("bindDate", new Date());
            paramMap.put("bindMobile", cardInfo.getBindMobile());
            paramMap.put("bindIdcard", cardInfo.getBindIdcard());
            paramMap.put("bindState", BindState.CODE00.getCode());
            paramMap.put("validityYear", cardInfo.getValidityYear());
            paramMap.put("validityMonth", cardInfo.getValidityMonth());
            paramMap.put("cardCvv", cardInfo.getCardCvv());
            if(null != cardBin){
                paramMap.put("headBankNo", cardBin.getBankinfoId());
            }
            paramMap.put("idDel", IsDel.CODE00.getCode());
            return cardInfoDao.updateCardInfo(paramMap);
        } else {
            return 0;
        }
    }

}
