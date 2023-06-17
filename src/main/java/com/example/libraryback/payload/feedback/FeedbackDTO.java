package com.example.libraryback.payload.feedback;

import com.example.libraryback.payload.UserDTO2;
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
public class FeedbackDTO {

    private UUID id;

    private Date date;

    private UserDTO2 user;

    private Integer point;

    private String message;
}
