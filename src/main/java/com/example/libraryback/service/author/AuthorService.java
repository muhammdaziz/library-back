package com.example.libraryback.service.author;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.author.AuthorAddDTO;
import com.example.libraryback.payload.author.AuthorDTO;

import java.util.List;

public interface AuthorService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<AuthorDTO>> get();

    ApiResult<AuthorDTO> get(Integer id);

    ApiResult<AuthorDTO> add(AuthorAddDTO authorAddDTO);

    ApiResult<AuthorDTO> edit(Integer id, AuthorAddDTO authorAddDTO);

}
