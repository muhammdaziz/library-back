package com.example.libraryback.repository;

import com.example.libraryback.entity.Featured;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeaturedRepository extends JpaRepository<Featured, Integer> {

    boolean existsByBookId(UUID bookId);

    List<Featured> findByOrderByOrderNumDesc(PageRequest of);
}
