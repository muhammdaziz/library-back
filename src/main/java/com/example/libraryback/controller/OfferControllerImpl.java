package com.example.libraryback.controller;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.offer.OfferAddDTO;
import com.example.libraryback.payload.offer.OfferDTO;
import com.example.libraryback.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OfferControllerImpl implements OfferController {

    private final OfferService offerService;

    @Override
    public ApiResult<?> delete(UUID id){
        return offerService.delete(id);
    }

    @Override
    public ApiResult<List<OfferDTO>> get(){
        return offerService.get();
    }

    @Override
    public ApiResult<OfferDTO> get(UUID id){
        return offerService.get(id);
    }

    @Override
    public ApiResult<OfferDTO> setOrder(UUID id) {
        return offerService.setOrder(id);
    }

    @Override
    public ApiResult<OfferDTO> add(OfferAddDTO offerAddDTO){
        return offerService.add(offerAddDTO);
    }

    @Override
    public ApiResult<OfferDTO> edit(UUID id, OfferAddDTO offerAddDTO){
        return offerService.edit(id, offerAddDTO);
    }


}
