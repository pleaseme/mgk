package com.example.jpademo.jpa;



import com.example.jpademo.entity.LbkMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<LbkMember, Integer> {

    List<LbkMember> findByNicknameEquals(String nickname);

    List<LbkMember> findByIdEquals(Integer id);
}
