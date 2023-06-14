package com.example.libraryback.payload.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ServiceDTO {

    private UUID svg;

    private Integer id;

    private String title;

    private String subtitle;

}
