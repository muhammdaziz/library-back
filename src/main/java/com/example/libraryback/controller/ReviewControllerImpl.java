package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.review.ReviewAddDTO;
import com.example.libraryback.payload.review.ReviewDTO;
import com.example.libraryback.payload.review.ReviewDTOList;
import com.example.libraryback.payload.review.ReviewsDTO;
import com.example.libraryback.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;

    @Override
    public ApiResult<?> delete(UUID id){
        return reviewService.delete(id);
    }

    @Override
    public ApiResult<ReviewsDTO> get(Integer size, Integer page){
        return reviewService.get(size, page);
    }

    @Override
    public ApiResult<ReviewDTO> get(UUID id){
        return reviewService.get(id);
    }

    @Override
    public ApiResult<List<ReviewDTOList>> getBookReview(UUID bookId) {
        return reviewService.getBookReview(bookId);
    }

    @Override
    public ApiResult<ReviewDTO> add(ReviewAddDTO reviewAddDTO, User user){
        return reviewService.add(reviewAddDTO, user);
    }

    @Override
    public ApiResult<ReviewDTO> edit(UUID id, ReviewAddDTO reviewAddDTO, User user){
        return reviewService.edit(id, reviewAddDTO, user);
    }


}
