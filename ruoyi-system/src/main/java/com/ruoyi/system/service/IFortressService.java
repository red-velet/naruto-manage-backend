package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Fortress;

/**
 * 要塞信息Service接口
 * 
 * @author shawn
 * @date 2024-06-21
 */
public interface IFortressService 
{
    /**
     * 查询要塞信息
     * 
     * @param id 要塞信息主键
     * @return 要塞信息
     */
    public Fortress selectFortressById(Long id);

    /**
     * 查询要塞信息列表
     * 
     * @param fortress 要塞信息
     * @return 要塞信息集合
     */
    public List<Fortress> selectFortressList(Fortress fortress);

    /**
     * 新增要塞信息
     * 
     * @param fortress 要塞信息
     * @return 结果
     */
    public int insertFortress(Fortress fortress);

    /**
     * 修改要塞信息
     * 
     * @param fortress 要塞信息
     * @return 结果
     */
    public int updateFortress(Fortress fortress);

    /**
     * 批量删除要塞信息
     * 
     * @param ids 需要删除的要塞信息主键集合
     * @return 结果
     */
    public int deleteFortressByIds(Long[] ids);

    /**
     * 删除要塞信息信息
     * 
     * @param id 要塞信息主键
     * @return 结果
     */
    public int deleteFortressById(Long id);
}
