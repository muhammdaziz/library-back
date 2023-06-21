package com.example.libraryback.repository;

import com.example.libraryback.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, UUID> {

    @Query(value = "SELECT * FROM website LIMIT 1", nativeQuery = true)
    Optional<Website> find();
}
