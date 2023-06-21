package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.news.NewsAddDTO;
import com.example.libraryback.payload.news.NewsDTO;
import com.example.libraryback.service.news.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NewsControllerImpl implements NewsController {

    private final NewsService newsService;

    @Override
    public ApiResult<?> delete(UUID id){
        return newsService.delete(id);
    }

    @Override
    public ApiResult<NewsDTO> get(UUID id){
        return newsService.get(id);
    }

    @Override
    public ApiResult<List<NewsDTO>> get(Integer page, Integer size){
        return newsService.get(page, size);
    }

    @Override
    public ApiResult<NewsDTO> add(NewsAddDTO newsAddDTO, User user){
        return newsService.add(newsAddDTO, user);
    }

    @Override
    public ApiResult<NewsDTO> edit(UUID id, NewsAddDTO newsAddDTO, User user){
        return newsService.edit(id, newsAddDTO, user);
    }


}
