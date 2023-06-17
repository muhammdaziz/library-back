package com.example.libraryback.service.featured;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.featured.FeaturedAddDTO;
import com.example.libraryback.payload.featured.FeaturedDTO;

import java.util.List;

public interface FeaturedService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<FeaturedDTO>> get();

    ApiResult<FeaturedDTO> get(Integer id);

    ApiResult<FeaturedDTO> add(FeaturedAddDTO featuredAddDTO);

}
