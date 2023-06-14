package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.genre.GenreAddDTO;
import com.example.libraryback.payload.genre.GenreDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/genre")
public interface GenreController {

    @GetMapping()
    ApiResult<List<GenreDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<GenreDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<GenreDTO> add(@NotNull @ModelAttribute GenreAddDTO genreAddDTO);

    @PutMapping("/{id}")
    ApiResult<GenreDTO> edit(@PathVariable Integer id, @RequestBody GenreAddDTO genreAddDTO);

}
