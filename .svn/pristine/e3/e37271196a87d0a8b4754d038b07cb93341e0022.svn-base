package com.zhl.card.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.pub.util.memcached.MemcacheManager;
import org.springframework.stereotype.Service;

import com.zhl.card.dao.IBankInfoDao;
import com.zhl.card.pojo.BankInfo;
import com.zhl.card.service.IBankInfoService;
import com.zhl.card.util.Constant;

@Service("bankInfoService")
public class BankInfoService implements IBankInfoService {
	
	@Resource
	private IBankInfoDao bankInfoDao;

	@Override
	public List<BankInfo> queryBankInfoAll() {
		return bankInfoDao.queryBankInfoAll();
	}
	
	@Override
    public void bankInit() {
        List<BankInfo> bankInfoList = bankInfoDao.queryBankInfoAll();
        if(null != bankInfoList && bankInfoList.size() > 0){
            MemcacheManager.getInstance().add(Constant.BANK_LIST, bankInfoList);
            
            Map<String, Object> map = new HashMap<String, Object>();
            for(BankInfo bankInfo : bankInfoList){
                map.put(bankInfo.getId(), bankInfo.getBankName());
            }
            MemcacheManager.getInstance().add(Constant.BANK_MAP, map);
        }
    }

	

}
