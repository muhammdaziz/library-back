package com.example.libraryback.payload.offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OfferAddDTO {

    private UUID bookId;

    private MultipartFile backgroundImage;
}
