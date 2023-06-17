package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.featured.FeaturedAddDTO;
import com.example.libraryback.payload.featured.FeaturedDTO;
import com.example.libraryback.service.featured.FeaturedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeaturedControllerImpl implements FeaturedController {

    private final FeaturedService featuredService;

    @Override
    public ApiResult<?> delete(Integer id){
        return featuredService.delete(id);
    }

    @Override
    public ApiResult<List<FeaturedDTO>> get(){
        return featuredService.get();
    }

    @Override
    public ApiResult<FeaturedDTO> get(Integer id){
        return featuredService.get(id);
    }

    @Override
    public ApiResult<FeaturedDTO> add(FeaturedAddDTO featuredAddDTO){
        return featuredService.add(featuredAddDTO);
    }

}
