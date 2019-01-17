package com.example.jpademo.jpa;

import com.example.jpademo.entity.GuideInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideInfoRepository extends JpaRepository<GuideInfo, Integer> {
}
