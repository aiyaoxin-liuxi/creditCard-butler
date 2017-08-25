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
import com.zhl.card.util.Constant;
import com.zhl.card.util.Stringer;

@Controller
@RequestMapping(value = "/bank")
public class BankInfoController extends BaseController{

	private Logger logs = LoggerFactory.getLogger(BankInfoController.class);
	
	@Resource
	private IBankInfoService bankInfoService;
	
	@RequestMapping(value = "getBankAll")
	@ResponseBody
	public Object getBankAll(){
	    String keySn = KeySn.getKey();
	    ResponseEntity resEntity = new ResponseEntity();
	    Object object = MemcacheManager.getInstance().get(Constant.BANK_LIST);
	    if(Stringer.isNullOrEmpty(object)){
	        bankInfoService.bankInit();
	        object = MemcacheManager.getInstance().get(Constant.CARD_BIN);
	    }
	    if(Stringer.isNullOrEmpty(object)){
	        resEntity.setReturnCode(ReturnCode.CACHE_FAIL_200003.getCode());
	        resEntity.setReason(ReturnCode.CACHE_FAIL_200003.getName());
            return resEntity;
        }
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", object);
	    resEntity.setResponseEntity(keySn, ReturnCode.SUCCESS_000000.getCode(), ReturnCode.SUCCESS_000000.getName(), map);
	    return resEntity;
	}
	
}
