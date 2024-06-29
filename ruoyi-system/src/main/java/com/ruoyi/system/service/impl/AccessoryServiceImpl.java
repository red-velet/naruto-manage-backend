package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.Accessory;
import com.ruoyi.system.domain.vo.AccessoryVo;
import com.ruoyi.system.mapper.AccessoryMapper;
import com.ruoyi.system.service.IAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 晓组织饰品Service业务层处理
 *
 * @author redvelet
 * @date 2024-06-28
 */
@Service
public class AccessoryServiceImpl implements IAccessoryService {
    @Autowired
    private AccessoryMapper accessoryMapper;

    /**
     * 查询晓组织饰品
     *
     * @param id 晓组织饰品主键
     * @return 晓组织饰品
     */
    @Override
    public Accessory selectAccessoryById(Integer id) {
        return accessoryMapper.selectAccessoryById(id);
    }

    /**
     * 查询晓组织饰品列表
     *
     * @param accessory 晓组织饰品
     * @return 晓组织饰品
     */
    @Override
    public List<Accessory> selectAccessoryList(Accessory accessory) {
        return accessoryMapper.selectAccessoryList(accessory);
    }

    @Override
    public List<AccessoryVo> getAccessoryList(AccessoryVo accessoryVo) {
        return accessoryMapper.getList(accessoryVo);
    }

    /**
     * 新增晓组织饰品
     *
     * @param accessory 晓组织饰品
     * @return 结果
     */
    @Override
    public int insertAccessory(Accessory accessory) {
        return accessoryMapper.insertAccessory(accessory);
    }

    /**
     * 修改晓组织饰品
     *
     * @param accessory 晓组织饰品
     * @return 结果
     */
    @Override
    public int updateAccessory(Accessory accessory) {
        return accessoryMapper.updateAccessory(accessory);
    }

    /**
     * 批量删除晓组织饰品
     *
     * @param ids 需要删除的晓组织饰品主键
     * @return 结果
     */
    @Override
    public int deleteAccessoryByIds(Integer[] ids) {
        return accessoryMapper.deleteAccessoryByIds(ids);
    }

    /**
     * 删除晓组织饰品信息
     *
     * @param id 晓组织饰品主键
     * @return 结果
     */
    @Override
    public int deleteAccessoryById(Integer id) {
        return accessoryMapper.deleteAccessoryById(id);
    }


}
