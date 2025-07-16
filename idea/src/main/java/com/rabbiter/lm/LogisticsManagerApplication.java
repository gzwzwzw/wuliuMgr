package com.rabbiter.lm;

import com.rabbiter.lm.handler.StartupExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.util.ObjectUtils;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class LogisticsManagerApplication {
    public static void main(String[] args) {
        try {
            SpringApplication application = new SpringApplication(LogisticsManagerApplication.class);
            application.addListeners(new StartupExceptionHandler());
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
