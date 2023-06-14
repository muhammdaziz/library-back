package com.example.libraryback.payload.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class PromotionAddDTO {
    private String btn1;
    private String btn2;
    private String title;
    private String header;
    private String subtitle;
    private String description;
    private MultipartFile backgroundImage;
}
