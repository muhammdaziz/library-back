package com.example.libraryback.payload.review;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewsDTO {

    private Long size;

    private List<ReviewDTO> reviews;

}
