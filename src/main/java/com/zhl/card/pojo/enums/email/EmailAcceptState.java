package com.zhl.card.pojo.enums.email;

/**
 * 上游邮箱提取受理枚举
 */
public enum EmailAcceptState {

	/**
	 * 受理完成
	 */
	CODE00("00","受理完成"),
	/**
	 * 受理失败
	 */
	CODE01("01","受理失败"),
	/**
	 * 未获取到账单信息
	 */
	CODE02("02","未获取到账单信息"),
	/**
	 * 用户名密码错误
	 */
	CODE03("03","用户名密码错误"),
	/**
     * 获取完成
     */
    CODE04("04","获取完成");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
//	 System.out.println(UserType.parseOf("01").getName());
	    System.out.println(EmailAcceptState.getText("01"));
	}
	
	private EmailAcceptState(String code,String name){
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
        for (EmailAcceptState t : EmailAcceptState.values()) {
            if (t.getCode().equals(code)) {
                return t.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (EmailAcceptState t : EmailAcceptState.values()) {
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
