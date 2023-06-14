package com.example.libraryback.payload.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiscountAddDTO {
    private Float value;
    private String description;
}