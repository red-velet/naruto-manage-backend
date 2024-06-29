package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.MemberState;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.domain.MemberExport;
import com.ruoyi.system.domain.vo.PowerBetweenVo;
import com.ruoyi.system.mapper.MemberMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.IMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 成员信息Service业务层处理
 *
 * @author shawn
 * @date 2024-06-19
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * 查询成员信息
     *
     * @param id 成员信息主键
     * @return 成员信息
     */
    @Override
    public Member selectMemberById(Long id) {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询成员信息列表
     *
     * @param member 成员信息
     * @return 成员信息
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增成员信息
     *
     * @param member 成员信息
     * @return 结果
     */
    @Override
    public int insertMember(Member member) {
        //检查是否已注册
        Member mb = memberMapper.selectMemberByNId(Long.parseLong(member.getnId()));
        if (mb != null) {
            return -1;
        }
        int res = memberMapper.insertMember(member);
        //为用户注册系统登录账号
        assignedAccount(member);
        return res;
    }

    public void assignedAccount(Member member) {
        //添加用户信息
        SysUser user = new SysUser();
        user.setNickName(member.getNickname());
        user.setUserName(member.getnId());
        user.setPassword(Constants.DEFAULT_PWD);
        user.setEmail(member.getQq() + "@qq.com");
        userMapper.insertUser(user);

        //为用户分配角色
        userRoleMapper.insertUserRole(user.getUserId(), Constants.ROLE_MEMBER);
    }

    /**
     * 修改成员信息
     *
     * @param member 成员信息
     * @return 结果
     */
    @Override
    public int updateMember(Member member) {
        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除成员信息
     *
     * @param ids 需要删除的成员信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(Long[] ids) {
        return memberMapper.deleteMemberByIds(ids);
    }

    /**
     * 删除成员信息信息
     *
     * @param id 成员信息主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id) {
        return memberMapper.deleteMemberById(id);
    }

    @Override
    public List<MemberExport> selectMemberListExport(Member member) {
        List<Member> members = memberMapper.selectMemberList(member);
        // 将Member列表转换为MemberExport列表
        List<MemberExport> memberExportList = new ArrayList<>();
        for (Member m : members) {
            MemberExport export = new MemberExport();
            export.setId(m.getId());
            export.setnId(m.getnId());
            export.setNickname(m.getNickname());
            export.setType(convertType(m.getType()));
            export.setState(convertState(m.getState()));
            export.setPower(m.getPower());
            export.setQq(m.getQq());
            export.setJoinTime(m.getJoinTime());
            export.setCreatedTime(m.getCreatedTime());
            export.setUpdatedTime(m.getUpdatedTime());
            memberExportList.add(export);
        }
        return memberExportList;
    }

    // 类型转换方法
    private String convertType(Long type) {
        if (type == null) return null;
        switch (type.intValue()) {
            case 0:
                return "成员";
            case 1:
                return "学员";
            default:
                return "未知";
        }
    }

    // 状态转换方法
    private String convertState(Long state) {
        if (state == null) return null;
        switch (state.intValue()) {
            case 0:
                return "仍在组织";
            case 1:
                return "离开组织";
            default:
                return "未知";
        }
    }

    @Autowired
    protected Validator validator;
    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public String importMember(List<MemberExport> memberList, boolean updateSupport, String operName) {

        if (StringUtils.isNull(memberList) || memberList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (MemberExport member : memberList) {
            try {
                // 验证是否存在这个用户
                Member m = memberMapper.selectMemberByNickName(member.getNickname());
                if (StringUtils.isNull(m)) {
                    BeanValidators.validateWithException(validator, member);
                    Member mb = new Member();
                    mb.setId(m.getId());
                    mb.setnId(m.getnId());
                    mb.setNickname(m.getNickname());
                    mb.setType(convertTypeToLong(member.getType()));
                    mb.setState(convertStateToLong(member.getState()));
                    mb.setPower(m.getPower());
                    mb.setQq(m.getQq());
                    mb.setJoinTime(m.getJoinTime());
                    mb.setCreatedTime(m.getCreatedTime());
                    mb.setUpdatedTime(new Date());
                    memberMapper.insertMember(mb);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + member.getNickname() + " 导入成功");
                } else if (updateSupport) {
                    BeanValidators.validateWithException(validator, member);
                    Member mb = new Member();
                    mb.setId(m.getId());
                    mb.setnId(m.getnId());
                    mb.setNickname(m.getNickname());
                    mb.setType(convertTypeToLong(member.getType()));
                    mb.setState(convertStateToLong(member.getState()));
                    mb.setPower(m.getPower());
                    mb.setQq(m.getQq());
                    mb.setJoinTime(m.getJoinTime());
                    mb.setCreatedTime(m.getCreatedTime());
                    mb.setUpdatedTime(new Date());
                    memberMapper.updateMember(mb);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + member.getNickname() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + member.getNickname() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + member.getNickname() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    @Override
    public List<Integer> getPowerChart() {
        List<String> betweenPower = generateIntervals();
        List<Integer> res = new ArrayList<>();
        for (String seriesDatum : betweenPower) {
            String[] parts = seriesDatum.split("-");
            Integer start = Integer.parseInt(parts[0]);
            Integer end = Integer.parseInt(parts[1]);
            int count = memberMapper.countUsersByPowerRange(start, end);
            res.add(count);
        }
        return res;
    }

    @Override
    public List<PowerBetweenVo> getPowerBetween() {
        // 生成区间
        List<String> intervals = generateIntervals();
        List<PowerBetweenVo> result = new ArrayList<>();
        //查询每个战力区间的人数
        for (String interval : intervals) {
            PowerBetweenVo pb = new PowerBetweenVo();
            pb.setName(interval);
            String[] split = interval.split("-");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1].replace("w", ""));
            pb.setCount(memberMapper.countUsersByPowerRange(start, end));
            result.add(pb);
        }

        return result;
    }

    @Override
    public List<String> getBetweenPower() {
        // 生成区间
        return generateIntervals();
    }

    @Override
    public List<Integer> getBetweenPowerCount(List<String> between) {
        List<Integer> counts = new ArrayList<>();

        //查询每个战力区间的人数
        for (String interval : between) {
            String[] split = interval.split("-");
            int start = Integer.parseInt(split[0]);
            int end = Integer.parseInt(split[1].replace("w", ""));
            counts.add(memberMapper.countUsersByPowerRange(start, end));
        }

        return counts;
    }

    @Override
    public List<Member> selectMemberListAll() {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        return memberMapper.selectMemberList(member);
    }

    private List<String> generateIntervals() {
        // 向下取整最小战力值到最接近的50或100的倍数


        int roundedMinPower = roundDown(memberMapper.findMinPower());

        // 向上取整最大战力值到最接近的50或100的倍数
        int roundedMaxPower = roundUp(memberMapper.findMaxPower());


        // 根据取整后的最小和最大战力值，以50为间隔生成区间
        List<String> intervals = new ArrayList<>();
        for (int i = roundedMinPower; i < roundedMaxPower; i += 50) {
            intervals.add(i + "-" + (i + 50));
        }
        return intervals;

    }

    private static int roundUp(int power) {
        return ((power + 49) / 50) * 50; // 向上取整到最接近的50的倍数
    }

    private static int roundDown(int power) {
        return (power / 50) * 50; // 向下取整到最接近的50的倍数
    }

//    private static int roundUp(int power) {
//        return (int) Math.ceil(power / 100.0) * 100;
//    }

    // 类型字符串转换回 Long 类型
    private Long convertTypeToLong(String type) {
        if (type == null) return null;
        switch (type) {
            case "成员":
                return 0L;
            case "学员":
                return 1L;
            default:
                return -1L; // 或者返回null或抛出异常，取决于你的业务逻辑
        }
    }

    // 状态字符串转换回 Long 类型
    private Long convertStateToLong(String state) {
        if (state == null) return null;
        switch (state) {
            case "仍在组织":
                return 0L;
            case "离开组织":
                return 1L;
            default:
                return -1L; // 或者返回null或抛出异常，取决于你的业务逻辑
        }
    }
}
