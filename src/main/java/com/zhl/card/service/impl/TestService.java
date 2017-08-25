package com.zhl.card.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import com.zhl.card.dao.IEmailInfoDao;
import com.zhl.card.dao.ISmsInfoDao;
import com.zhl.card.pojo.EmailInfo;
import com.zhl.card.pojo.SmsInfo;
import com.zhl.card.service.ITestService;

@Service("testService")
public class TestService implements ITestService {
	
    @Resource
    private IEmailInfoDao emailDao;
    @Resource
    private ISmsInfoDao smsDao;

    @Override
    public void addtestSer() {
        
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setId("123123");
        emailInfo.setIsDel("02");
        emailDao.insertEmailInfo(emailInfo);
        
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setMobile("11111");
        smsInfo.setIsDel("02");
        String a = "abc";
        int b = Integer.parseInt(a);
        smsDao.insertSmsInfo(smsInfo);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void testSer() {
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setId("123123");
        emailInfo.setIsDel("02");
        emailDao.insertEmailInfo(emailInfo);
        
        SmsInfo smsInfo = new SmsInfo();
        smsInfo.setMobile("11111");
        smsInfo.setIsDel("02");
        String a = "abc";
        int b = Integer.parseInt(a);
        smsDao.insertSmsInfo(smsInfo);
        
    }
	

}
