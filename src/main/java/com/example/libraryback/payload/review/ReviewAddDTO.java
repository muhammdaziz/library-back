package com.example.libraryback.payload.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReviewAddDTO {

    private UUID bookId;

    private Integer point;

    private String message;
}
