package com.example.libraryback.payload.promotion;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromotionDTO {

    private UUID bgImg;
    private Integer id;
    private String btn1;
    private String btn2;
    private String title;
    private String header;
    private String subtitle;
    private String description;
}
