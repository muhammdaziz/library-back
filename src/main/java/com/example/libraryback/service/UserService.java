package com.example.libraryback.service;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.ApiResult;
import com.example.libraryback.payload.UserDTO;

public interface UserService {
    ApiResult<UserDTO> getUserMe(User user);

    ApiResult<Boolean> logout(User user);
}
