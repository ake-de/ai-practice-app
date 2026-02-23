package com.ai.practice.demo.service;

import com.ai.practice.demo.dto.RoadmapResponse;
import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    private final String apiKey;

    public GeminiService(@Value("${google.api.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    public RoadmapResponse getRoadmap(String topic) {
        try {
            // 1. [해결] 생성자 대신 Builder를 사용하여 Client를 만듭니다.
            // 2026년형 SDK의 가장 올바른 생성 방식입니다.
            Client client = Client.builder()
                .apiKey(apiKey)
                .build();
            
            // 2. [해결] 인자 3개를 정확히 채워줍니다. (모델, 질문, 설정)
            // 특별한 설정이 없으므로 세 번째 인자에는 null을 넣어줍니다.
            var response = client.models.generateContent(
                "gemini-3-flash-preview", 
                topic + " 학습을 위한 5단계 로드맵을 한국어로 아주 상세하게 짜줘.",
                null // Config 인자 추가
            );

            // 3. 결과 텍스트 반환
            return new RoadmapResponse(response.text());

        } catch (Exception e) {
            e.printStackTrace();
            return new RoadmapResponse("Gemini 호출 실패: " + e.getMessage());
        }
    }
}