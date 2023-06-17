package com.example.libraryback.payload.review;

import com.example.libraryback.payload.UserDTO2;
import com.example.libraryback.payload.book.BookDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {

    private UUID id;

    private Date date;

    private UserDTO2 user;

    private BookDTO book;

    private Integer point;

    private String message;

}
