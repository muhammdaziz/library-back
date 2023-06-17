package com.example.libraryback.repository;

import com.example.libraryback.entity.Offer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    boolean existsByBookId(UUID bookId);

    @Query(nativeQuery = true, value = "SELECT * from offer where id != :offerId AND book_id = :bookId")
    boolean existsByBookIdNotId(UUID bookId, UUID offerId);

    List<Offer> findByOrderByOrderNumDesc(Pageable pageable);
}
