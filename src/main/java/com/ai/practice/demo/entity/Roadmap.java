package com.ai.practice.demo.entity;

import jakarta.persistence.*;

@Entity // "이 클래스는 DB 테이블과 연결됩니다!" 선언
public class Roadmap {

    @Id // 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 1, 2, 3 자동 증가
    private Long id;

    private String topic;

    @Column(columnDefinition = "TEXT") // 내용이 기니까 TEXT 타입으로 설정
    private String content;

    // JPA를 위한 기본 생성자 (필수)
    public Roadmap() {}

    public Roadmap(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }

    // Getter 메서드들
    public Long getId() { return id; }
    public String getTopic() { return topic; }
    public String getContent() { return content; }
}