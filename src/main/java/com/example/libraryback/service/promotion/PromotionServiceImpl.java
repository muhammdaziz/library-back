package com.example.libraryback.service.promotion;

import com.example.libraryback.entity.Promotion;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.promotion.PromotionAddDTO;
import com.example.libraryback.payload.promotion.PromotionDTO;
import com.example.libraryback.repository.PromotionRepository;
import com.example.libraryback.service.io.IOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final IOService ioService;

    private final PromotionRepository promotionRepository;

    @Override
    public ApiResult<?> delete(Integer id) {

        checkExist(id);

        promotionRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<PromotionDTO>> get() {

        List<Promotion> promotions = promotionRepository.findAll();

        return ApiResult
                .successResponse(
                        mapPromoDTO(promotions));
    }

    @Override
    public ApiResult<PromotionDTO> get(Integer id) {

        Promotion promotion = checkExist(id);

        return ApiResult
                .successResponse(
                        mapPromoDTO(promotion));
    }

    @Override
    public ApiResult<PromotionDTO> add(PromotionAddDTO promotionAddDTO) {

        Promotion promotion = mapPromo(promotionAddDTO);

        try {
            promotion.setBackgroundImage(
                    ioService
                            .upload(
                                    promotionAddDTO.getBackgroundImage(),
                                    true)
            );
        } catch (IOException e) {
            throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
        }

        promotionRepository.save(promotion);

        return ApiResult
                .successResponse(
                        mapPromoDTO(promotion));
    }

    @Override
    public ApiResult<PromotionDTO> edit(Integer id, PromotionAddDTO promotionAddDTO) {

        checkExist(id);

        Promotion promotion = mapPromo(promotionAddDTO);

        promotion.setId(id);

        if (!Objects.isNull(promotionAddDTO.getBackgroundImage()))
            try {
                promotion.setBackgroundImage(
                        ioService
                                .upload(
                                        promotionAddDTO.getBackgroundImage(),
                                        true)
                );
            } catch (IOException e) {
                throw new RestException("COULD NOT SAVE IMAGE", HttpStatus.CONFLICT);
            }

        promotionRepository.save(promotion);

        return ApiResult
                .successResponse(
                        mapPromoDTO(promotion));
    }



    private Promotion checkExist(Integer id) {

        return promotionRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO PROMOTION FOUND", HttpStatus.NOT_FOUND));
    }

    private PromotionDTO mapPromoDTO(Promotion promotion) {

        return PromotionDTO
                .builder()
                .id(promotion.getId())
                .btn1(promotion.getBtn1())
                .btn2(promotion.getBtn2())
                .title(promotion.getTitle())
                .header(promotion.getHeader())
                .subtitle(promotion.getSubtitle())
                .description(promotion.getDescription())
                .bgImg(promotion.getBackgroundImage().getId())
                .build();
    }

    private Promotion mapPromo(PromotionAddDTO promotionAddDTO) {

        return Promotion
                .builder()
                .btn1(promotionAddDTO.getBtn1())
                .btn2(promotionAddDTO.getBtn2())
                .title(promotionAddDTO.getTitle())
                .header(promotionAddDTO.getHeader())
                .subtitle(promotionAddDTO.getSubtitle())
                .description(promotionAddDTO.getDescription())
                .build();
    }

    private List<PromotionDTO> mapPromoDTO(List<Promotion> promotions) {

        return promotions
                .stream()
                .map(this::mapPromoDTO)
                .collect(Collectors.toList());
    }
}
