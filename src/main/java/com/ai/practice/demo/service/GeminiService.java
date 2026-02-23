package com.ai.practice.demo.service;

import com.ai.practice.demo.dto.RoadmapResponse;
import com.ai.practice.demo.entity.Roadmap;
import com.ai.practice.demo.repository.RoadmapRepository;
import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeminiService {

    private final RoadmapRepository roadmapRepository;

    private final String apiKey;

    public GeminiService(RoadmapRepository roadmapRepository, @Value("${google.api.key}") String apiKey) {
        this.roadmapRepository = roadmapRepository;
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

            String content = response.text();

            // 2. DB에 저장하기 위해 Entity 객체 생성
            Roadmap roadmap = new Roadmap(topic, content);

            // 3. DB에 저장! (JPA가 알아서 INSERT SQL을 날려줍니다)
            roadmapRepository.save(roadmap);

            // 4. 사용자에게 반환
            return new RoadmapResponse(content);

        } catch (Exception e) {
            e.printStackTrace();
            return new RoadmapResponse("Gemini 호출 실패: " + e.getMessage());
        }
    }

    public List<Roadmap> getAllRoadmaps() {
        return roadmapRepository.findAll(); 
    }
}