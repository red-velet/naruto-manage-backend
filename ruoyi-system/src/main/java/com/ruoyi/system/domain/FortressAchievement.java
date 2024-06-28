package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 要塞成绩对象 nar_fortress_achievement
 * 
 * @author shawn
 * @date 2024-06-21
 */
public class FortressAchievement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityTime;

    /** 要塞编号 */
    @Excel(name = "要塞编号")
    private Integer fortressId;

    /** 要塞排名：0-第一名/荣耀礼包 1-第二名/闪光礼包 */
    @Excel(name = "要塞排名：0-第一名/荣耀礼包 1-第二名/闪光礼包")
    private Integer rank;

    /** 第一名组织 */
    @Excel(name = "第一名组织")
    private String first;

    /** 第二名组织 */
    @Excel(name = "第二名组织")
    private String second;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setActivityTime(Date activityTime) 
    {
        this.activityTime = activityTime;
    }

    public Date getActivityTime() 
    {
        return activityTime;
    }
    public void setFortressId(Integer fortressId) 
    {
        this.fortressId = fortressId;
    }

    public Integer getFortressId() 
    {
        return fortressId;
    }
    public void setRank(Integer rank) 
    {
        this.rank = rank;
    }

    public Integer getRank() 
    {
        return rank;
    }
    public void setFirst(String first) 
    {
        this.first = first;
    }

    public String getFirst() 
    {
        return first;
    }
    public void setSecond(String second) 
    {
        this.second = second;
    }

    public String getSecond() 
    {
        return second;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityTime", getActivityTime())
            .append("fortressId", getFortressId())
            .append("rank", getRank())
            .append("first", getFirst())
            .append("second", getSecond())
            .toString();
    }
}
