package com.zhl.card.pojo.enums.cardbluebill;

public enum CardBlueBillState {

	/**
	 * 默认
	 */
	CODE00("00","默认");
	
	private String code;
	private String name;
	
	private CardBlueBillState(String code,String name){
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
        for (CardBlueBillState t : CardBlueBillState.values()) {
            if (t.getCode().equals(code)) {
                return t.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (CardBlueBillState t : CardBlueBillState.values()) {
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
