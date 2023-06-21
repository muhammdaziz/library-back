package com.example.libraryback.service.news;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.news.NewsAddDTO;
import com.example.libraryback.payload.news.NewsDTO;

import java.util.List;
import java.util.UUID;

public interface NewsService {

    ApiResult<?> delete(UUID id);

    ApiResult<NewsDTO> get(UUID id);

    ApiResult<List<NewsDTO>> get(Integer page, Integer size);

    ApiResult<NewsDTO> add(NewsAddDTO newsAddDTO, User user);

    ApiResult<NewsDTO> edit(UUID id, NewsAddDTO newsAddDTO, User user);

}
