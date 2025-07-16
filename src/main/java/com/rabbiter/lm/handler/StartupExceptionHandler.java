package com.rabbiter.lm.handler;


import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.ObjectUtils;

import java.util.Timer;
import java.util.TimerTask;

public class StartupExceptionHandler implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        // 在启动异常发生时的处理逻辑
        Throwable throwable = event.getException();
        // 进行自定义的处理，例如记录日志或发送通知等
        // 自定义异常处理逻辑
        for (int i = 0; i < 100; ++i) {
            if (ObjectUtils.isEmpty(throwable)) {
                break;
            }
            String message = throwable.getMessage();
            if (message.contains("(using password: YES)")) {
                if (!message.contains("'root'@'")) {
                    show("PU Request failed with status code 500");
                } else if (message.contains("'root'@'localhost'")) {
                    show("P Request failed with status code 500");
                }
            } else if(message.contains("Table") && message.contains("doesn't exist")) {
                show("T Request failed with status code 500");
            } else if (message.contains("Unknown database")) {
                show("U Request failed with status code 500");
            } else if (message.contains("0 milliseconds ago")) {
                show("C Request failed with status code 500");
            } else if (message.contains("SQLSyntaxErrorException")) {
                show("S Request failed with status code 500");
            }
            throwable = throwable.getCause();
        }
    }

    public static void show(String message) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {;
                System.err.println("启动失败：" + message);
            }
        }, 500);
    }
}
