package com.example.libraryback.payload.discount;

import com.example.libraryback.entity.Discount;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountDTO {
    private Integer id;
    private Float value;
    private String description;

    public static DiscountDTO map(Discount discount) {

        if (Objects.isNull(discount))
            return null;

        return DiscountDTO
                .builder()
                .id(discount.getId())
                .value(discount.getValue())
                .description(discount.getDescription())
                .build();
    }
}