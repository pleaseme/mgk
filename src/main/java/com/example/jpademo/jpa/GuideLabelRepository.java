package com.example.jpademo.jpa;


import com.example.jpademo.entity.GuideLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuideLabelRepository extends JpaRepository<GuideLabel, Integer> {
}
