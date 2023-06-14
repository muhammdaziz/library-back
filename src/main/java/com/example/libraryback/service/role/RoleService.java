package com.example.libraryback.service.role;

import com.example.libraryback.payload.api.ApiResult;

import java.util.UUID;

public interface RoleService {

    ApiResult<Boolean> setRole(Long roleId, UUID userId);
}
