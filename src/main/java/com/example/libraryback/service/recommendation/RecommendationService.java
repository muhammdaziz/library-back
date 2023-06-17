package com.example.libraryback.service.recommendation;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.recommendation.RecommendationAddDTO;
import com.example.libraryback.payload.recommendation.RecommendationDTO;

import java.util.List;

public interface RecommendationService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<RecommendationDTO>> get();

    ApiResult<RecommendationDTO> get(Integer id);

    ApiResult<RecommendationDTO> add(RecommendationAddDTO recommendationAddDTO);

    ApiResult<RecommendationDTO> edit(Integer id, RecommendationAddDTO recommendationAddDTO);

}
