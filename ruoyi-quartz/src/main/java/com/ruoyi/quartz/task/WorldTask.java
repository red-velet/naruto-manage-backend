package com.ruoyi.quartz.task;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.MemberState;
import com.ruoyi.common.enums.MemberType;
import com.ruoyi.quartz.domain.MemberEmailTask;
import com.ruoyi.quartz.task.template.AbstractEmailTask;
import com.ruoyi.quartz.task.template.ActivityNotify;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 天地战场活动 - 今日提示
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */
@Component("worldTask")
public class WorldTask extends AbstractEmailTask implements ActivityNotify {

    @Autowired
    private IMemberService memberService;

    @Override
    public void notifyTodayEvent(String subject, String time) {
        // 提取所有成员的 qq 字段
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        member.setType(MemberType.MEMBER.getCode());
        List<Member> memberList = memberService.selectMemberList(member);

        //获取邮件标题
        String emailSubject = Constants.ACTIVITY_TODAY + subject + Constants.COME_ON;

        //批量存进redis队列，等待消费
        List<MemberEmailTask> tasks = new ArrayList<>();
        for (Member m : memberList) {
            String text = generateNotifyEmailContent(m.getNickname(), subject, time);
            tasks.add(new MemberEmailTask(m.getQq() + Constants.QQ_TAIL, emailSubject, subject, text));
        }
        pushTasksToRedis(tasks);
        //手动启动任务
        startScheduledTask();
    }

    @Override
    public void notifyEventActivation(String subject, String time) {
        // 提取所有成员的 qq 字段
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        member.setType(MemberType.MEMBER.getCode());

        List<Member> memberList = memberService.selectMemberList(member);

        // 计算当前时间距离活动时间的分钟差
        String diffTime = formatTimeDifference(calculateMinutesUntilEvent(time));

        //获取邮件标题
        String emailsSubject = Constants.ACTIVITY_UP_LINE + subject + Constants.COME_ON;

        //批量存进redis队列，等待消费
        List<MemberEmailTask> tasks = new ArrayList<>();
        for (Member m : memberList) {
            String text = generateOnlineEmailContent(m.getNickname(), subject, diffTime);
            tasks.add(new MemberEmailTask(m.getQq() + Constants.QQ_TAIL, emailsSubject, subject, text));
        }
        pushTasksToRedis(tasks);
        // 手动启动任务
        startScheduledTask();
    }
}
