//package com.zhl.card.pay.impl;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.smartcardio.Card;
//
//import org.pub.util.security.MessageSecurity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.zhl.card.pay.common.BasePay;
//import com.zhl.card.pojo.CardInfo;
//import com.zhl.card.pojo.common.ResponseEntity;
//import com.zhl.card.pojo.enums.common.ReturnCode;
//import com.zhl.card.pojo.enums.trans.State;
//import com.zhl.card.util.AmountUtil;
//import com.zhl.card.util.Constant;
//import com.zhl.card.util.JsonUtil;
//import com.zhl.card.util.Stringer;
//import com.zhl.card.util.http.HttpClientHelper;
///**
// * 	收银台支付实现类
//  * @ClassName: CashierPay
//  * @author zhaotisheng	
//  * @date 2017年3月20日 下午3:46:15
//  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
// */
//public class CashierPay extends BasePay {
//	
//	private Logger logs = LoggerFactory.getLogger(CashierPay.class);
//
//	public static final String enkey="cashier";//收银台的key
//	public static final String webyurl=Constant.CASHIER_WEBYURL;
//	
//	
//	
//	
//	//Object[] objArr = new Object[]{amount,card,res};
//	/**
//	 * 发送短信
//	 */
//	public Map<String, Object> sendSmsCode(Object[] objArr) {
//	    
//	    Map<String, Object> returnMap = new HashMap<String, Object>();
//		logs.info("##>>> 发送短信验证码..start ");
//		
//		double amount =(double) objArr[0];Card card=(Card) objArr[1];
//		ResponseEntity res=(ResponseEntity) objArr[2];
//		Accountflow accountflow=(Accountflow) objArr[3];
//		Map<String, String> paramMap = getParamMap(amount,card,accountflow);
//		//RECHARGE_URL
//		String sign = MessageSecurity.getMapMessageSecurity(paramMap,enkey);
//		paramMap.put("sign", sign);
//		StringBuilder json = JsonUtil.toJson(paramMap);
//		String url = Constant.RECHARGE_URL;
//		logs.info(url+" <--上送的url，【发送短信验证码】上送的param："+json.toString());
//		String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json.toString(), "60000","application/json","");
//		if(Stringer.isNullOrEmpty(o)){
//			logs.info(" 【发送短信验证码】返回的结果：\r\n\t"+State.ERROR_INTERFACE_NODATA_08.getName());
//			returnMap.put("returnCode", State.ERROR_INTERFACE_NODATA_08.getCode());
//			returnMap.put("returnStr", State.ERROR_INTERFACE_NODATA_08.getName());
//			return returnMap;
//		}
//		logs.info(" 【发送短信验证码】返回的结果：\r\n\t"+o);
//		Map<String, Object> resMap = new HashMap<String, Object>();
//		resMap = JsonUtil.toMap(new StringBuilder(o));
//		String returnCode = (String) resMap.get("returnCode");
//		if(returnCode.toString().equals("999999")){
//		    returnMap.put("returnCode", "999999");
//		    returnMap.put("returnCode", State.ERROR_INTERFACE_05.getName());
//            return returnMap;
//        }
//		Object returnMsg = resMap.get("returnMsg");
//		
//		returnMap.put("returnCode", returnCode);
//        returnMap.put("returnStr", returnMsg);
//		return returnMap;
//	}
//
//	private Map<String, String> getParamMap(double amount, CardInfo cardInfo, Accountflow accountflow) {
//		Map<String, String> map = new HashMap<String,String>();
//		map.put("certType", "01");
//		map.put("accName", cardInfo.getCardHolderName());//账户名 如张三
//		map.put("certNo", cardInfo.getBindIdcard());//身份证
//		map.put("accNo", cardInfo.getCardNo());//身份证号
//		map.put("mobile", cardInfo.getBindMobile());//手机号
//		map.put("platcode", "04");// Platcode   COLLEGE_PART_TIME("04","大学生兼职"),
//		String odrc=org.pub.util.uuid.KeySn.getKey();
//		System.out.println(odrc);
//		map.put("oriordercore", accountflow.getTransFlowNo());//交易流水
//		map.put("merchno", "college-part-time");//
//		map.put("amount",  AmountUtil.yuan2Fen(amount));//
//		map.put("cardtype", "1");//支付方式1借记卡  2信用卡 默认上送1
//		map.put("webyurl",webyurl /*"http://localhost/ppayTestMer/wallet/new/payResSYNC"*/);
//		map.put("tranTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//		//ordertype
//		map.put("ordertype", "0");//PAY("0","支付/消费"),
//		return map;
//	}
//
//	public Map<String, Object> payConfirm(Object[] objArr) {
//	    
//	    Map<String, Object> returnMap = new HashMap<String, Object>();
//	    
//		logs.info("##>>> 支付确认..start ");
//		//Object[] objArr = new Object[]{res,smscode,accountflow};
//		ResponseEntity res=(ResponseEntity) objArr[0];
//		String smscode=(String) objArr[1];
//		Accountflow accountflow=(Accountflow) objArr[2];
//		Map<String, String> paramMap=new HashMap<String,String>();
//		paramMap.put("orderTranOrderId", accountflow.getRetTn());
//		paramMap.put("smsCode", smscode);
//    	String sign = MessageSecurity.getMapMessageSecurity(paramMap,enkey);
//    	paramMap.put("sign", sign);
//		StringBuilder json = JsonUtil.toJson(paramMap);
//		String url = Constant.RECHARGE_URL;
//		logs.info(url+" <--上送的url，【支付确认】上送的param："+json.toString());
//		String o = HttpClientHelper.doHttpClient(url, "POST", "utf-8", json.toString(), "60000","application/json","");
//		if(Stringer.isNullOrEmpty(o)){
//			logs.info(" 【支付确认】返回的结果：\r\n\t"+State.ERROR_INTERFACE_NODATA_08.getName());
//			returnMap.put("returnCode", State.ERROR_INTERFACE_NODATA_08.getCode());
//			returnMap.put("returnStr", State.ERROR_INTERFACE_NODATA_08.getName());
//			return returnMap;
//		}
//		logs.info(" 【支付确认】返回的结果：\r\n\t"+o);
//		Map<String, Object> resMap = new HashMap<String, Object>();
//		resMap = JsonUtil.toMap(new StringBuilder(o));
//		Object returnCode = resMap.get("returnCode");
//		if(returnCode.toString().equals("999999")){
//            returnMap.put("returnCode", "999999");
//            returnMap.put("returnCode", State.ERROR_INTERFACE_05.getName());
//            return returnMap;
//        }
//		
//		Object returnMsg = resMap.get("returnMsg");
//        
//        returnMap.put("returnCode", returnCode);
//        returnMap.put("returnStr", returnMsg);
//        return returnMap;
//	}
//
//	
//}
