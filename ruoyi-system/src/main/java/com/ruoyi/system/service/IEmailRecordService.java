package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.EmailRecord;

/**
 * 通知邮件管理Service接口
 * 
 * @author shawn
 * @date 2024-06-28
 */
public interface IEmailRecordService 
{
    /**
     * 查询通知邮件管理
     * 
     * @param id 通知邮件管理主键
     * @return 通知邮件管理
     */
    public EmailRecord selectEmailRecordById(Integer id);

    /**
     * 查询通知邮件管理列表
     * 
     * @param emailRecord 通知邮件管理
     * @return 通知邮件管理集合
     */
    public List<EmailRecord> selectEmailRecordList(EmailRecord emailRecord);

    /**
     * 新增通知邮件管理
     * 
     * @param emailRecord 通知邮件管理
     * @return 结果
     */
    public int insertEmailRecord(EmailRecord emailRecord);

    /**
     * 修改通知邮件管理
     * 
     * @param emailRecord 通知邮件管理
     * @return 结果
     */
    public int updateEmailRecord(EmailRecord emailRecord);

    /**
     * 批量删除通知邮件管理
     * 
     * @param ids 需要删除的通知邮件管理主键集合
     * @return 结果
     */
    public int deleteEmailRecordByIds(Integer[] ids);

    /**
     * 删除通知邮件管理信息
     * 
     * @param id 通知邮件管理主键
     * @return 结果
     */
    public int deleteEmailRecordById(Integer id);
}
