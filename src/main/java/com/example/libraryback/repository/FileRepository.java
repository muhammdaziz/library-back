package com.example.libraryback.repository;

import com.example.libraryback.entity.FileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<FileImg, UUID> {
}
