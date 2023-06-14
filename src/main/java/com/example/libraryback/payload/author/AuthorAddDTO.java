package com.example.libraryback.payload.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class AuthorAddDTO {

    private String lastname;

    private String firstname;

    private MultipartFile avatar;
}
