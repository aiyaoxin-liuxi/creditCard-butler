package com.zhl.card.pojo.common;

import java.util.Map;

import org.pub.util.json.JsonUtil;


public class ResponseEntity {

    /**
     * 流水单号
     */
    private String KeySn;
    /**
     * 返回结果编码
     */
	private String returnCode;
	/**
	 * 返回内容
	 */
	private String reason;
	/**
	 * 返回数据
	 */
	private Object data;
	
	// return
	/**
	 * 写入返回实体
	 * @param keySn 流水号
	 */
	public void setResponseEntity(String keySn){
	    setResponseEntity(keySn, "", "", "");
    }
	/**
     * 写入返回实体
     * @param keySn        流水号
     * @param returnCode   返回码
     */
	public void setResponseEntity(String keySn, String returnCode){
	    setResponseEntity(keySn, returnCode, "", "");
    }
	/**
     * 写入返回实体
     * @param keySn        流水号
     * @param returnCode   返回码
     * @param reason       返回码对应中文提示
     */
	public void setResponseEntity(String keySn, String returnCode, String reason){
	    setResponseEntity(keySn, returnCode, reason, "");
    }
	/**
	 * 写入返回实体
	 * @param keySn        流水号
	 * @param returnCode   返回码
	 * @param reason       返回码对应中文提示
	 * @param data         返回数据
	 */
	public void setResponseEntity(String keySn, String returnCode, String reason, String data){
	    this.KeySn = keySn;
	    this.returnCode = returnCode;
	    this.reason = reason;
	    this.data = data;
	}
	/**
     * 写入返回实体
     * @param keySn        流水号
     * @param returnCode   返回码
     * @param reason       返回码对应中文提示
     * @param data         返回数据
     */
    public void setResponseEntity(String keySn, String returnCode, String reason, Map<String, Object> map){
        this.KeySn = keySn;
        this.returnCode = returnCode;
        this.reason = reason;
        if(!map.isEmpty()){
            this.data = map;
        } else {
            this.data = "";
        }
    }
	
    /**
     * 获取keySn keySn
     */
    public String getKeySn() {
        return KeySn;
    }
    /**
     * 设置keySn keySn
     */
    public void setKeySn(String keySn) {
        KeySn = keySn;
    }
    /**
     * 获取返回结果编码 returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }
    /**
     * 设置返回结果编码 returnCode
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    /**
     * 获取返回内容 reason
     */
    public String getReason() {
        return reason;
    }
    /**
     * 设置返回内容 reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * 获取返回数据 data
     */
    public Object getData() {
        return data;
    }
    /**
     * 设置返回数据 data
     */
    public void setData(Object data) {
        this.data = data;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ResponseEntity [KeySn=" + KeySn + ", returnCode=" + returnCode + ", reason=" + reason + ", data="
                + data + "]";
    }
	
    
}
