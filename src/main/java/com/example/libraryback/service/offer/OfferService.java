package com.example.libraryback.service.offer;

import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.offer.OfferAddDTO;
import com.example.libraryback.payload.offer.OfferDTO;

import java.util.List;
import java.util.UUID;

public interface OfferService {

    ApiResult<?> delete(UUID id);

    ApiResult<List<OfferDTO>> get();

    ApiResult<OfferDTO> get(UUID id);

    ApiResult<OfferDTO> setOrder(UUID id);

    ApiResult<OfferDTO> add(OfferAddDTO offerAddDTO);

    ApiResult<OfferDTO> edit(UUID id, OfferAddDTO offerAddDTO);

}
