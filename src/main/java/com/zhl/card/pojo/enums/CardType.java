package com.zhl.card.pojo.enums;

/**
 * 卡类型
 */
public enum CardType {

	/**
	 * 借记卡
	 */
	CODE01("01","借记卡"),
	/**
     * 信用卡
     */
    CODE02("02","信用卡"),
    /**
     * 借贷卡
     */
    CODE03("03","借贷卡");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
//	 System.out.println(UserType.parseOf("01").getName());
	    System.out.println(CardType.getText("01"));
	}
	
	private CardType(String code,String name){
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
        for (CardType userType : CardType.values()) {
            if (userType.getCode().equals(code)) {
                return userType.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (CardType cardType : CardType.values()) {
            if (cardType.getName().equals(text)) {
                return cardType.code;
            }
        }
        return null;
    }

    public String getText() {
        return name;
    }
}
