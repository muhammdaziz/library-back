package com.example.libraryback.service.review;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.review.ReviewAddDTO;
import com.example.libraryback.payload.review.ReviewDTO;
import com.example.libraryback.payload.review.ReviewDTOList;
import com.example.libraryback.payload.review.ReviewsDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

    ApiResult<?> delete(UUID id);

    ApiResult<ReviewsDTO> get(Integer size, Integer page);

    ApiResult<ReviewDTO> get(UUID id);

    ApiResult<List<ReviewDTOList>> getBookReview(UUID bookId);

    ApiResult<ReviewDTO> add(ReviewAddDTO reviewAddDTO, User user);

    ApiResult<ReviewDTO> edit(UUID id, ReviewAddDTO reviewAddDTO, User user);

}
