package com.ruoyi.system.service;

import com.ruoyi.system.domain.Member;
import com.ruoyi.system.domain.MemberExport;
import com.ruoyi.system.domain.vo.PowerBetweenVo;

import java.util.List;

/**
 * 成员信息Service接口
 *
 * @author shawn
 * @date 2024-06-19
 */
public interface IMemberService {
    /**
     * 查询成员信息
     *
     * @param id 成员信息主键
     * @return 成员信息
     */
    public Member selectMemberById(Long id);

    /**
     * 查询成员信息列表
     *
     * @param member 成员信息
     * @return 成员信息集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增成员信息
     *
     * @param member 成员信息
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改成员信息
     *
     * @param member 成员信息
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除成员信息
     *
     * @param ids 需要删除的成员信息主键集合
     * @return 结果
     */
    public int deleteMemberByIds(Long[] ids);

    /**
     * 删除成员信息信息
     *
     * @param id 成员信息主键
     * @return 结果
     */
    public int deleteMemberById(Long id);


    public List<MemberExport> selectMemberListExport(Member member);

    String importMember(List<MemberExport> memberList, boolean updateSupport, String operName);


    List<Integer> getPowerChart();

    List<PowerBetweenVo> getPowerBetween();

    List<String> getBetweenPower();

    List<Integer> getBetweenPowerCount(List<String> between);
}
