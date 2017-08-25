package com.zhl.card.pay;

import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.zhl.card.pojo.CardInfo;
/**
 * 	定义支付接口
  * @ClassName: PayInterface
  * @author zhaotisheng	
  * @date 2017年3月20日 下午3:43:28
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public interface IPay {

	/**
	 * 快捷支付第一步发送短信
	 */
    Map<String, Object> sendSmsCode(Object[] objArr);
	/**
	 * 第二步支付确认
	 */
    Map<String, Object> payConfirm(Object[] objArr);
	/**
	 * 绑卡校验 有效性验证
	 */
    Map<String, Object> validCard(CardInfo cardInfo);
}
