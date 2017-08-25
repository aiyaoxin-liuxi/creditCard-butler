package com.zhl.card.pojo.enums;

/**
 * 用户类型
 */
public enum UserType {

	/**
	 * 普通用户
	 */
	CODE01("01","普通用户");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
//	 System.out.println(UserType.parseOf("01").getName());
	    System.out.println(UserType.getText("01"));
	}
	
	private UserType(String code,String name){
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
        for (UserType userType : UserType.values()) {
            if (userType.getCode().equals(code)) {
                return userType.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (UserType userType : UserType.values()) {
            if (userType.getName().equals(text)) {
                return userType.code;
            }
        }
        return null;
    }

    public String getText() {
        return name;
    }
}
