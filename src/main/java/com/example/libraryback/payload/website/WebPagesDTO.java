package com.example.libraryback.payload.website;

import com.example.libraryback.entity.WebPages;
import com.example.libraryback.entity.Website;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebPagesDTO {

    private Integer id;

    private String name;

    private String link;

    private UUID image;

    public static WebPagesDTO map(WebPages webPages) {
        return WebPagesDTO
                .builder()
                .id(webPages.getId())
                .name(webPages.getName())
                .link(webPages.getLink())
                .image(webPages.getImage().getId())
                .build();
    }

    public static List<WebPagesDTO> map(List<WebPages> webPages) {
        return webPages.stream()
                .map(WebPagesDTO::map)
                .collect(Collectors.toList());
    }
}
