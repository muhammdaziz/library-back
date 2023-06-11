package com.example.libraryback.service;


import com.example.libraryback.payload.ApiResult;

import java.util.UUID;

public interface RoleService {

    ApiResult<Boolean> setRole(Long roleId, UUID userId);
}
