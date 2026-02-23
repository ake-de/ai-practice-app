package com.ai.practice.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.practice.demo.dto.RoadmapResponse;
import com.ai.practice.demo.service.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/roadmap")
public class RoadmapController {

    private final GeminiService geminiService;
    private final String appPasscode; // 서버의 비밀번호를 담을 변수

    public RoadmapController(GeminiService geminiService, 
                             @Value("${app.passcode}") String appPasscode) {
        this.geminiService = geminiService;
        this.appPasscode = appPasscode;
    }
    
    // 브라우저에서 /api/roadmap?topic=자바 라고 검색하면 작동합니다.
    @GetMapping
    public RoadmapResponse generateRoadmap(@RequestParam String topic,
                                           @RequestParam String passcode) {
        
        if (!appPasscode.equals(passcode)) {
            return new RoadmapResponse("🚨 접근 거부: 올바른 비밀번호를 입력해주세요.");
        }

        // 2. 비밀번호가 맞으면 정상적으로 AI에게 로드맵 생성을 요청합니다.
        return geminiService.getRoadmap(topic);
    }
    
}
