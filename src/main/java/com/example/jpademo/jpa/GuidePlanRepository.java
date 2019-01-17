package com.example.jpademo.jpa;

import com.example.jpademo.entity.GuidePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuidePlanRepository extends JpaRepository<GuidePlan, Integer> {
}
