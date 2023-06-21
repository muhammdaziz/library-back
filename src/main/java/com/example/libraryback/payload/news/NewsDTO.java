package com.example.libraryback.payload.news;

import com.example.libraryback.entity.News;
import com.example.libraryback.payload.UserDTO2;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDTO {

    private UUID id;
    private Date date;
    private UUID image;
    private String text;
    private String title;
    private UserDTO2 author;

    public static NewsDTO map(News news) {
        return NewsDTO
                .builder()
                .id(news.getId())
                .date(news.getDate())
                .text(news.getText())
                .title(news.getTitle())
                .image(news.getImage().getId())
                .author(UserDTO2.mapUserDTO(news.getAuthor()))
                .build();
    }
}
