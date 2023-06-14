package com.example.libraryback.payload.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookAddDTO {

    private Float price;
    private String isbn;
    private String title;
    private String language;
    private String publisher;
    private Integer authorId;
    private String description;
    @DateTimeFormat(pattern ="dd/MM/yyyy")
    private Date publishedDate;
    private MultipartFile image;
    private List<Integer> genres;
    private String editionFormat;
    private MultipartFile document;
}
