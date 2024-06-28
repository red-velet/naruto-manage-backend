package com.ruoyi.quartz.task;

import com.ruoyi.common.enums.MemberState;
import com.ruoyi.system.domain.Member;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/21
 */
@Component("emailTest")
public class EmailTestTask {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


    // 方法1：今日活动提醒
    public void test(String activityName, String activityTime) throws MessagingException {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        // 提取所有成员的 qq 字段
        List<String> memberList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            memberList.add("969467050");
            memberList.add("velet7");
            memberList.add("3050775465");
        }
        String subject = "今日活动提醒! " + activityName + "来袭!";
        String text;
        for (String m : memberList) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            text = "<html>" +
                    "<body>" +
                    "<p>亲爱的组员" + System.currentTimeMillis() + "-" + m + "，</p>" +
                    "<p style=\"text-indent: 2em;\">提醒您今天<strong>" + activityTime + "</strong>有" + activityName + "活动，请记得准时参加。</p>" +
                    "<p><em>祝好，</em></p>" +
                    "<p><em>一花一世界组织</em></p>" +
                    "</body>" +
                    "</html>";
            sendHtmlEmail(m + "@qq.com", subject, text);
        }
    }


    public void test1(String activityName, String activityTime) throws MessagingException {
        Member member = new Member();
        member.setState(MemberState.IN_ORGANIZATION.getCode());
        // 提取所有成员的 qq 字段
        List<String> memberList = new ArrayList<>();
        memberList.add("969467050");
        memberList.add("velet7");
        memberList.add("3050775465");
        String subject = "今日活动提醒! " + activityName + "来袭!";
        String text;
        for (String m : memberList) {
            text = "你好";
            sendEmail(m + "@qq.com", subject, text);
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

    private void sendEmail(String to, String subject, String content) throws MessagingException, MessagingException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
}
