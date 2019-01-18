package com.example.jpademo.jpa;

import com.example.jpademo.entity.LbkAd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LbkAdRepository extends JpaRepository<LbkAd, Integer> {

    List<LbkAd> findByStatusEquals(Integer status);
}
