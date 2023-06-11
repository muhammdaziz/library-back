package com.example.libraryback.payload;

import com.example.libraryback.entity.enums.PageEnum;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;

    private String email;

    private String firstname;

    private String lastname;

    private boolean enabled;

    private List<PageEnum> pages;

    private Set<PermissionEnum> permissions;

    private String role;

}

