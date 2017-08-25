package com.zhl.card.service;

import java.util.Map;

public interface IEmailBillInsertService {
    
    
    Map<String, Object> addEmailAndInsertBill(String logId, String userId, String reStr);

}
