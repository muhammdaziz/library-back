package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.ApiResult;
import com.example.libraryback.payload.UserDTO;
import com.example.libraryback.utils.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/user")
public interface UserController {
    String USER_ME_PATH = "/me";

    @GetMapping(value = USER_ME_PATH)
    ApiResult<UserDTO> getUserMe(@CurrentUser User user);

    @GetMapping()
    ApiResult<Boolean> logout(@CurrentUser User user);
}
