package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FortressAchievement;

/**
 * 要塞成绩Service接口
 * 
 * @author shawn
 * @date 2024-06-21
 */
public interface IFortressAchievementService 
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
     * 批量删除要塞成绩
     * 
     * @param ids 需要删除的要塞成绩主键集合
     * @return 结果
     */
    public int deleteFortressAchievementByIds(Integer[] ids);

    /**
     * 删除要塞成绩信息
     * 
     * @param id 要塞成绩主键
     * @return 结果
     */
    public int deleteFortressAchievementById(Integer id);
}
