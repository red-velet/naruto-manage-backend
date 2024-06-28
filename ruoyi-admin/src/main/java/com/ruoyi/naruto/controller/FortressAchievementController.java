package com.ruoyi.naruto.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.FortressAchievement;
import com.ruoyi.system.service.IFortressAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 要塞成绩Controller
 *
 * @author shawn
 * @date 2024-06-21
 */
@RestController
@RequestMapping("/naruto/fortressAchievement")
public class FortressAchievementController extends BaseController {
    @Autowired
    private IFortressAchievementService fortressAchievementService;

    /**
     * 查询要塞成绩列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortressAchievement:list')")
    @GetMapping("/list")
    public TableDataInfo list(FortressAchievement fortressAchievement) {
        startPage();
        List<FortressAchievement> list = fortressAchievementService.selectFortressAchievementList(fortressAchievement);
        return getDataTable(list);
    }

    /**
     * 导出要塞成绩列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortressAchievement:export')")
    @Log(title = "要塞成绩", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FortressAchievement fortressAchievement) {
        List<FortressAchievement> list = fortressAchievementService.selectFortressAchievementList(fortressAchievement);
        ExcelUtil<FortressAchievement> util = new ExcelUtil<FortressAchievement>(FortressAchievement.class);
        util.exportExcel(response, list, "要塞成绩数据");
    }

    /**
     * 获取要塞成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortressAchievement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return success(fortressAchievementService.selectFortressAchievementById(id));
    }

    /**
     * 新增要塞成绩
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortressAchievement:add')")
    @Log(title = "要塞成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FortressAchievement fortressAchievement) {
        return toAjax(fortressAchievementService.insertFortressAchievement(fortressAchievement));
    }

    /**
     * 修改要塞成绩
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortressAchievement:edit')")
    @Log(title = "要塞成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FortressAchievement fortressAchievement) {
        return toAjax(fortressAchievementService.updateFortressAchievement(fortressAchievement));
    }

    /**
     * 删除要塞成绩
     */
    @PreAuthorize("@ss.hasPermi('naruto:fortressAchievement:remove')")
    @Log(title = "要塞成绩", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(fortressAchievementService.deleteFortressAchievementByIds(ids));
    }
}
