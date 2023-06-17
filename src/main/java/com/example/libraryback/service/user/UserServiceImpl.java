package com.example.libraryback.service.user;

import com.example.libraryback.entity.User;
import com.example.libraryback.entity.enums.PageEnum;
import com.example.libraryback.entity.enums.PermissionEnum;
import com.example.libraryback.payload.RoleDTO;
import com.example.libraryback.payload.UserDTO2;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public ApiResult<Boolean> logout(User user) {
        return null;
    }

    @Override
    public ApiResult<UserDTO> getUserMe(User user) {
        return ApiResult.successResponse(UserDTO.mapUserDTO(user));
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
