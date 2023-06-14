package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.author.AuthorAddDTO;
import com.example.libraryback.payload.author.AuthorDTO;
import com.example.libraryback.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {

    private final AuthorService authorService;

    @Override
    public ApiResult<?> delete(Integer id){
        return authorService.delete(id);
    }

    @Override
    public ApiResult<List<AuthorDTO>> get(){
        return authorService.get();
    }

    @Override
    public ApiResult<AuthorDTO> get(Integer id){
        return authorService.get(id);
    }

    @Override
    public ApiResult<AuthorDTO> add(AuthorAddDTO authorAddDTO){
        return authorService.add(authorAddDTO);
    }

    @Override
    public ApiResult<AuthorDTO> edit(Integer id, AuthorAddDTO authorAddDTO){
        return authorService.edit(id, authorAddDTO);
    }


}
