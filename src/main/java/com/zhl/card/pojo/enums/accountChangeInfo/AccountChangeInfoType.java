package com.zhl.card.pojo.enums.accountChangeInfo;

public enum AccountChangeInfoType {

	/**
	 * 默认
	 */
	CODE00("00","默认");
	
	private String code;
	private String name;
	
	
	private AccountChangeInfoType(String code,String name){
		this.code=code;
		this.name=name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static String getText(String code) {
        for (AccountChangeInfoType t : AccountChangeInfoType.values()) {
            if (t.getCode().equals(code)) {
                return t.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (AccountChangeInfoType t : AccountChangeInfoType.values()) {
            if (t.getName().equals(text)) {
                return t.code;
            }
        }
        return null;
    }

    public String getText() {
        return name;
    }
}
