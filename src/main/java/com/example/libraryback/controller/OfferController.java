package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.offer.OfferAddDTO;
import com.example.libraryback.payload.offer.OfferDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/offer")
public interface OfferController {

    @GetMapping()
    ApiResult<List<OfferDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<OfferDTO> get(@PathVariable UUID id);

    @PostMapping("/set-priority/{id}")
    ApiResult<OfferDTO> setOrder(@PathVariable UUID id);

    @PostMapping()
    ApiResult<OfferDTO> add(@NotNull @ModelAttribute OfferAddDTO offerAddDTO);

    @PutMapping("/{id}")
    ApiResult<OfferDTO> edit(@PathVariable UUID id, @RequestBody OfferAddDTO offerAddDTO);

}
