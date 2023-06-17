package com.example.libraryback.payload.author;

import com.example.libraryback.entity.Author;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {

    private Integer id;

    private UUID avatar;

    private String lastname;

    private String firstname;

    public static AuthorDTO map(Author author) {
        return AuthorDTO
                .builder()
                .id(author.getId())
                .lastname(author.getLastname())
                .firstname(author.getFirstname())
                .avatar(author.getAvatar().getId())
                .build();
    }
}
