package com.ruoyi.naruto.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Accessory;
import com.ruoyi.system.domain.vo.AccessoryVo;
import com.ruoyi.system.service.IAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 晓组织饰品Controller
 *
 * @author redvelet
 * @date 2024-06-28
 */
@RestController
@RequestMapping("/naruto/accessory")
public class AccessoryController extends BaseController {
    @Autowired
    private IAccessoryService accessoryService;

    /**
     * 查询晓组织饰品列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:list')")
    @GetMapping("/list")
    public TableDataInfo list(Accessory accessory) {
        startPage();
        List<Accessory> list = accessoryService.selectAccessoryList(accessory);
        return getDataTable(list);
    }

    /**
     * 查询晓组织饰品列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:list')")
    @GetMapping("/getList")
    public TableDataInfo getList(AccessoryVo accessoryVo) {
        startPage();
        List<AccessoryVo> list = accessoryService.getAccessoryList(accessoryVo);
        return getDataTable(list);
    }

    /**
     * 导出晓组织饰品列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:export')")
    @Log(title = "晓组织饰品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Accessory accessory) {
        List<Accessory> list = accessoryService.selectAccessoryList(accessory);
        ExcelUtil<Accessory> util = new ExcelUtil<Accessory>(Accessory.class);
        util.exportExcel(response, list, "晓组织饰品数据");
    }

    /**
     * 获取晓组织饰品详细信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return success(accessoryService.selectAccessoryById(id));
    }

    /**
     * 新增晓组织饰品
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:add')")
    @Log(title = "晓组织饰品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Accessory accessory) {
        return toAjax(accessoryService.insertAccessory(accessory));
    }

    /**
     * 修改晓组织饰品
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:edit')")
    @Log(title = "晓组织饰品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Accessory accessory) {
        return toAjax(accessoryService.updateAccessory(accessory));
    }

    /**
     * 删除晓组织饰品
     */
    @PreAuthorize("@ss.hasPermi('naruto:accessory:remove')")
    @Log(title = "晓组织饰品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(accessoryService.deleteAccessoryByIds(ids));
    }
}
