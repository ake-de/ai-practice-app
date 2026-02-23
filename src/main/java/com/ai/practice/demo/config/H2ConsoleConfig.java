package com.ai.practice.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.beans.factory.annotation.Value;
import java.sql.SQLException;

@Configuration
public class H2ConsoleConfig {

    // 스프링 부트가 켜질 때 H2 웹 서버(콘솔)를 강제로 같이 실행시킵니다.
    @EventListener(ApplicationReadyEvent.class)
    public void startH2Console() throws SQLException {
        // 도커 외부 접속 허용(-webAllowOthers) 및 포트 8082 강제 오픈
        org.h2.tools.Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("✅ H2 DB 콘솔이 8082 포트에서 강제로 켜졌습니다!");
    }
}