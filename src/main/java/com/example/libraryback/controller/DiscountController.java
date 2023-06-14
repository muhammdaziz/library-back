package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.discount.DiscountAddDTO;
import com.example.libraryback.payload.discount.DiscountDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/discount")
public interface DiscountController {

    @GetMapping()
    ApiResult<List<DiscountDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable Integer id);

    @GetMapping("/{id}")
    ApiResult<DiscountDTO> get(@PathVariable Integer id);

    @PostMapping()
    ApiResult<DiscountDTO> add(@NotNull @ModelAttribute DiscountAddDTO discountAddDTO);

    @PutMapping("/{id}")
    ApiResult<DiscountDTO> edit(@PathVariable Integer id, @RequestBody DiscountAddDTO discountAddDTO);

}
