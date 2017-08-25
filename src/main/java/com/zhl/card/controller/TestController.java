package com.zhl.card.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.memcached.MemcacheManager;
import org.pub.util.uuid.KeySn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhl.card.controller.common.BaseController;
import com.zhl.card.pojo.BankInfo;
import com.zhl.card.pojo.common.ResponseEntity;
import com.zhl.card.pojo.enums.common.ReturnCode;
import com.zhl.card.service.IBankInfoService;
import com.zhl.card.service.ITestService;
import com.zhl.card.util.Constant;
import com.zhl.card.util.Stringer;

@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(TestController.class);
	
	@Resource
	private ITestService testService;
	
	@RequestMapping(value = "/test1")
	public Object testC(){
	    
	    Map<String, Object> reMap = new HashMap<String, Object>();
	    
	    logs.info("进入");
	    
	    testService.testSer();
//	    testService.addtestSer();
	    
	    
	    logs.info("完成");
        return null;
	    
	}
	
}
