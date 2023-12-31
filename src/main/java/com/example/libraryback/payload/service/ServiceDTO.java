package com.example.libraryback.payload.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceDTO {

    private UUID svg;

    private Integer id;

    private String title;

    private String subtitle;

}
