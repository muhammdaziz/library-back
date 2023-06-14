package com.example.libraryback.repository;

import com.example.libraryback.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
    Optional<Recommendation> findByTitle(String title);

    @Query(value = "SELECT * FROM recommendation WHERE title = :title AND id IS NOT :id", nativeQuery = true)
    Optional<Recommendation> findByTitleNotId(String title, Integer id);

    boolean existsByTitle(String title);
}
