package com.zhl.card.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.message.BasicNameValuePair;
import org.pub.util.json.JsonUtil;
import org.pub.util.properties.PropertiesUtil;
import org.pub.util.security.MapKeyComparator;

import sun.misc.BASE64Encoder;

import com.zhl.card.util.MDUtil;
import com.zhl.card.util.http.HttpClientUtil;

public class HCBillService{

	String apiKey=null;
	String apiSecret=null;
	String url=null;
	String charset=null;
	String callBackUrl=null;
	public HCBillService(){
		try {
	    	Properties p = PropertiesUtil.loadProperties("pub-util.properties");
	    	apiKey = p.getProperty("hc.bill.apikey");
	    	apiSecret = p.getProperty("hc.bill.apiSecret");
	    	url = p.getProperty("hc.bill.url");
	    	charset = p.getProperty("hc.bill.charset");
	    	callBackUrl = p.getProperty("hc.bill.callBackUrl");
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    private String sortMapObjByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        //移除map中的value空值
//        MapRemoveNullUtil.removeNullValue(map); 
        
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
        sortMap.putAll(map);
        
        String retStr="";
        Set<String> keySet = sortMap.keySet();  
        Iterator<String> iter = keySet.iterator();  
        while (iter.hasNext()) {  
            String key = iter.next();  
            System.out.println(key + ":" + map.get(key));  
            retStr+="&"+key+"="+URLDecoder.decode(map.get(key));
        }  
        if(retStr!=null && retStr.length()>0){
        	return retStr.substring(1);
        }

        return retStr;
    }

//    /**
//     * 签名转化   jdk1.6编译错误，暂时注释
//     *
//     * @param reqParam
//     * @return
//     */
//    public String getSign(List<BasicNameValuePair> reqParam) {
//
//        StringBuffer sbb = new StringBuffer();
//        int index = 1;
//        for (BasicNameValuePair nameValuePair : reqParam) {
//            sbb.append(nameValuePair.getName() + "=" + nameValuePair.getValue());
//            if (reqParam.size() != index) {
//                sbb.append("&");
//            }
//            index++;
//        }
//        String sign = sbb.toString();
//
//        Map<String, String> paramMap = new HashMap<String, String>();
//        String ret = "";
//        if (!StringUtils.isEmpty(sign)) {
//            String[] arr = sign.split("&");
//            for (int i = 0; i < arr.length; i++) {
//                String tmp = arr[i];
//                if (-1 != tmp.indexOf("=")) {
//                    paramMap.put(tmp.substring(0, tmp.indexOf("=")), tmp.substring(tmp.indexOf("=") + 1, tmp.length()));
//                }
//
//            }
//        }
//        List<Map.Entry<String, String>> list = new ArrayList<>(paramMap.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
//            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
//                int ret = 0;
//                ret = o1.getKey().compareTo(o2.getKey());
//                if (ret > 0) {
//                    ret = 1;
//                } else {
//                    ret = -1;
//                }
//                return ret;
//            }
//        });
//
//        StringBuilder sbTmp = new StringBuilder();
//        for (Map.Entry<String, String> mapping : list) {
//            if (!"sign".equals(mapping.getKey())) {
//                sbTmp.append(mapping.getKey()).append("=").append(URLDecoder.decode(mapping.getValue())).append("&");
//            }
//        }
//        sbTmp.setLength(sbTmp.lastIndexOf("&"));
//        sbTmp.append(apiSecret);
//        
//        System.out.println("sbTmp:"+sbTmp.toString());
//        ret = MDUtil.SHA1(sbTmp.toString());
//
//        //System.out.println(sb.toString());
//        return ret;
//    }

	public String getBillByEmail(String sequence,String email,String pwd,String billType,String billBankCode,String identityCardNo,String identityName){

		Map<String,Object> retMap = new HashMap<String,Object>();
		if(email==null || email.trim().equals("")){
			retMap.put("retCode", "error");
			retMap.put("retMes", "email is null");
			return JsonUtil.getMapToJson(retMap);
		}
		if(pwd == null || pwd.trim().equals("")){
			retMap.put("retCode", "error");
			retMap.put("retMes", "pwd is null");
			return JsonUtil.getMapToJson(retMap);
		}
		if(billType == null || billType.trim().equals("")){
			billType="email";
		}
		if(billBankCode == null || billBankCode.trim().equals("")){
			billBankCode="ALL";
		}
		

		Map<String,String> map = new HashMap<String,String>();
		map.put("method", "api.bill.get");
		map.put("apiKey", apiKey);
		map.put("callBackUrl", callBackUrl+"?sequence="+sequence);
		map.put("version", "1.2.0");
		map.put("accessType", "api");
		map.put("identityCardNo", identityCardNo);
		map.put("identityName", identityName);
		map.put("username", email);

//		BASE64Encoder encoder = new BASE64Encoder();
//		map.put("password", encoder.encode(pwd.getBytes()));
		map.put("password", pwd);
		map.put("billType", billType);
		map.put("bankCode", billBankCode);
		
		
		String signStr = sortMapObjByKey(map)+apiSecret;
		System.out.println("signStr1====="+signStr);
		
		String sign = MDUtil.SHA1(signStr.trim());
		System.out.println("sign====="+sign);
		map.put("sign", sign); 
		
		System.out.println("请求报文：" + map);
		HttpClientUtil hClient = new HttpClientUtil();  
		System.out.println("url====="+url);
		String httpRtn = hClient.doPost(url,map,charset);  
        System.out.println("result:"+httpRtn);
		return httpRtn;
	}
	
	
	public String queryBill(String token,String bizType){
		
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("method", "api.common.getResult");
		map.put("apiKey", apiKey);
		map.put("version", "1.2.0");
		map.put("token", token);
		
		if(bizType==null || bizType.trim().equals("")){
			bizType="bill";
		}
		map.put("bizType", bizType);
		
		String signStr = sortMapObjByKey(map)+apiSecret;
		System.out.println("signStr====="+signStr);

		String sign = MDUtil.SHA1(signStr.trim());
		System.out.println("sign====="+sign);
		map.put("sign", sign);
		
		System.out.println("请求报文：" + map);
		HttpClientUtil hClient = new HttpClientUtil();  
		String httpRtn = hClient.doPost(url,map,charset);  
//        System.out.println("result:"+httpRtn);
		return httpRtn;
	}

	public static void main(String[] args){
		HCBillService hs = new HCBillService();
//		hs.getBillByEmail("12345678909","feng283669854@163.com", "xw112126", "email", "ICBC|CCB|CMB","","");
		System.out.println(hs.queryBill("23e42a989b5f4a39af6ba7693693f76a", ""));
//		hs.queryBill("a9f29252e9024aa3b72f6365af71794e", "");
	}
}
