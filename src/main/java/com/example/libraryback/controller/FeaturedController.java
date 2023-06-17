package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.featured.FeaturedAddDTO;
import com.example.libraryback.payload.featured.FeaturedDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/featured")
public interface FeaturedController {

    @GetMapping()
    ApiResult<List<FeaturedDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<FeaturedDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<FeaturedDTO> add(@NotNull @ModelAttribute FeaturedAddDTO featuredAddDTO);

}
