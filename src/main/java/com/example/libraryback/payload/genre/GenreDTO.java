package com.example.libraryback.payload.genre;

import com.example.libraryback.entity.Genre;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
public class GenreDTO {

    private Integer id;

    private String name;

    public static GenreDTO map(Genre genre) {

        return GenreDTO
                .builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    public static List<String> map(List<Genre> genres) {

        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
    }
}
