package com.zhl.card.pojo.enums.cardbluebilldetail;

public enum CardBlueBillDetailState {

	/**
	 * 默认
	 */
	CODE00("00","默认");
	
	private String code;
	private String name;
	
	private CardBlueBillDetailState(String code,String name){
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
        for (CardBlueBillDetailState t : CardBlueBillDetailState.values()) {
            if (t.getCode().equals(code)) {
                return t.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (CardBlueBillDetailState t : CardBlueBillDetailState.values()) {
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
