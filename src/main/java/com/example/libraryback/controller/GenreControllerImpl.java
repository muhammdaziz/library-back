package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.genre.GenreAddDTO;
import com.example.libraryback.payload.genre.GenreDTO;
import com.example.libraryback.service.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreControllerImpl implements GenreController {

    private final GenreService genreService;

    @Override
    public ApiResult<?> delete(Integer id){
        return genreService.delete(id);
    }

    @Override
    public ApiResult<List<GenreDTO>> get(){
        return genreService.get();
    }

    @Override
    public ApiResult<GenreDTO> get(Integer id){
        return genreService.get(id);
    }

    @Override
    public ApiResult<GenreDTO> add(GenreAddDTO genreAddDTO){
        return genreService.add(genreAddDTO);
    }

    @Override
    public ApiResult<GenreDTO> edit(Integer id, GenreAddDTO genreAddDTO){
        return genreService.edit(id, genreAddDTO);
    }


}
