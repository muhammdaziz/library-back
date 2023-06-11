package com.example.libraryback.security;

import com.example.libraryback.entity.User;
import com.example.libraryback.repository.UserRepository;
import com.example.libraryback.utils.RestConstants;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    @Value("${jwt.access.key}")
    private String TOKEN_KEY;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        setSecurityContext(request);
        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(HttpServletRequest request) {
        String authorization = request.getHeader(RestConstants.AUTHENTICATION_HEADER);
        if (Objects.nonNull(authorization) && authorization.startsWith("Bearer")) {

            authorization = authorization.substring(6).trim();

            String email = getEmailFromToken(authorization);
            if (!email.isEmpty()) {
                Optional<User> optionalUser = userRepository.findByEmail(email);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    if (user.isEnabled()
                            && user.isAccountNonExpired()
                            && user.isAccountNonLocked()
                            && user.isCredentialsNonExpired())
                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(
                                        new UsernamePasswordAuthenticationToken(
                                                user,
                                                null,
                                                user.getAuthorities()
                                        ));
                }
            }
        }
    }

    private String getEmailFromToken(String authorization) {
        String email = "";
        try {
            email = Jwts
                    .parser()
                    .setSigningKey(TOKEN_KEY)
                    .parseClaimsJws(authorization)
                    .getBody()
                    .getSubject();
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return email;
    }
}