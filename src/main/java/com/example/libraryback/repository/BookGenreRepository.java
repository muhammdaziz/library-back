package com.example.libraryback.repository;

import com.example.libraryback.entity.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Long> {
    List<BookGenre> findAllByBookId(UUID bookId);
}
