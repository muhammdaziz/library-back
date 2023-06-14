package com.example.libraryback.repository;

import com.example.libraryback.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    boolean existsByValue(Float value);

    @Query(nativeQuery = true, value = "SELECT * from discount where id != :id AND value = :value")
    boolean existsByValueNotId(Float value, Integer id);
}
