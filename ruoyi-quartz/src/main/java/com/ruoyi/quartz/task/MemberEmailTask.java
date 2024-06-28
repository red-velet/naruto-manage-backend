package com.ruoyi.quartz.task;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/21
 */
public class MemberEmailTask {
    private String to;
    private String emailSubject;
    private String htmlContent;

    private String subject;

    public MemberEmailTask(String to, String subject, String htmlContent) {
        this.to = to;
        this.emailSubject = subject;
        this.htmlContent = htmlContent;
    }

    public MemberEmailTask(String to, String subject, String tableSubject, String htmlContent) {
        this.to = to;
        this.emailSubject = subject;
        this.htmlContent = htmlContent;
        this.subject = tableSubject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
