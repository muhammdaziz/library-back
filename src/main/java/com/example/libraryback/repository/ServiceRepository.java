package com.example.libraryback.repository;

import com.example.libraryback.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {

    boolean existsByTitle(String title);

    @Query(nativeQuery = true, value = "SELECT * from services where id != :id AND title = :title")
    Optional<Services> findByTitleAndNotId(String title, Integer id);

}
