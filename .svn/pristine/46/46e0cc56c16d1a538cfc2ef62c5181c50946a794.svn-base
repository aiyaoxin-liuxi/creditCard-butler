/**
 * 
 */
package com.zhl.card.pojo.enums.common;


/**
 * 逻辑删除
 */
public enum IsDel {

	/** 正常 */
	CODE00("00","正常"),
	
	/** 已删除 */
	CODE01("01","已删除");
	
	
	private String code;
	private String cnName;

	/**
	 * @param code
	 * @param cnName
	 */
	private IsDel(String code, String cnName) {
		this.code = code;
		this.cnName = cnName;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getCnName() {
		return cnName;
	}


	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	
	public static String getText(String code) {
		for (IsDel isDel : IsDel.values()) {
			if (isDel.getCode().equals(code)) {
				return isDel.cnName;
			}
		}
		return null;
	}
	
	public static String getCode(String text) {
		for (IsDel isDel : IsDel.values()) {
			if (isDel.getCnName().equals(text)) {
				return isDel.code;
			}
		}
		return null;
	}

	public String getText() {
		return cnName;
	}
}
