package com.example.libraryback.service.auth;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.api.SignDTO;
import com.example.libraryback.payload.api.TokenDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.UUID;

public interface AuthService extends UserDetailsService {
    Optional<User> getUserById(UUID id);

    ApiResult<TokenDTO> signIn(SignDTO signDTO);

    ApiResult<TokenDTO> refreshToken(String accessToken, String refreshToken);
}
