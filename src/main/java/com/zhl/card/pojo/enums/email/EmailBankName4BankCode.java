package com.zhl.card.pojo.enums.email;

/**
 * email提取接口银行编码对应的银行名称的枚举
 */
public enum EmailBankName4BankCode {

	/**
	 * 工商银行
	 */
	ICBC("ICBC","工商银行"),
	/**
	 * 建设银行
	 */
	CCB("CCB","建设银行"),
	/**
	 * 中国银行
	 */
	BOC("BOC","中国银行"),
	/**
	 * 农业银行
	 */
	ABC("ABC","农业银行"),
	/**
	 * 交通银行
	 */
	BCM("BCM","交通银行"),
	/**
	 * 招商银行
	 */
	CMB("CMB","招商银行"),
	/**
	 * 兴业银行
	 */
	CIB("CIB","兴业银行"),
	/**
	 * 广发银行
	 */
	CGB("CGB","广发银行"),
	/**
	 * 浦发银行
	 */
	SPDB("SPDB","浦发银行"),
	/**
	 * 平安银行
	 */
	PAB("PAB","平安银行"),
	/**
	 * 上海银行
	 */
	BOSC("BOSC","上海银行"),
	/**
	 * 中信银行
	 */
	CITIC("CITIC","中信银行"),
	/**
	 * 华夏银行
	 */
	HXB("HXB","华夏银行"),
	/**
	 * 民生银行
	 */
	CMBC("CMBC","民生银行"),
	/**
	 * 光大银行
	 */
	CEB("CEB","光大银行"),
	/**
	 * 花旗银行
	 */
	CITIBANK("CITIBANK","花旗银行");
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
//	 System.out.println(UserType.parseOf("01").getName());
	    System.out.println(EmailBankName4BankCode.getText("01"));
	}
	
	private EmailBankName4BankCode(String code,String name){
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
        for (EmailBankName4BankCode t : EmailBankName4BankCode.values()) {
            if (t.getCode().equals(code)) {
                return t.name;
            }
        }
        return null;
    }
    
    public static String getCode(String text) {
        for (EmailBankName4BankCode t : EmailBankName4BankCode.values()) {
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
