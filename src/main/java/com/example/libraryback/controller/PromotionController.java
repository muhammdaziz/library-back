package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.promotion.PromotionAddDTO;
import com.example.libraryback.payload.promotion.PromotionDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/promotion")
public interface PromotionController {

    @GetMapping()
    ApiResult<List<PromotionDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<PromotionDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<PromotionDTO> add(@NotNull @ModelAttribute PromotionAddDTO promotionAddDTO);

    @PutMapping("/{id}")
    ApiResult<PromotionDTO> edit(@PathVariable Integer id, @RequestBody PromotionAddDTO promotionAddDTO);

}
