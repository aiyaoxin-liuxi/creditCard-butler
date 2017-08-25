package com.zhl.card.service;

import java.util.List;
import java.util.Map;

import com.zhl.card.pojo.EmailInfo;


public interface IEmailInfoService {
    
    /**
     * 增加邮箱
     * @param emailInfo
     * @return
     */
    Map<String, Object> addEmailInfo(String keySn, EmailInfo emailInfo);
    
    /**
     * 邮箱初始入库
     * @param emailInfo
     * @return
     */
    int insertEmailInfo(String keySn, EmailInfo emailInfo);
    
    /**
     * 恢复已删除邮箱
     * @param emailInfo
     * @return
     */
    int reDelEmailInfo(EmailInfo emailInfo);
    
    /**
     * 查询该用户是否绑定过此邮箱
     * @param emailInfo
     * @return
     */
    List<EmailInfo> queryEmailInfoByIdAndEmail(EmailInfo emailInfo);
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    EmailInfo queryEmailInfoById(String id);
    
    /**
     * 根据ybId查询
     * @param id
     * @return
     */
    EmailInfo queryEmailByybId(String ybId);
    
    /**
     * 根据id修改token和ybid
     * @param token
     * @param ybId
     * @return
     */
    int updateEmailInfoTokenAndybIdByid(String token, String ybId, String id);
    
    /**
     * 根据id修改acceptState
     * @param token
     * @param ybId
     * @return
     */
    int updateEmailInfoAcceptStateByid(String id, String acceptState);
    
    /**
     * 查询用户所有绑定邮箱
     * @param logId
     * @param userId
     * @return
     */
    List<EmailInfo> getBindEmail(String logId, String userId);
    
    /**
     * 查询该用户是否绑定过此邮箱
     * @param emailInfo
     * @return
     */
    List<EmailInfo> queryEmailInfoByIdAndUserId(String logId, String userId, String emailId);
    
    /**
     * 修改邮箱密码
     * @param logId
     * @param userId
     * @param emailId
     * @param passWord
     * @return
     */
    int updateEmailPassWord(String emailId, String email, String emailPassword);

}
