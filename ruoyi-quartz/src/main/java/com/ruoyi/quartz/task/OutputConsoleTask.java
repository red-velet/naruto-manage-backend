package com.ruoyi.quartz.task;

import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author red-velvet
 * @since 2024/6/20
 */
@Component("output")
public class OutputConsoleTask {
    public void opConsole() {
        System.out.println("hello world==============");
    }
}
