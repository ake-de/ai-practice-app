package com.ai.practice.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.practice.demo.dto.RoadmapResponse;
import com.ai.practice.demo.service.GeminiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    // 1. 데이터를 담을 튼튼한 가방(DTO) 역할을 하는 Record를 만듭니다. (Java 최신 문법)
    public record RoadmapRequest(String topic, String passcode) {}

    // 2. GetMapping 대신 PostMapping을 사용합니다! URL이 아주 깔끔해집니다.
    @PostMapping("/api/roadmap")
    public RoadmapResponse getRoadmap(@RequestBody RoadmapRequest request) { 
        // @RequestBody는 "HTTP 가방(Body) 안에 있는 JSON을 열어서 꺼내줘!"라는 뜻입니다.
        
        // 3. 가방에서 꺼낸 비밀번호(request.passcode())를 확인합니다.
        if (!appPasscode.equals(request.passcode())) {
            return new RoadmapResponse("🚨 접근 거부: 올바른 비밀번호를 입력해주세요.");
        }
        
        // 4. 비밀번호가 맞으면 가방에서 주제(request.topic())를 꺼내 AI에게 전달합니다.
        return geminiService.getRoadmap(request.topic());
    }
    
}
