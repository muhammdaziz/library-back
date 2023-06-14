package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.recommendation.RecommendationAddDTO;
import com.example.libraryback.payload.recommendation.RecommendationDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/recommendation")
public interface RecommendationController {

    @GetMapping()
    ApiResult<List<RecommendationDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<RecommendationDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<RecommendationDTO> add(@NotNull @ModelAttribute RecommendationAddDTO recommendationAddDTO);

    @PutMapping("/{id}")
    ApiResult<RecommendationDTO> edit(@PathVariable Integer id, @RequestBody RecommendationAddDTO recommendationAddDTO);


}
