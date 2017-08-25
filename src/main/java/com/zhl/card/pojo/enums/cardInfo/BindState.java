package com.zhl.card.pojo.enums.cardInfo;

/**
 * 绑卡状态
 */
public enum BindState {

	/**
	 * 绑卡成功
	 */
	CODE00("00","绑卡成功"),
	/**
     * 绑卡失败
     */
	CODE01("01","绑卡失败"),
	/**
     * 未绑卡
     */
    CODE03("03","未绑卡");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
//	 System.out.println(UserType.parseOf("01").getName());
	    System.out.println(BindState.getText("01"));
	}
	
	private BindState(String code,String name){
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
        for (BindState bindState : BindState.values()) {
            if (bindState.getCode().equals(code)) {
                return bindState.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (BindState bindState : BindState.values()) {
            if (bindState.getName().equals(text)) {
                return bindState.code;
            }
        }
        return null;
    }

    public String getText() {
        return name;
    }
}
