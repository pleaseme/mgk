package com.example.jpademo.jpa;


import com.example.jpademo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Modifying
    @Query("update Cart hy set " +
            "hy.userId = CASE WHEN :#{#cart.userId} IS NULL THEN hy.userId ELSE :#{#cart.userId} END ," +
            "hy.productId = CASE WHEN :#{#cart.productId} IS NULL THEN hy.productId ELSE :#{#cart.productId} END ," +
            "hy.quantity = CASE WHEN :#{#cart.quantity} IS NULL THEN hy.quantity ELSE :#{#cart.quantity} END ," +
            "hy.checked =  CASE WHEN :#{#cart.checked} IS NULL THEN hy.checked ELSE :#{#cart.checked} END " +
            "where hy.id = :#{#cart.id}")
    int updateByPrimaryKeySelective(@Param("cart") Cart category);





}
