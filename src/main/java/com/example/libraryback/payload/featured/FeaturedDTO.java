package com.example.libraryback.payload.featured;

import com.example.libraryback.payload.book.BookDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeaturedDTO {

    private Integer id;

    private BookDTO book;

}
