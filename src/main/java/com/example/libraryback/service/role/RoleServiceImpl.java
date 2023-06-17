package com.example.libraryback.service.role;

import com.example.libraryback.entity.Role;
import com.example.libraryback.entity.User;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.repository.RoleRepository;
import com.example.libraryback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    @Override
    public ApiResult<Boolean> setRole(Long roleId, UUID userId) {

            User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            RestException
                                    .restThrow("no user found", HttpStatus.NOT_FOUND));

            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() ->
                            RestException
                                    .restThrow("no role found", HttpStatus.NOT_FOUND));

            user.setRole(role);

            userRepository.save(user);

            return ApiResult.successResponse();
    }
}
