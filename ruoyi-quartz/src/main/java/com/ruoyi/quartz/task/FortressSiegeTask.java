package com.ruoyi.quartz.task;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.MemberState;
import com.ruoyi.common.enums.MemberType;
import com.ruoyi.quartz.task.template.AbstractEmailTask;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component("fortressSiege")
public class FortressSiegeTask extends AbstractEmailTask implements SchedulingConfigurer {


    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private IMemberService memberService;


    // 方法1：今日活动提醒
    public void notifyTodayEvent() {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        List<Member> memberList = memberService.selectMemberList(member);
        String subject = "今日活动提醒! 要塞争夺战来袭!";

        List<MemberEmailTask> tasks = new ArrayList<>();
        for (Member m : memberList) {
            String text = generateEmailContent(m, "20:00");
            tasks.add(new MemberEmailTask(m.getQq() + "@qq.com", subject, text));
        }
        pushTasksToRedis(tasks);
        startScheduledTask(); // 手动启动任务
    }

    // 方法2：活动上线提醒
    public void notifyEventActivation(String memberTime, String studentTime) {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        List<Member> memberList = memberService.selectMemberList(member);
        String subject = "活动上线提醒! 距离要塞争夺战还有" + memberTime + "分钟!";
        String tableSubject = "要塞争夺战上线提醒";
        List<MemberEmailTask> tasks = new ArrayList<>();
        for (Member m : memberList) {
            String text = generateEmailContent(m, memberTime, studentTime);
            tasks.add(new MemberEmailTask(m.getQq() + Constants.QQ_TAIL, subject, tableSubject, text));
        }
        pushTasksToRedis(tasks);
        startScheduledTask(); // 手动启动任务
    }

    private String generateEmailContent(Member member, String memberTime) {
        if (member.getType().equals(MemberType.MEMBER.getCode())) {
            return "<html>" +
                    "<body>" +
                    "<p>亲爱的成员-" + member.getNickname() + "，</p>" +
                    "<p style=\"text-indent: 2em;\">提醒您今天晚上<strong>20:00</strong>有要塞争夺战活动，请记得准时参加。</p>" +
                    "<p style=\"text-indent: 2em;\">同时，要塞结束后还有叛忍来袭活动。</p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
        } else {
            return "<html>" +
                    "<body>" +
                    "<p>亲爱的学员-" + member.getNickname() + "，</p>" +
                    "<p style=\"text-indent: 2em;\">提醒您今天晚上有叛忍来袭活动，请多多参加组织活动，有机会转正。</p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
        }
    }

    private String generateEmailContent(Member member, String memberTime, String studentTime) {
        if (member.getType().equals(MemberType.MEMBER.getCode())) {
            return "<html>" +
                    "<body>" +
                    "<p>亲爱的成员-" + member.getNickname() + "，</p>" +
                    "<p style=\"text-indent: 2em;\">距离要塞争夺战活动还有<strong>" + memberTime + "分钟</strong>，请记得准时参加。</p>" +
                    "<p style=\"text-indent: 2em;\">同时，要塞结束后还有叛忍来袭活动(叛忍开自动)。</p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
        } else {
            return "<html>" +
                    "<body>" +
                    "<p>亲爱的学员-" + member.getNickname() + "，</p>" +
                    "<p style=\"text-indent: 2em;\">距离今天晚上叛忍来袭活动还有<strong>" + studentTime + "小时</strong>，请多多参加组织活动，有机会转正。</p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
        }
    }


}