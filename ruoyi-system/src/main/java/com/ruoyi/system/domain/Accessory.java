package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 晓组织饰品对象 nar_accessory
 * 
 * @author redvelet
 * @date 2024-06-28
 */
public class Accessory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;

    /** 饰品类型：0-耳环 1-项链 2-手镯 3-戒指 4-徽章 5-腰带 */
    @Excel(name = "饰品类型：0-耳环 1-项链 2-手镯 3-戒指 4-徽章 5-腰带")
    private Integer type;

    /** 组织成员ID */
    @Excel(name = "组织成员ID")
    private Integer mId;

    /** 周起始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "周起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }
    public void setmId(Integer mId) 
    {
        this.mId = mId;
    }

    public Integer getmId() 
    {
        return mId;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("mId", getmId())
            .append("startTime", getStartTime())
            .toString();
    }
}
