package com.example.libraryback.service.discount;

import com.example.libraryback.entity.Discount;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.discount.DiscountAddDTO;
import com.example.libraryback.payload.discount.DiscountDTO;
import com.example.libraryback.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    @Override
    public ApiResult<?> delete(Integer id) {

        checkExist(id);

        discountRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<DiscountDTO>> get() {

        List<Discount> discounts = discountRepository.findAll();

        return ApiResult
                .successResponse(
                        mapDiscountDTO(discounts));
    }

    @Override
    public ApiResult<DiscountDTO> get(Integer id) {

        Discount discount = checkExist(id);

        return ApiResult
                .successResponse(
                        mapDiscountDTO(discount));
    }

    @Override
    public ApiResult<DiscountDTO> add(DiscountAddDTO discountAddDTO) {

        checkExistByValue(discountAddDTO.getValue());

        Discount discount = mapDiscount(discountAddDTO);

        discountRepository.save(discount);

        return ApiResult
                .successResponse(
                        mapDiscountDTO(discount));
    }

    @Override
    public ApiResult<DiscountDTO> edit(Integer id, DiscountAddDTO discountAddDTO) {

        checkExist(id);

        checkExistByValueNotId(id, discountAddDTO.getValue());

        Discount discount = mapDiscount(discountAddDTO);

        discount.setId(id);

        discountRepository.save(discount);

        return ApiResult
                .successResponse(
                        mapDiscountDTO(discount));
    }



    private void checkExistByValueNotId(Integer id, Float value) {
        if(discountRepository.existsByValueNotId(value, id))
            throw RestException
                    .restThrow("DISCOUNT ALREADY EXIST", HttpStatus.CONFLICT);
    }

    private void checkExistByValue(Float value) {
        if(discountRepository.existsByValue(value))
            throw RestException
                    .restThrow("DISCOUNT ALREADY EXIST", HttpStatus.CONFLICT);
    }

    private Discount checkExist(Integer id) {

        return discountRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO DISCOUNT FOUND", HttpStatus.NOT_FOUND));
    }

    private DiscountDTO mapDiscountDTO(Discount discount) {

        return DiscountDTO
                .builder()
                .id(discount.getId())
                .value(discount.getValue())
                .description(discount.getDescription())
                .build();
    }

    private Discount mapDiscount(DiscountAddDTO discountAddDTO) {

        return Discount
                .builder()
                .value(discountAddDTO.getValue())
                .description(discountAddDTO.getDescription())
                .build();
    }

    private List<DiscountDTO> mapDiscountDTO(List<Discount> discounts) {

        return discounts
                .stream()
                .map(this::mapDiscountDTO)
                .collect(Collectors.toList());
    }
}
