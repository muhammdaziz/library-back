package com.example.libraryback.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SetRoleDTO {

    private UUID userId;

    private Long roleId;
}
