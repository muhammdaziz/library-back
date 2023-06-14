package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.discount.DiscountAddDTO;
import com.example.libraryback.payload.discount.DiscountDTO;
import com.example.libraryback.service.discount.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiscountControllerImpl implements DiscountController {

    private final DiscountService discountService;

    @Override
    public ApiResult<?> delete(Integer id){
        return discountService.delete(id);
    }

    @Override
    public ApiResult<List<DiscountDTO>> get(){
        return discountService.get();
    }

    @Override
    public ApiResult<DiscountDTO> get(Integer id){
        return discountService.get(id);
    }

    @Override
    public ApiResult<DiscountDTO> add(DiscountAddDTO discountAddDTO){
        return discountService.add(discountAddDTO);
    }

    @Override
    public ApiResult<DiscountDTO> edit(Integer id, DiscountAddDTO discountAddDTO){
        return discountService.edit(id, discountAddDTO);
    }


}
