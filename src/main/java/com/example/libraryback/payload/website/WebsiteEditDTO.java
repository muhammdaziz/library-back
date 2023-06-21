package com.example.libraryback.payload.website;

import com.example.libraryback.entity.WebPages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class WebsiteEditDTO {

    private UUID id;

    private String name;

    private String subtitle;

    private MultipartFile logo;

    private String description;

    private String address;

    private String email;

    private String phone;

    private List<Double> location;

    private List<WebPages> webPages;

}
