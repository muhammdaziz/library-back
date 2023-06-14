package com.example.libraryback.repository;

import com.example.libraryback.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    boolean existsByName(String name);

    boolean existsAllByIdIn(List<Integer> genres);

    @Query(nativeQuery = true, value = "SELECT * from genre where id != :id AND name = :name")
    boolean existsByNameNotId(String name, Integer id);
}
