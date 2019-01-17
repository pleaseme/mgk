package com.example.jpademo.jpa;

import com.example.jpademo.entity.PlanLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanLabelRepository extends JpaRepository<PlanLabel, Integer> {
}
