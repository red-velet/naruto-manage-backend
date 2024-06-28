package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 成员信息Mapper接口
 *
 * @author shawn
 * @date 2024-06-19
 */
public interface MemberMapper {
    /**
     * 查询成员信息
     *
     * @param id 成员信息主键
     * @return 成员信息
     */
    public Member selectMemberById(Long id);

    public Member selectMemberByNId(Long nId);


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
     * 删除成员信息
     *
     * @param id 成员信息主键
     * @return 结果
     */
    public int deleteMemberById(Long id);

    /**
     * 批量删除成员信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberByIds(Long[] ids);

    public Member selectMemberByNickName(@Param("nickName") String nickName);

    int countUsersByPowerRange(@Param("start") Integer start, @Param("end") Integer end);

    int findMaxPower();

    int findMinPower();
}
