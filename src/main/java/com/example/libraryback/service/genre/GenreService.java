package com.example.libraryback.service.genre;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.genre.GenreAddDTO;
import com.example.libraryback.payload.genre.GenreDTO;

import java.util.List;

public interface GenreService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<GenreDTO>> get();

    ApiResult<GenreDTO> get(Integer id);

    ApiResult<GenreDTO> add(GenreAddDTO genreAddDTO);

    ApiResult<GenreDTO> edit(Integer id, GenreAddDTO genreAddDTO);

}
