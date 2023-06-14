package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController{

    private final RoleService roleService;

    @Override
    public ApiResult<Boolean> setRole(Long roleId, UUID userId) {
        return roleService.setRole(roleId, userId);
    }
}
