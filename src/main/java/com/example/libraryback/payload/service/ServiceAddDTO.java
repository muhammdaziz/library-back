package com.example.libraryback.payload.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class ServiceAddDTO {
    private String title;

    private String subtitle;

    private MultipartFile image;
}
