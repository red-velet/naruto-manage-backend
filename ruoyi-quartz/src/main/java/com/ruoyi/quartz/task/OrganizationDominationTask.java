package com.ruoyi.quartz.task;

import com.ruoyi.common.enums.MemberState;
import com.ruoyi.common.enums.MemberType;
import com.ruoyi.system.domain.Member;
import com.ruoyi.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * <p>
 * 组织争霸赛活动
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */
@Component("organizationDomination")
public class OrganizationDominationTask {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private IMemberService memberService;

    // 方法1：今日活动提醒
    public void notifyTodayEvent() throws MessagingException {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        member.setType(MemberType.MEMBER.getCode());
        // 提取所有成员的 qq 字段
        List<Member> memberList = memberService
                .selectMemberList(member);
        String subject = "今日活动提醒! 天地战场来袭!";
        String text;
        for (Member m : memberList) {
            text = "<html>" +
                    "<body>" +
                    "<p>亲爱的成员-" + m.getNickname() + "，</p>" +
                    "<p style=\"text-indent: 2em;\">提醒您今天晚上<strong>21:00</strong>有组织争霸赛活动，请记得准时参加。</p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
            sendHtmlEmail(m.getQq() + "@qq.com", subject, text);
        }
    }

    // 方法2：活动上线提醒
    public void notifyEventActivation() throws MessagingException {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        member.setType(MemberType.MEMBER.getCode());
        // 提取所有成员的 qq 字段
        List<Member> memberList = memberService
                .selectMemberList(member);
        String subject = "活动上线提醒! 距离天地战场还有30分钟!";
        String text;
        // 计算当前时间距离 21:00 的分钟差
        LocalTime now = LocalTime.now();
        LocalTime eventTime = LocalTime.of(21, 0); // 目标时间为每天的21:00
        long minutesUntilEvent = now.until(eventTime, ChronoUnit.MINUTES);
        for (Member m : memberList) {
            text = "<html>" +
                    "<body>" +
                    "<p>亲爱的成员-" + m.getNickname() + "，</p>" +
                    "<p style=\"text-indent: 2em;\">距离组织争霸活动还有<strong>" + minutesUntilEvent + "分钟</strong>，请勿缺席! 准时参加!</p>" +
                    "<p style=\"text-indent: 2em;\"><strong>多多杀敌</strong></p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
            sendHtmlEmail(m.getQq() + "@qq.com", subject, text);
        }
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}
