package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.author.AuthorAddDTO;
import com.example.libraryback.payload.author.AuthorDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/author")
public interface AuthorController {

    @GetMapping()
    ApiResult<List<AuthorDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<AuthorDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<AuthorDTO> add(@NotNull @ModelAttribute AuthorAddDTO authorAddDTO);

    @PutMapping("/{id}")
    ApiResult<AuthorDTO> edit(@PathVariable Integer id, @RequestBody AuthorAddDTO authorAddDTO);

}
