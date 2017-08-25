package com.zhl.card.dao;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.EmailInfo;

public interface IEmailInfoDao {
    
    /**
     * 按条件查询
     * @return
     */
    List<EmailInfo> queryEmailCondition2List(Map<String, Object> map);
    
    /**
     * 按条件查询,不返回邮箱密码
     * @return
     */
    List<EmailInfo> queryEmailCondition2ListNoPassword(Map<String, Object> map);
    
    /**
     * 写入
     * @return
     */
	int insertEmailInfo(EmailInfo emailInfo);
	
	/**
	 * 修改
	 * @return
	 */
	int updateEmailInfo(Map<String, Object> map);

}
