package com.ruoyi.naruto.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.domain.MemberExport;
import com.ruoyi.system.domain.vo.PowerBetweenVo;
import com.ruoyi.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 成员信息Controller
 *
 * @author shawn
 * @date 2024-06-19
 */
@RestController
@RequestMapping("/naruto/member")
public class MemberController extends BaseController {
    @Autowired
    private IMemberService memberService;

    /**
     * 查询成员信息列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(Member member) {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        //暂时性的添加用户
        //memberService.tmpMethod();
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('naruto:member:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll() {
        List<Member> list = memberService.selectMemberListAll();
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", list);
        return ajaxResult;
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('naruto:member:import')")
    @PostMapping("/import")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<MemberExport> util = new ExcelUtil<MemberExport>(MemberExport.class);
        List<MemberExport> memberList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = memberService.importMember(memberList, updateSupport, operName);
        return success(message);
    }

    /**
     * 导出成员信息列表
     */
    @PreAuthorize("@ss.hasPermi('naruto:member:export')")
    @Log(title = "组织成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Member member) {
        List<MemberExport> list = memberService.selectMemberListExport(member);
        ExcelUtil<MemberExport> util = new ExcelUtil<MemberExport>(MemberExport.class);
        util.exportExcel(response, list, "组织成员数据");
    }

    /**
     * 获取成员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(memberService.selectMemberById(id));
    }

    /**
     * 新增成员信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:member:add')")
    @Log(title = "成员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Member member) {
        return toAjaxExist(memberService.insertMember(member));
    }

    /**
     * 修改成员信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:member:edit')")
    @Log(title = "成员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Member member) {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除成员信息
     */
    @PreAuthorize("@ss.hasPermi('naruto:member:remove')")
    @Log(title = "成员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(memberService.deleteMemberByIds(ids));
    }

    /**
     * 查询并统计每个战力阶段的成员人数
     */
    @GetMapping("/getPowerChart")
    public AjaxResult getPowerChart() {
        //获取区间
        List<String> between = memberService.getBetweenPower();

        //获取人数
        List<Integer> counts = memberService.getBetweenPowerCount(between);
        AjaxResult success = AjaxResult.success();
        success.put("betweens", between);
        success.put("counts", counts);
        return success;
    }

    /**
     * 获取战力区间人数
     */
    @GetMapping(value = "/getPowerBetween")
    public AjaxResult getPowerBetween() {
        AjaxResult success = AjaxResult.success();
        List<PowerBetweenVo> data = memberService.getPowerBetween();
        success.put("data", data);
        return success;
    }


}
