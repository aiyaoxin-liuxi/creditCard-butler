package com.zhl.card.pojo.enums.common;

import java.util.HashMap;
import java.util.Map;

public enum ReturnCode {


	/** 成功 */
	SUCCESS_000000("000000","操作成功"),
	/** 未找到请求对象 */
	OBJECT_NOT_FIND_111111("111111","未找到请求对象"),
	/** 未找到卡bin信息 */
    CARDBIN_NOT_FIND_111112("111112","未找到卡bin信息"),
    /** 未找到卡信息 */
    CARD_NOT_FIND_111113("111113","未找到卡信息"),
    /** 未找到邮箱信息 */
    EMAIL_NOT_FIND_111114("111114","未找到邮箱信息"),
    /** 此手机号未注册 */
    MOBILE_NOT_FIND_111115("111115","此手机号未注册"),
    /** 未找到账单数据 */
    BLUE_BILL_NOT_FIND_111116("111116","未找到账单数据"),
    /** 账单正在查询中，请耐心等待 */
    WAIT_BILL_111117("111117","账单正在查询中，请耐心等待"),
	/** 对象已经存在 */
	OBJECT_ALREADY_EXIST_222222("222222","对象已经存在"),
	/** 邮箱已经存在 */
    EMAIL_EXIST_2222223("222223","邮箱已经存在"),
	/** 数据验证失败 */
	DATA_VALID_FAIL_200000("200000","数据验证失败"),
	/** 注册失败 */
	REGISTER_FAIL_200001("200001","注册失败"),
	/** 用户名密码错误 */
	LOGIN_FAIL_200002("200002","用户名密码错误"),
	/** 缓存获取错误 */
    CACHE_FAIL_200003("200003","缓存获取错误"),
    /** 绑卡失败 */
    BIND_CARD_FAIL_200004("200004","绑卡失败"),
    /** 绑卡成功，写入失败 */
    BIND_CARD_DB_FAIL_200005("200005","绑卡成功，写入失败"),
    /** 上传失败 */
    UPLOAD_FAIL_200006("200006","上传失败"),
    /** 添加失败 */
    INSERT_FAIL_200007("200007","添加失败"),
    /** 修改失败 */
    UPDATE_FAIL_200008("200008","修改失败"),
    /** 接口无返回 */
    RETURN_NULL_FAIL_200009("200009","接口无返回"),
    /** 接口返回错误 */
    RETURN_FAIL_200010("200010","接口返回错误"),
    /** 邮箱用户名密码错误 */
    EMAIL_LOGIN_FAIL_200011("200011","邮箱用户名密码错误"),
    /** 邮箱数据错误 */
    EMAIL_INFO_FAIL_200012("200012","邮箱数据错误"),
	/** 短信验证码错误 2016-12-10为爱农快捷支付添加*/
	DATA_VALID_SMSCODE_FAIL_202015("202015","短信验证码错误"),
	/** 数据验签失败 */
	DATA_SIGNATURE_FAIL_300000("300000","数据验签失败"),
	/** 接口调用失败 */
	GATEWAY_EXCEPTION_400000("400000","接口调用失败"),
	/** 网络连接失败 */
	REQUEST_TIMEOUT_500000("500000","网络连接失败"),
	/** 程序错误 */
	PROGRAM_EXCEPTION_600000("600000","程序错误"),
	/** 事件验证失败 */
	EVENT_EXCEPTION_888888("888888","事件验证失败"),
	/** 系统错误 */
	SYSTEM_EXCEPTION_999999("999999","系统错误"),
	/** 操作失败 */
	FAIL_110110("110110","操作失败");
	
	
	private String code;
	private String name;
	
	public static void main(String[] args) {
	 System.out.println(ReturnCode.parseOf("00").getName());	
	}
	
	private ReturnCode(String code,String name){
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
	
	private static Map<String, ReturnCode> valueMap = new HashMap<String, ReturnCode>();

	static {
		for (ReturnCode _enum : ReturnCode.values()) {
			valueMap.put(_enum.code, _enum);
		}
	}
	
	public static ReturnCode parseOf(String code) {
		for (ReturnCode item : values())
			if (item.getCode().equals(code))
				return item;

		throw new IllegalArgumentException("ReturnCode异常错误代码[" + code + "]不匹配!");
	}

}
