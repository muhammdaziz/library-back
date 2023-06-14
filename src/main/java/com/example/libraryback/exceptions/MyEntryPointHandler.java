package com.example.libraryback.exceptions;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.api.ErrorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class MyEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) {

        ApiResult<List<ErrorData>> apiResult = ApiResult
                .failResponse(
                        "Full authentication is required to access this resource",
                        HttpStatus.UNAUTHORIZED.value());

        log.error("Full authentication is required to access this resource: {}", request.getRequestURI());

        try {

            String string = objectMapper.writeValueAsString(apiResult);
            response.getWriter().write(string);
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
