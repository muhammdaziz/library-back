package com.example.libraryback.service.user;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.UserDTO;

public interface UserService {

    ApiResult<Boolean> logout(User user);

    ApiResult<UserDTO> getUserMe(User user);
}
