package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FortressAchievementMapper;
import com.ruoyi.system.domain.FortressAchievement;
import com.ruoyi.system.service.IFortressAchievementService;

/**
 * 要塞成绩Service业务层处理
 * 
 * @author shawn
 * @date 2024-06-21
 */
@Service
public class FortressAchievementServiceImpl implements IFortressAchievementService 
{
    @Autowired
    private FortressAchievementMapper fortressAchievementMapper;

    /**
     * 查询要塞成绩
     * 
     * @param id 要塞成绩主键
     * @return 要塞成绩
     */
    @Override
    public FortressAchievement selectFortressAchievementById(Integer id)
    {
        return fortressAchievementMapper.selectFortressAchievementById(id);
    }

    /**
     * 查询要塞成绩列表
     * 
     * @param fortressAchievement 要塞成绩
     * @return 要塞成绩
     */
    @Override
    public List<FortressAchievement> selectFortressAchievementList(FortressAchievement fortressAchievement)
    {
        return fortressAchievementMapper.selectFortressAchievementList(fortressAchievement);
    }

    /**
     * 新增要塞成绩
     * 
     * @param fortressAchievement 要塞成绩
     * @return 结果
     */
    @Override
    public int insertFortressAchievement(FortressAchievement fortressAchievement)
    {
        return fortressAchievementMapper.insertFortressAchievement(fortressAchievement);
    }

    /**
     * 修改要塞成绩
     * 
     * @param fortressAchievement 要塞成绩
     * @return 结果
     */
    @Override
    public int updateFortressAchievement(FortressAchievement fortressAchievement)
    {
        return fortressAchievementMapper.updateFortressAchievement(fortressAchievement);
    }

    /**
     * 批量删除要塞成绩
     * 
     * @param ids 需要删除的要塞成绩主键
     * @return 结果
     */
    @Override
    public int deleteFortressAchievementByIds(Integer[] ids)
    {
        return fortressAchievementMapper.deleteFortressAchievementByIds(ids);
    }

    /**
     * 删除要塞成绩信息
     * 
     * @param id 要塞成绩主键
     * @return 结果
     */
    @Override
    public int deleteFortressAchievementById(Integer id)
    {
        return fortressAchievementMapper.deleteFortressAchievementById(id);
    }
}
