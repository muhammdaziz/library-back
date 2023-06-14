package com.example.libraryback.repository;

import com.example.libraryback.entity.RecommendationBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationBookRepository extends JpaRepository<RecommendationBook, Integer> {

}
