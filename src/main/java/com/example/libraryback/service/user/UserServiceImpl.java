package com.example.libraryback.service.user;

import com.example.libraryback.entity.User;
import com.example.libraryback.entity.enums.PageEnum;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.UserDTO;
import com.example.libraryback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ApiResult<Boolean> logout(User user) {
        return null;
    }

    @Override
    public ApiResult<UserDTO> getUserMe(User user) {
        return ApiResult.successResponse(mapUserDTO(user));
    }

    public UserDTO mapUserDTO(User user) {

        return UserDTO
                .builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .pages(getPages(user).stream().toList())
                .enabled(user.isEnabled())
                .id(user.getId())
                .role(user.getRole().getName())
                .build();
    }



    private static Set<PageEnum> getPages(User user) {

        return user
                .getRole()
                .getPermissions()
                .stream()
                .map(PermissionEnum::getPage)
                .collect(Collectors.toSet());
    }
}
