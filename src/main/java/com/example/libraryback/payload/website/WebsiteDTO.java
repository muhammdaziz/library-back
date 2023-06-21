package com.example.libraryback.payload.website;

import com.example.libraryback.entity.WebPages;
import com.example.libraryback.entity.Website;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WebsiteDTO {

    private UUID id;

    private String name;

    private String subtitle;

    private String description;

    private UUID logo;

    private String address;

    private String email;

    private String phone;

    private List<Double> location;

    private List<WebPagesDTO> webPages;

    public static WebsiteDTO map(Website website) {
        return WebsiteDTO
                .builder()
                .id(website.getId())
                .name(website.getName())
                .phone(website.getPhone())
                .email(website.getEmail())
                .address(website.getAddress())
                .location(website.getLocation())
                .logo(website.getLogo().getId())
                .subtitle(website.getSubtitle())
                .description(website.getDescription())
                .webPages(WebPagesDTO.map(website.getWebpages()))
                .build();
    }
}
