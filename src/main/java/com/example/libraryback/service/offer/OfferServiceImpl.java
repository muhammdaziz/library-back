package com.example.libraryback.service.offer;

import com.example.libraryback.entity.Offer;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.offer.OfferAddDTO;
import com.example.libraryback.payload.offer.OfferDTO;
import com.example.libraryback.repository.OfferRepository;
import com.example.libraryback.service.book.BookServiceImpl;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final IOService ioService;

    private final BookServiceImpl bookService;

    private final OfferRepository offerRepository;

    @Override
    public ApiResult<?> delete(UUID id) {

        checkExist(id);

        offerRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<OfferDTO>> get() {

        List<Offer> offers = offerRepository.findByOrderByOrderNumDesc(PageRequest.of(0, 15));

        return ApiResult
                .successResponse(
                        mapOfferDTO(offers));
    }

    @Override
    public ApiResult<OfferDTO> get(UUID id) {

        Offer offer = checkExist(id);

        return ApiResult
                .successResponse(
                        mapOfferDTO(offer));
    }

    @Override
    public ApiResult<OfferDTO> setOrder(UUID id) {
        Offer offer = checkExist(id);

        Date date = new Date();

        offer.setOrderNum(date.getTime());

        return ApiResult
                .successResponse(mapOfferDTO(offer));
    }

    @Override
    public ApiResult<OfferDTO> add(OfferAddDTO offerAddDTO) {

        checkExistByBook(offerAddDTO.getBookId());

        Offer offer = new Offer();

        mapOffer(offerAddDTO, offer);

        try {
            offer.setBackgroundImg(
                    ioService
                            .upload(
                                    offerAddDTO.getBackgroundImage(),
                                    true)
            );
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        offerRepository.save(offer);

        return ApiResult
                .successResponse(
                        mapOfferDTO(offer));
    }

    @Override
    public ApiResult<OfferDTO> edit(UUID id, OfferAddDTO offerAddDTO) {

        Offer offer = checkExist(id);

        checkExistByBookNotId(offerAddDTO.getBookId(), id);

        mapOffer(offerAddDTO, offer);

        offer.setId(id);

        if (!Objects.isNull(offerAddDTO.getBackgroundImage()))
            try {
                offer.setBackgroundImg(
                        ioService
                                .upload(
                                        offerAddDTO.getBackgroundImage(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        offerRepository.save(offer);

        return ApiResult
                .successResponse(
                        mapOfferDTO(offer));
    }



    private void checkExistByBookNotId(UUID bookId, UUID offerId) {
        if (offerRepository.existsByBookIdNotId(bookId, offerId))
            throw RestException
                    .restThrow("OFFER ALREADY EXIST", HttpStatus.CONFLICT);
    }

    private void checkExistByBook(UUID bookId) {
        if (offerRepository.existsByBookId(bookId))
            throw RestException
                    .restThrow("OFFER ALREADY EXIST", HttpStatus.CONFLICT);
    }

    private Offer checkExist(UUID id) {

        return offerRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO AUTHOR FOUND", HttpStatus.NOT_FOUND));
    }

    private OfferDTO mapOfferDTO(Offer offer) {

        return OfferDTO
                .builder()
                .id(offer.getId())
                .bkgImage(offer.getBackgroundImg().getId())
                .book(bookService.mapBookDTO(offer.getBook()))
                .build();
    }

    private void mapOffer(OfferAddDTO offerAddDTO, Offer offer) {

        Date date = new Date();

        offer.setOrderNum(date.getTime());
        offer.setBook(bookService.getBookById(offerAddDTO.getBookId()));
    }

    private List<OfferDTO> mapOfferDTO(List<Offer> offers) {

        return offers
                .stream()
                .map(this::mapOfferDTO)
                .collect(Collectors.toList());
    }
}
