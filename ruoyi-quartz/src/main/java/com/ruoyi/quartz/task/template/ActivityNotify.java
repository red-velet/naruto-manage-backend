package com.ruoyi.quartz.task.template;

/**
 * <p>
 * 活动通知
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/28
 */
public interface ActivityNotify {
    /**
     * 今日活动通知
     *
     * @param subject
     * @param time
     */
    public void notifyTodayEvent(String subject, String time);

    /**
     * 活动上线通知
     *
     * @param subject
     * @param time
     */

    public void notifyEventActivation(String subject, String time);
}
