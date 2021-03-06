package com.zhl.card.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.pub.util.memcached.MemcacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.ICardBinDao;
import com.zhl.card.pojo.CardBin;
import com.zhl.card.service.ICardBinService;
import com.zhl.card.util.Constant;
import com.zhl.card.util.Stringer;

@Service("cardBinService")
public class CardBinService implements ICardBinService {
	
    private Logger logs = LoggerFactory.getLogger(CardBinService.class);
    
	@Resource
	private ICardBinDao cardBinDao;

	@Override
	public List<CardBin> queryCardBinAll() {
		return cardBinDao.queryCardBinAll();
	}
	
	@Override
    public Map<String, Object> cardBinInit() {
	    Map<String, Object> carBinMap = new HashMap<String, Object>();
        List<CardBin> cardBinList = cardBinDao.queryCardBinAll();
        if(null != cardBinList && cardBinList.size() > 0){
            for (CardBin cardBin : cardBinList) {
                carBinMap.put(cardBin.getCardBin(), cardBin);
            }
            
            MemcacheManager.getInstance().add(Constant.CARD_BIN, carBinMap);
        }
        return carBinMap;
    }
	
    
	@Override
	public CardBin getBankInfo(String cardNo, Map<String, Object> map) {
        if(Stringer.isNullOrEmpty(cardNo)){
            return null;
        }
        List<String> list = new ArrayList<String>();
        
        list.add(StringUtils.substring(cardNo, 0, 6));
        list.add(StringUtils.substring(cardNo, 0, 7));
        list.add(StringUtils.substring(cardNo, 0, 8));
        list.add(StringUtils.substring(cardNo, 0, 9));
        list.add(StringUtils.substring(cardNo, 0, 10));
        
        CardBin cardBin = null;
        for(String key : list){
            Object object = map.get(key);
            if(!Stringer.isNullOrEmpty(object)){
                cardBin = (CardBin) object;
                break;
            }
        }
        return cardBin;
    }
	

}
