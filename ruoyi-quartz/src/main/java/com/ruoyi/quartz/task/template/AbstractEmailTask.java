package com.ruoyi.quartz.task.template;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.quartz.domain.MemberEmailTask;
import com.ruoyi.system.domain.EmailRecord;
import com.ruoyi.system.service.IEmailRecordService;
import com.ruoyi.system.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 抽象为 生成邮件、发送邮件、定时分批发送（qq邮件限制）、发送状态记录  模板
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/28
 */
@Component("abstractEmailTask")
public class AbstractEmailTask implements SchedulingConfigurer {
    private static final int BATCH_SIZE = 3;
    private static boolean isRunning = false; // 标志任务是否在运行
    private static final ReentrantLock lock = new ReentrantLock();

    @Resource
    protected JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    protected String fromEmail;

    @Value("${naruto.clubName}")
    protected String clubName;

    @Autowired
    protected IMemberService memberService;

    @Autowired
    protected RedisCache redisCache;

    @Autowired
    protected IEmailRecordService emailRecordService;

    // 手动启动定时任务的方法
    public void startScheduledTask() {
        isRunning = true;
    }

    // 停止定时任务的方法
    public void stopScheduledTask() {
        isRunning = false;
    }

    protected void pushTasksToRedis(List<MemberEmailTask> tasks) {
        for (MemberEmailTask task : tasks) {
            redisCache.lRightPush("emailQueue", task);
        }
        startScheduledTask(); // 确保有新任务时重新启动调度任务
    }

    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void sendEmailsFromQueue() {
        if (!isRunning || !lock.tryLock()) {
            return; // 如果任务已停止或锁未获得，则不执行
        }

        try {
            List<MemberEmailTask> tasks = new ArrayList<>();
            for (int i = 0; i < BATCH_SIZE; i++) {
                MemberEmailTask task = redisCache.lLeftPop("emailQueue");
                if (task != null) {
                    tasks.add(task);
                } else {
                    break;
                }
            }

            if (tasks.isEmpty()) {
                stopScheduledTask(); // 队列为空时停止任务
                return;
            }

            for (MemberEmailTask task : tasks) {
                EmailRecord emailRecord = new EmailRecord();
                try {
                    emailRecord.setFromMember(fromEmail);
                    emailRecord.setToMember(task.getTo());
                    emailRecord.setSubject(task.getSubject());
                    emailRecord.setSendTime(new Date());
                    sendHtmlEmail(task.getTo(), task.getEmailSubject(), task.getHtmlContent());
                    emailRecord.setState(0);
                } catch (Exception e) {
                    // 处理邮件发送异常
                    e.printStackTrace();
                    emailRecord.setState(1);
                    emailRecord.setReason(e.getMessage().substring(0, 200));
                } finally {
                    emailRecordService.insertEmailRecord(emailRecord);
                }
            }

            if (!redisCache.hasKey("emailQueue")) {
                stopScheduledTask(); // 如果队列中已没有任务，则停止任务
            }
        } finally {
            lock.unlock();
        }
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(fromEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                this::sendEmailsFromQueue,
                triggerContext -> {
                    if (isRunning) {
                        return new CronTrigger("0 * * * * *").nextExecutionTime(triggerContext);
                    } else {
                        return null;
                    }
                }
        );
    }

    protected String generateNotifyEmailContent(String nickName, String activityName, String activityTime) {
        return "<html>" +
                "<body>" +
                "<p>亲爱的组员-" + nickName + "，</p>" +
                "<p style=\"text-indent: 2em;\">提醒您今天<strong>" + activityTime + "</strong>有" + activityName + "活动，请记得准时参加。</p>" +
                "<p><em>祝好，</em></p>" +
                "<p><em>" + clubName + "组织</em></p>" +
                "</body>" +
                "</html>";
    }

    protected String generateOnlineEmailContent(String nickName, String activityName, String activityTime) {
        return "<html>" +
                "<body>" +
                "<p>亲爱的成员-" + nickName + "，</p>" +
                "<p style=\"text-indent: 2em;\">距离" + activityName + "活动还有<strong>" + activityTime + "</strong>，请勿缺席! 准时参加!</p>" +
                "<p><em>祝好，</em></p>" +
                "<p><em>" + clubName + "组织</em></p>" +
                "</body>" +
                "</html>";
    }

    public long calculateMinutesUntilEvent(String activityTime) {
        try {
            // 验证并解析活动时间
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime eventTime = LocalTime.parse(activityTime, timeFormatter);

            // 获取当前时间
            LocalDateTime now = LocalDateTime.now();

            // 计算活动时间的日期时间
            LocalDateTime eventDateTime = now.with(eventTime);

            if (now.isAfter(eventDateTime)) {
                // 如果当前时间晚于活动时间，计算到第二天同一时间的分钟差
                eventDateTime = eventDateTime.plusDays(1);
            }

            // 计算分钟差
            long minutesUntilEvent = Duration.between(now, eventDateTime).toMinutes();
            return minutesUntilEvent;
        } catch (Exception e) {
            // 处理时间解析异常
            e.printStackTrace();
            return -1; // 返回一个特殊值表示出错
        }
    }

    public String formatTimeDifference(long minutes) {
        if (minutes < 0) {
            return "时间计算出错";
        }
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;
        if (hours > 0) {
            return hours + "小时" + remainingMinutes + "分钟";
        } else {
            return remainingMinutes + "分钟";
        }
    }
}


