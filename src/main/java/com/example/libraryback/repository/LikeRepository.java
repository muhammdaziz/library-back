package com.example.libraryback.repository;

import com.example.libraryback.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, UUID> {

    Integer countByBookId(UUID bookId);
}
