package com.example.libraryback.service.promotion;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.promotion.PromotionAddDTO;
import com.example.libraryback.payload.promotion.PromotionDTO;

import java.util.List;

public interface PromotionService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<PromotionDTO>> get();

    ApiResult<PromotionDTO> get(Integer id);

    ApiResult<PromotionDTO> add(PromotionAddDTO promotionAddDTO);

    ApiResult<PromotionDTO> edit(Integer id, PromotionAddDTO promotionAddDTO);

}
