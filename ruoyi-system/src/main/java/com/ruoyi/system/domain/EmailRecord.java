package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 通知邮件管理对象 nar_email_record
 *
 * @author shawn
 * @date 2024-06-28
 */
public class EmailRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;

    /**
     * 发件人
     */
    @Excel(name = "发件人")
    private String fromMember;

    /**
     * 收件人
     */
    @Excel(name = "收件人")
    private String toMember;

    /**
     * 主题
     */
    @Excel(name = "主题")
    private String subject;

    /**
     * 邮件状态
     */
    @Excel(name = "邮件状态")
    private Integer state;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;


    /**
     * 错误原因
     */
    @Excel(name = "错误原因")
    private String reason;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setFromMember(String fromMember) {
        this.fromMember = fromMember;
    }

    public String getFromMember() {
        return fromMember;
    }

    public void setToMember(String toMember) {
        this.toMember = toMember;
    }

    public String getToMember() {
        return toMember;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("fromMember", getFromMember())
                .append("toMember", getToMember())
                .append("subject", getSubject())
                .append("state", getState())
                .append("sendTime", getSendTime())
                .append("reason", getReason())
                .toString();
    }
}
