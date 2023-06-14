package com.example.libraryback.payload.recommendation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RecommendationAddDTO {
    private String title;
    private String subtitle;
    private List<UUID> books;
    private MultipartFile backgroundImage;
}
