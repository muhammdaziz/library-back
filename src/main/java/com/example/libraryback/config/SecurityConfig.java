package com.example.libraryback.config;

import com.example.libraryback.exceptions.MyEntryPointHandler;
import com.example.libraryback.security.JWTFilter;
import com.example.libraryback.utils.RestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JWTFilter jwtFilter;

    private final MyEntryPointHandler myEntryPointHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(auth ->
                        auth
                                .antMatchers("/",
                                        "/favicon.ico",
                                        "//*.png",
                                        "//*.gif",
                                        "//*.svg",
                                        "//*.jpg",
                                        "//*.html",
                                        "//*.css",
                                        "//*.js",
                                        "/swagger-ui.html",
                                        "/swagger-resources/",
                                        "/v2/",
                                        "/csrf",
                                        "/webjars/",
                                        "/v2/api-docs",
                                        "/configuration/ui")
                                .permitAll()
                                .antMatchers(RestConstants.OPEN_PAGES)
                                .permitAll()
                                .antMatchers("/api/**")
                                .authenticated()
                                .antMatchers(HttpMethod.OPTIONS)
                                .permitAll()
                )
                .exceptionHandling()
                .authenticationEntryPoint(myEntryPointHandler)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
