package com.example.libraryback.payload.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class NewsAddDTO {

    private String text;
    private String title;
    private MultipartFile image;
}
