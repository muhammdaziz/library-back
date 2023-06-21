package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.news.NewsAddDTO;
import com.example.libraryback.payload.news.NewsDTO;
import com.example.libraryback.security.CurrentUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/news")
public interface NewsController {

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<NewsDTO> get(@PathVariable UUID id);

    @GetMapping("/{page}/{size}")
    ApiResult<List<NewsDTO>> get(@PathVariable Integer page, @PathVariable Integer size);

    @PostMapping()
    ApiResult<NewsDTO> add(@NotNull @ModelAttribute NewsAddDTO newsAddDTO, @NotNull @CurrentUser User user);

    @PutMapping("/{id}")
    ApiResult<NewsDTO> edit(@PathVariable UUID id, @RequestBody NewsAddDTO newsAddDTO, @CurrentUser User user);

}
