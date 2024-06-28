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

@Component("bastionTask")
public class BastionTask extends AbstractEmailTask implements ActivityNotify {


    @Autowired
    private IMemberService memberService;


    @Override
    public void notifyTodayEvent(String subject, String time) {
        //获取可以参加要塞资格的组员
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        member.setType(MemberType.MEMBER.getCode());
        List<Member> memberList = memberService.selectMemberList(member);
        //生成邮件标题
        String emailSubject = Constants.ACTIVITY_TODAY + subject + Constants.COME_ON;

        //入队等待消费
        List<MemberEmailTask> tasks = new ArrayList<>();
        for (Member m : memberList) {
            String text = generateNotifyEmailContent(m.getNickname(), subject, time);
            tasks.add(new MemberEmailTask(m.getQq() + "@qq.com", emailSubject, subject, text));
        }
        pushTasksToRedis(tasks);
        // 手动启动任务
        startScheduledTask();
    }

    @Override
    public void notifyEventActivation(String subject, String time) {
        //获取可以参加要塞资格的组员
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        member.setType(MemberType.MEMBER.getCode());
        List<Member> memberList = memberService.selectMemberList(member);

        //生成邮件标题
        String emailSubject = Constants.ACTIVITY_UP_LINE + subject + Constants.COME_ON;

        //入队等待消费
        List<MemberEmailTask> tasks = new ArrayList<>();
        for (Member m : memberList) {
            String text = generateNotifyEmailContent(m.getNickname(), subject, time);
            tasks.add(new MemberEmailTask(m.getQq() + Constants.QQ_TAIL, emailSubject, subject, text));
        }
        pushTasksToRedis(tasks);
        // 手动启动任务
        startScheduledTask();
    }
}