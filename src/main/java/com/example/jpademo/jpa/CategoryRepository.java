package com.example.jpademo.jpa;

import com.example.jpademo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Modifying
    @Query(value = "update category  hy set " +
            "hy.parent_id = if(:#{#huaYangArea.parentId}!='',:#{#huaYangArea.parentId},hy.parent_id)  ," +
            "hy.name =if(:#{#huaYangArea.name}!='',:#{#huaYangArea.name},hy.name)   ," +
            "hy.status = if(:#{#huaYangArea.status}!='',:#{#huaYangArea.status},hy.status)   ," +
            "hy.sort_order =  if(:#{#huaYangArea.sortOrder}!='',:#{#huaYangArea.sortOrder},hy.sort_order)  " +
            "where hy.id = :#{#huaYangArea.id}",nativeQuery = true)
    int updateByPrimaryKey(@Param("huaYangArea") Category category);

    @Query(value = "SELECT employer_id,employer_name,jointime,phone,card_id FROM employbaseinfo",nativeQuery = true)
    List<Object> select();

}
