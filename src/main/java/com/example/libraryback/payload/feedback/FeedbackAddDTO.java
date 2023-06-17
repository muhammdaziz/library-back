package com.example.libraryback.payload.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedbackAddDTO {

    private Integer point;

    private String message;
}
