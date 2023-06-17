package com.example.libraryback.payload;

import com.example.libraryback.entity.Role;
import com.example.libraryback.entity.enums.PermissionEnum;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RoleDTO {

    private UUID id;

    private String name;

    private String description;

    private Set<PermissionEnum> permissions;

    public static RoleDTO map(Role role) {
        return RoleDTO
                .builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .permissions(role.getPermissions())
                .build();
    }
}
