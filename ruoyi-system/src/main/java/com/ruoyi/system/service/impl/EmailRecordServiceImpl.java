package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EmailRecordMapper;
import com.ruoyi.system.domain.EmailRecord;
import com.ruoyi.system.service.IEmailRecordService;

/**
 * 通知邮件管理Service业务层处理
 * 
 * @author shawn
 * @date 2024-06-28
 */
@Service
public class EmailRecordServiceImpl implements IEmailRecordService 
{
    @Autowired
    private EmailRecordMapper emailRecordMapper;

    /**
     * 查询通知邮件管理
     * 
     * @param id 通知邮件管理主键
     * @return 通知邮件管理
     */
    @Override
    public EmailRecord selectEmailRecordById(Integer id)
    {
        return emailRecordMapper.selectEmailRecordById(id);
    }

    /**
     * 查询通知邮件管理列表
     * 
     * @param emailRecord 通知邮件管理
     * @return 通知邮件管理
     */
    @Override
    public List<EmailRecord> selectEmailRecordList(EmailRecord emailRecord)
    {
        return emailRecordMapper.selectEmailRecordList(emailRecord);
    }

    /**
     * 新增通知邮件管理
     * 
     * @param emailRecord 通知邮件管理
     * @return 结果
     */
    @Override
    public int insertEmailRecord(EmailRecord emailRecord)
    {
        return emailRecordMapper.insertEmailRecord(emailRecord);
    }

    /**
     * 修改通知邮件管理
     * 
     * @param emailRecord 通知邮件管理
     * @return 结果
     */
    @Override
    public int updateEmailRecord(EmailRecord emailRecord)
    {
        return emailRecordMapper.updateEmailRecord(emailRecord);
    }

    /**
     * 批量删除通知邮件管理
     * 
     * @param ids 需要删除的通知邮件管理主键
     * @return 结果
     */
    @Override
    public int deleteEmailRecordByIds(Integer[] ids)
    {
        return emailRecordMapper.deleteEmailRecordByIds(ids);
    }

    /**
     * 删除通知邮件管理信息
     * 
     * @param id 通知邮件管理主键
     * @return 结果
     */
    @Override
    public int deleteEmailRecordById(Integer id)
    {
        return emailRecordMapper.deleteEmailRecordById(id);
    }
}
