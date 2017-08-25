package com.zhl.card.pojo.enums;

public enum UserState {

	/**
	 * 启用
	 */
	CODE01("01","启用"),
	/**
	 * 禁用
	 */
	CODE02("02","禁用");
	
	private String code;
	private String name;
	
	private UserState(String code,String name){
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
        for (UserState userState : UserState.values()) {
            if (userState.getCode().equals(code)) {
                return userState.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (UserState userState : UserState.values()) {
            if (userState.getName().equals(text)) {
                return userState.code;
            }
        }
        return null;
    }

    public String getText() {
        return name;
    }

}
