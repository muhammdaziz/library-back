package com.example.libraryback.payload.recommendation;

import com.example.libraryback.payload.book.BookDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecommendationDTO {
    private Integer id;
    private String title;
    private UUID bkgImage;
    private String subtitle;
    private List<BookDTO> books;
}
