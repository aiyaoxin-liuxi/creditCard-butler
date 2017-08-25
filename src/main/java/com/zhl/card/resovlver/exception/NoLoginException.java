package com.zhl.card.resovlver.exception;

/**
 *  未登录异常
  * @ClassName: NoLoginException
  * @Description: TODO
  * @author zhaotisheng	
  * @date 2017年3月24日 下午4:41:21
  * Copyright (c) 2016, zhaotisheng@qq.com All Rights Reserved.
 */
public class NoLoginException extends BaseException {

	/**
	  * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	  */
	
	private static final long serialVersionUID = -1736107030172207890L;

	public NoLoginException(){
		super(ExceptionType.NO_LOGIN.getName());
	}
	public NoLoginException(String msg){
		super(msg);
	}
}
