package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 成员信息对象 nar_member
 *
 * @author shawn
 * @date 2024-06-19
 */
public class Member extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 游戏ID
     */
    @Excel(name = "游戏ID")
    private String nId;

    /**
     * 游戏昵称
     */
    @Excel(name = "游戏昵称")
    private String nickname;

    /**
     * 身份类型
     */
    @Excel(name = "身份类型")
    private Long type;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private Long state;

    /**
     * 战力
     */
    @Excel(name = "战力")
    private String power;

    /**
     * qq
     */
    @Excel(name = "qq")
    private String qq;

    /**
     * 最近加入
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最近加入", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinTime;

    /**
     * 首次加入
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次加入", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 退出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "退出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getnId() {
        return nId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getState() {
        return state;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getPower() {
        return power;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("nId", getnId())
                .append("nickname", getNickname())
                .append("type", getType())
                .append("state", getState())
                .append("power", getPower())
                .append("qq", getQq())
                .append("joinTime", getJoinTime())
                .append("createdTime", getCreatedTime())
                .append("updatedTime", getUpdatedTime())
                .toString();
    }
}
