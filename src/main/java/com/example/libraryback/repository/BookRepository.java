package com.example.libraryback.repository;

import com.example.libraryback.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query(value = "SELECT price FROM book where id =?", nativeQuery = true)
    Float findPriceById(Long id);

    @Query(value = "SELECT path FROM file_img where id =?", nativeQuery = true)
    String findPathById(UUID id);

//    JOIN author as a
//     a.firstname, a.lastname,
    @Query(value = "SELECT * FROM book WHERE to_tsvector('english', title) @@ to_tsquery('english', ?1)", nativeQuery = true)
    Page<Book> search(String keyword, Pageable pageable);

    Optional<Book> findByIdAndDeletedFalse(UUID id);

    List<Book> findAllByDeletedFalse(Pageable pageable);

    Optional<Book> findByTitleAndAuthorId(String title, Integer authorId);

    boolean existsByTitleAndAuthorId(String title, Integer authorId);

    @Query(value = "SELECT * from book WHERE id  IN(SELECT book_id from recommendation_book WHERE recommendation_id = ?)", nativeQuery = true)
    List<Book> findAllByRecommendationId(Integer recommendationId);

    boolean existsAllByIdIn(List<UUID> books);

    Page<Book> findByTitleContainingIgnoreCaseOrPublisherContainingIgnoreCase(String value1, String value2, PageRequest of);

//    List<Book> findAllByDeletedFalseAndCategoryId(Pageable pageable, Integer categoryId);

//    @Query(value = "SELECT * from book WHERE deleted = false ORDER BY created_at DESC LIMIT 5",nativeQuery = true)
//    List<Book> findAllNewBooks();

//    @Query(value = "SELECT (SELECT count(*) FROM book WHERE deleted = false)", nativeQuery = true)
//    Double findBooksCount();

//    @Query(value = "SELECT (SELECT count(*) FROM book WHERE deleted = false AND category_id = :categoryId)", nativeQuery = true)
//    Double findBooksCountByCategory(Integer categoryId);

//    boolean existsByTitleAndAuthorId(String title, UUID authorId);
}
