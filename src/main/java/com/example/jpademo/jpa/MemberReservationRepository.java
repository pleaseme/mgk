package com.example.jpademo.jpa;

import com.example.jpademo.entity.MemberReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberReservationRepository extends JpaRepository<MemberReservation, Integer> {
}
