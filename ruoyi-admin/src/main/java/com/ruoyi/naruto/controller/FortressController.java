package com.ruoyi.naruto.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Fortress;
import com.ruoyi.system.service.IFortressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 要塞信息Controller
 *
 * @author shawn
 * @date 2024-06-21
 */
@RestController
@RequestMapping("/naruto/fortress")
public class FortressController extends BaseController {
    @Autowired
    private IFortressService fortressService;

    /**
     * 查询要塞信息列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortress:list')")
    @GetMapping("/list")
    public TableDataInfo list(Fortress fortress) {
        startPage();
        List<Fortress> list = fortressService.selectFortressList(fortress);
        return getDataTable(list);
    }

    /**
     * 导出要塞信息列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortress:export')")
    @Log(title = "要塞信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Fortress fortress) {
        List<Fortress> list = fortressService.selectFortressList(fortress);
        ExcelUtil<Fortress> util = new ExcelUtil<Fortress>(Fortress.class);
        util.exportExcel(response, list, "要塞信息数据");
    }

    /**
     * 获取要塞信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(fortressService.selectFortressById(id));
    }

    /**
     * 新增要塞信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortress:add')")
    @Log(title = "要塞信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Fortress fortress) {
        return toAjax(fortressService.insertFortress(fortress));
    }

    /**
     * 修改要塞信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortress:edit')")
    @Log(title = "要塞信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Fortress fortress) {
        return toAjax(fortressService.updateFortress(fortress));
    }

    /**
     * 删除要塞信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortress:remove')")
    @Log(title = "要塞信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(fortressService.deleteFortressByIds(ids));
    }
}
