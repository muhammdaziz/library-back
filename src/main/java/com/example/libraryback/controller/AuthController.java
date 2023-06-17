package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.api.SignDTO;
import com.example.libraryback.payload.api.TokenDTO;
import com.example.libraryback.util.RestConstants;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = AuthController.AUTH_CONTROLLER_BASE_PATH)
public interface AuthController {

    String AUTH_CONTROLLER_BASE_PATH = RestConstants.BASE_PATH + "auth";
    String SIGN_IN_PATH = "/sign-in";
    String REFRESH_TOKEN_PATH = "/refresh-token";

    @ApiOperation(value = "Sign in path")
    @PostMapping(value = SIGN_IN_PATH)
    ApiResult<TokenDTO> signIn(@Valid @ModelAttribute SignDTO signDTO);

    @ApiOperation(value = "Refresh token path")
    @GetMapping(value = REFRESH_TOKEN_PATH)
    ApiResult<TokenDTO> refreshToken(@RequestHeader(value = RestConstants.AUTHENTICATION_HEADER) String accessToken,
                                     @RequestHeader(value = RestConstants.REFRESH_TOKEN) String refreshToken);

}
