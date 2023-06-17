package com.example.libraryback.payload.featured;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FeaturedAddDTO {

    private UUID bookId;

}
