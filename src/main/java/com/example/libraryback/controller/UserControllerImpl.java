package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.UserDTO;
import com.example.libraryback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ApiResult<Boolean> logout(User user) {
        return userService.logout(user);
    }

    @Override
    public ApiResult<UserDTO> getUserMe(User user) {
        if (Objects.isNull(user))
            throw RestException
                    .restThrow("User not found", HttpStatus.UNAUTHORIZED);

        return userService.getUserMe(user);
    }
}
