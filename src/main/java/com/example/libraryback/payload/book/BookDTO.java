package com.example.libraryback.payload.book;

import com.example.libraryback.payload.author.AuthorDTO;
import com.example.libraryback.payload.discount.DiscountDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    private UUID id;
    private UUID image;
    private Float price;
    private String isbn;
    private String title;
    private Double points;
    private UUID document;
    private String language;
    private AuthorDTO author;
    private String publisher;
    private Integer likeCount;
    private Date publishedDate;
    private String description;
    private Integer reviewCount;
    private List<String> genres;
    private String editionFormat;
    private DiscountDTO discount;
}
