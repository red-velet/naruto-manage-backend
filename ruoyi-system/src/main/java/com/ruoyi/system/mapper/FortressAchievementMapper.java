package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FortressAchievement;

/**
 * 要塞成绩Mapper接口
 * 
 * @author shawn
 * @date 2024-06-21
 */
public interface FortressAchievementMapper 
{
    /**
     * 查询要塞成绩
     * 
     * @param id 要塞成绩主键
     * @return 要塞成绩
     */
    public FortressAchievement selectFortressAchievementById(Integer id);

    /**
     * 查询要塞成绩列表
     * 
     * @param fortressAchievement 要塞成绩
     * @return 要塞成绩集合
     */
    public List<FortressAchievement> selectFortressAchievementList(FortressAchievement fortressAchievement);

    /**
     * 新增要塞成绩
     * 
     * @param fortressAchievement 要塞成绩
     * @return 结果
     */
    public int insertFortressAchievement(FortressAchievement fortressAchievement);

    /**
     * 修改要塞成绩
     * 
     * @param fortressAchievement 要塞成绩
     * @return 结果
     */
    public int updateFortressAchievement(FortressAchievement fortressAchievement);

    /**
     * 删除要塞成绩
     * 
     * @param id 要塞成绩主键
     * @return 结果
     */
    public int deleteFortressAchievementById(Integer id);

    /**
     * 批量删除要塞成绩
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFortressAchievementByIds(Integer[] ids);
}
