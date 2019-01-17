package com.example.jpademo.jpa;


import com.example.jpademo.entity.Employbaseinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmploybaseinfoRepository extends JpaRepository<Employbaseinfo, Integer> {

    @Modifying
    @Query(value = "update employbaseinfo hy set " +
            "hy.employer_id = if(:#{#huaYangArea.employerId}!='',:#{#huaYangArea.employerId},hy.employer_id)  ," +
            "hy.employer_name = if(:#{#huaYangArea.employerName}!='',:#{#huaYangArea.employerName},hy.employer_name)  ," +
            "hy.sex =if(:#{#huaYangArea.sex}!='',:#{#huaYangArea.sex},hy.sex)   ," +
            "hy.remark = if(:#{#huaYangArea.remark}!='',:#{#huaYangArea.remark},hy.remark)   ," +
            "hy.phone =  if(:#{#huaYangArea.phone}!='',:#{#huaYangArea.phone},hy.phone)  " +
            "where hy.id = :#{#huaYangArea.id}",nativeQuery = true)
    int update(@Param("huaYangArea") Employbaseinfo employbaseinfo);

   List<Employbaseinfo> findByIdEquals(Integer id);







}
