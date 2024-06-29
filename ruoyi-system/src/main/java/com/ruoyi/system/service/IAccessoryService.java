package com.ruoyi.system.service;

import com.ruoyi.system.domain.Accessory;
import com.ruoyi.system.domain.vo.AccessoryVo;

import java.util.List;

/**
 * 晓组织饰品Service接口
 *
 * @author redvelet
 * @date 2024-06-28
 */
public interface IAccessoryService {
    /**
     * 查询晓组织饰品
     *
     * @param id 晓组织饰品主键
     * @return 晓组织饰品
     */
    public Accessory selectAccessoryById(Integer id);

    /**
     * 查询晓组织饰品列表
     *
     * @param accessory 晓组织饰品
     * @return 晓组织饰品集合
     */
    public List<Accessory> selectAccessoryList(Accessory accessory);

    /**
     * 新增晓组织饰品
     *
     * @param accessory 晓组织饰品
     * @return 结果
     */
    public int insertAccessory(Accessory accessory);

    /**
     * 修改晓组织饰品
     *
     * @param accessory 晓组织饰品
     * @return 结果
     */
    public int updateAccessory(Accessory accessory);

    /**
     * 批量删除晓组织饰品
     *
     * @param ids 需要删除的晓组织饰品主键集合
     * @return 结果
     */
    public int deleteAccessoryByIds(Integer[] ids);

    /**
     * 删除晓组织饰品信息
     *
     * @param id 晓组织饰品主键
     * @return 结果
     */
    public int deleteAccessoryById(Integer id);

    List<AccessoryVo> getAccessoryList(AccessoryVo accessoryVo);
}
