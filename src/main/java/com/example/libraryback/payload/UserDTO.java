package com.example.libraryback.payload;

import com.example.libraryback.entity.enums.PageEnum;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private UUID id;

    private String role;

    private String email;

    private boolean enabled;

    private String lastname;

    private String firstname;

    private List<PageEnum> pages;

    private Set<PermissionEnum> permissions;

}

