package com.ai.practice.demo.repository;

import com.ai.practice.demo.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<다룰 엔티티, ID의 타입>
public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {
    // 끝입니다! 이 한 줄로 저장(save), 조회(findAll) 기능이 모두 자동 생성됩니다.
}