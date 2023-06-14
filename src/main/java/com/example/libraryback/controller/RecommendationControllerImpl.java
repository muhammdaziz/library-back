package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.recommendation.RecommendationAddDTO;
import com.example.libraryback.payload.recommendation.RecommendationDTO;
import com.example.libraryback.service.recommendation.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecommendationControllerImpl implements RecommendationController {

    private final RecommendationService recommendationService;

    @Override
    public ApiResult<?> delete(Integer id){
        return recommendationService.delete(id);
    }

    @Override
    public ApiResult<List<RecommendationDTO>> get(){
        return recommendationService.get();
    }

    @Override
    public ApiResult<RecommendationDTO> get(Integer id){
        return recommendationService.get(id);
    }

    @Override
    public ApiResult<RecommendationDTO> add(RecommendationAddDTO recommendationAddDTO){
        return recommendationService.add(recommendationAddDTO);
    }

    @Override
    public ApiResult<RecommendationDTO> edit(Integer id, RecommendationAddDTO recommendationAddDTO){
        return recommendationService.edit(id, recommendationAddDTO);
    }


}
