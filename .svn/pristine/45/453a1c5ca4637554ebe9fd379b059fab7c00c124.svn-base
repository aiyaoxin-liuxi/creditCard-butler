package com.zhl.card.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.pub.util.memcached.MemcacheManager;

import com.zhl.card.pojo.BankInfo;
import com.zhl.card.pojo.CardBin;
import com.zhl.card.service.IBankInfoService;
import com.zhl.card.service.ICardBinService;
import com.zhl.card.util.Constant;

/**
 * 启动初始化类
 * @author 刘熙
 */
public class CardInit {
    
    @Resource
    private IBankInfoService bankInfoService;
    @Resource
    private ICardBinService cardBinService;
	
	private Logger logger = Logger.getLogger(CardInit.class);
	
	public void initMethod() throws Exception {  
		logger.info("启动执行---start");
        
//		logger.info("开始加载省市区信息...start");
//        Map<String, Object> map = regionService.queryAll();
//		Map<String, Object> cMap = (Map<String, Object>) map.get("cityMap");
//		Map<String, Object> aMap = (Map<String, Object>) map.get("areaMap");
//        MemcacheManager.getInstance().add(Constant.CITY_MAP, cMap);
//        MemcacheManager.getInstance().add(Constant.AREA_MAP, aMap);
//        logger.info("省市区信息加载完成...end");
//        
        logger.info("开始加载银行信息...start");
        bankInfoService.bankInit();
        logger.info("银行信息加载完成...end");
        
        logger.info("开始加载卡bin信息...start");
        cardBinService.cardBinInit();
        logger.info("卡bin信息加载完成...end");
        
        
        logger.info("启动执行---end");
    }  

}
