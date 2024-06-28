package com.ruoyi.naruto.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.EmailRecord;
import com.ruoyi.system.service.IEmailRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 通知邮件管理Controller
 *
 * @author shawn
 * @date 2024-06-27
 */
@RestController
@RequestMapping("/naruto/emailRecord")
public class EmailRecordController extends BaseController {
    @Autowired
    private IEmailRecordService emailRecordService;

    /**
     * 查询通知邮件管理列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:emailRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(EmailRecord emailRecord) {
        startPage();
        List<EmailRecord> list = emailRecordService.selectEmailRecordList(emailRecord);
        return getDataTable(list);
    }

    /**
     * 导出通知邮件管理列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:emailRecord:export')")
    @Log(title = "通知邮件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EmailRecord emailRecord) {
        List<EmailRecord> list = emailRecordService.selectEmailRecordList(emailRecord);
        ExcelUtil<EmailRecord> util = new ExcelUtil<EmailRecord>(EmailRecord.class);
        util.exportExcel(response, list, "通知邮件管理数据");
    }

    /**
     * 获取通知邮件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:emailRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return success(emailRecordService.selectEmailRecordById(id));
    }

    /**
     * 新增通知邮件管理
     */
    @PreAuthorize("@ss.hasPermi('naruto:emailRecord:add')")
    @Log(title = "通知邮件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EmailRecord emailRecord) {
        return toAjax(emailRecordService.insertEmailRecord(emailRecord));
    }

    /**
     * 修改通知邮件管理
     */
    @PreAuthorize("@ss.hasPermi('naruto:emailRecord:edit')")
    @Log(title = "通知邮件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EmailRecord emailRecord) {
        return toAjax(emailRecordService.updateEmailRecord(emailRecord));
    }

    /**
     * 删除通知邮件管理
     */
    @PreAuthorize("@ss.hasPermi('naruto:emailRecord:remove')")
    @Log(title = "通知邮件管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(emailRecordService.deleteEmailRecordByIds(ids));
    }
}
