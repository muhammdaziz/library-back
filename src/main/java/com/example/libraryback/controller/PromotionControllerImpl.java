package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.promotion.PromotionAddDTO;
import com.example.libraryback.payload.promotion.PromotionDTO;
import com.example.libraryback.service.promotion.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PromotionControllerImpl implements PromotionController {

    private final PromotionService promotionService;

    @Override
    public ApiResult<?> delete(Integer id){
        return promotionService.delete(id);
    }

    @Override
    public ApiResult<List<PromotionDTO>> get(){
        return promotionService.get();
    }

    @Override
    public ApiResult<PromotionDTO> get(Integer id){
        return promotionService.get(id);
    }

    @Override
    public ApiResult<PromotionDTO> add(PromotionAddDTO promotionAddDTO){
        return promotionService.add(promotionAddDTO);
    }

    @Override
    public ApiResult<PromotionDTO> edit(Integer id, PromotionAddDTO promotionAddDTO){
        return promotionService.edit(id, promotionAddDTO);
    }


}
