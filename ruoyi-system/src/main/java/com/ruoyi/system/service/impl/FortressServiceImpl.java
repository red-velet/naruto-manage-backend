package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FortressMapper;
import com.ruoyi.system.domain.Fortress;
import com.ruoyi.system.service.IFortressService;

/**
 * 要塞信息Service业务层处理
 * 
 * @author shawn
 * @date 2024-06-21
 */
@Service
public class FortressServiceImpl implements IFortressService 
{
    @Autowired
    private FortressMapper fortressMapper;

    /**
     * 查询要塞信息
     * 
     * @param id 要塞信息主键
     * @return 要塞信息
     */
    @Override
    public Fortress selectFortressById(Long id)
    {
        return fortressMapper.selectFortressById(id);
    }

    /**
     * 查询要塞信息列表
     * 
     * @param fortress 要塞信息
     * @return 要塞信息
     */
    @Override
    public List<Fortress> selectFortressList(Fortress fortress)
    {
        return fortressMapper.selectFortressList(fortress);
    }

    /**
     * 新增要塞信息
     * 
     * @param fortress 要塞信息
     * @return 结果
     */
    @Override
    public int insertFortress(Fortress fortress)
    {
        return fortressMapper.insertFortress(fortress);
    }

    /**
     * 修改要塞信息
     * 
     * @param fortress 要塞信息
     * @return 结果
     */
    @Override
    public int updateFortress(Fortress fortress)
    {
        return fortressMapper.updateFortress(fortress);
    }

    /**
     * 批量删除要塞信息
     * 
     * @param ids 需要删除的要塞信息主键
     * @return 结果
     */
    @Override
    public int deleteFortressByIds(Long[] ids)
    {
        return fortressMapper.deleteFortressByIds(ids);
    }

    /**
     * 删除要塞信息信息
     * 
     * @param id 要塞信息主键
     * @return 结果
     */
    @Override
    public int deleteFortressById(Long id)
    {
        return fortressMapper.deleteFortressById(id);
    }
}
