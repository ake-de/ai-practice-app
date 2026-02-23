package com.ai.practice.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.practice.demo.dto.RoadmapResponse;
import com.ai.practice.demo.service.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/roadmap")
public class RoadmapController {

    private final GeminiService geminiService;

    public RoadmapController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }
    
    // 브라우저에서 /api/roadmap?topic=자바 라고 검색하면 작동합니다.
    @GetMapping
    public RoadmapResponse generateRoadmap(@RequestParam String topic) {
        return geminiService.getRoadmap(topic);
    }
    
}
