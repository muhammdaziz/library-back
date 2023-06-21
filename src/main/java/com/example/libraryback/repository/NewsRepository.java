package com.example.libraryback.repository;

import com.example.libraryback.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
    boolean existsByTitleAndAuthorId(String title, UUID authorId);

    @Query(nativeQuery = true, value = "SELECT * from news where id != :id AND title = :title AND author_id = :authorId")
    boolean existsByTitleAndAuthorIdNotId(UUID id, String title, UUID authorId);

}
