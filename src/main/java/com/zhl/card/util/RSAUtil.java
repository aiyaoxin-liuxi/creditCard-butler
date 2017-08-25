package com.zhl.card.util;

import sun.misc.BASE64Encoder;

public class RSAUtil {

	/** 
     * base 64 encode 
     * @param bytes 待编码的byte[] 
     * @return 编码后的base 64 code 
     */  
    public static String base64Encode(byte[] bytes){  
        return new BASE64Encoder().encode(bytes);  
    }  
    
    public static void main(String[] args) throws Exception {  
        String s = "123";  
        System.out.println("转换前：" + s);  
        String encode = base64Encode(s.getBytes());  
        System.out.println("转换后：" + encode);  
    }  
    
}
