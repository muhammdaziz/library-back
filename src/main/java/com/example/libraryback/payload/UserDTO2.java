package com.example.libraryback.payload;

import com.example.libraryback.entity.User;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO2 {

    private UUID id;

    private String bio;

    private UUID avatar;

    private String email;

    private String lastname;

    private String firstname;


    public static UserDTO2 mapUserDTO(User user) {
        return UserDTO2.builder()
                .id(user.getId())
                .bio(user.getBio())
                .email(user.getEmail())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .avatar(user.getAvatar().getId())
                .build();
    }

    public static Set<UserDTO2> mapUserDTO(Set<User> users) {
        return users.stream().map(UserDTO2::mapUserDTO).collect(Collectors.toSet());
    }
}

