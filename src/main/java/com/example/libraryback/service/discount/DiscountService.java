package com.example.libraryback.service.discount;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.discount.DiscountAddDTO;
import com.example.libraryback.payload.discount.DiscountDTO;

import java.util.List;

public interface DiscountService {

    ApiResult<?> delete(Integer id);

    ApiResult<List<DiscountDTO>> get();

    ApiResult<DiscountDTO> get(Integer id);

    ApiResult<DiscountDTO> add(DiscountAddDTO discountAddDTO);

    ApiResult<DiscountDTO> edit(Integer id, DiscountAddDTO discountAddDTO);

}
