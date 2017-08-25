package com.zhl.card.service;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.CardBin;

public interface ICardBinService {
	
	/**
	 * 查询所有卡bin信息
	 * @return
	 */
	List<CardBin> queryCardBinAll();
	
	/**
	 * 根据卡号从卡bin中获取银行名称
	 * @param cardNo
	 * @param map
	 * @return
	 */
	CardBin getBankInfo(String cardNo, Map<String, Object> map);
	
	/**
	 * init将卡bin放入缓存
	 */
	Map<String, Object> cardBinInit();

}
