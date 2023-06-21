package com.example.libraryback.service.review;

import com.example.libraryback.entity.Book;
import com.example.libraryback.entity.Review;
import com.example.libraryback.entity.User;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.UserDTO2;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.review.ReviewAddDTO;
import com.example.libraryback.payload.review.ReviewDTO;
import com.example.libraryback.payload.review.ReviewDTOList;
import com.example.libraryback.payload.review.ReviewsDTO;
import com.example.libraryback.repository.BookRepository;
import com.example.libraryback.repository.ReviewRepository;
import com.example.libraryback.service.book.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final BookServiceImpl bookService;

    private final BookRepository bookRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public ApiResult<?> delete(UUID id) {

        checkExist(id);

        reviewRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<ReviewsDTO> get(Integer size, Integer page) {

        Page<Review> reviews = reviewRepository.findAll(PageRequest.of(page, size));

        return ApiResult
                .successResponse(
                        mapReviewsDTO(reviews.getContent(), reviews.getTotalElements()));
    }

    @Override
    public ApiResult<ReviewDTO> get(UUID id) {

        Review review = checkExist(id);

        return ApiResult
                .successResponse(
                        mapReviewDTO(review));
    }

    @Override
    public ApiResult<List<ReviewDTOList>> getBookReview(UUID bookId) {
        List<Review> reviews = reviewRepository.findAllByBookId(bookId);

        return ApiResult
                .successResponse(mapReviewDTOList(reviews));
    }

    @Override
    public ApiResult<ReviewDTO> add(ReviewAddDTO reviewAddDTO, User user) {

        checkExist(user.getId(), reviewAddDTO.getBookId());

        Review review = mapReview(reviewAddDTO, user);

        reviewRepository.save(review);

        return ApiResult
                .successResponse(
                        mapReviewDTO(review));
    }

    private void checkExist(UUID userId, UUID bookId) {
        if (reviewRepository.existsByBookIdAndUserId(bookId, userId))
            throw RestException
                    .restThrow("ALREADY REVIEWED", HttpStatus.CONFLICT);
    }

    @Override
    public ApiResult<ReviewDTO> edit(UUID id, ReviewAddDTO reviewAddDTO, User user) {

        checkExist(id);

        Review review = mapReview(reviewAddDTO, user);

        review.setId(id);

        reviewRepository.save(review);

        return ApiResult
                .successResponse(
                        mapReviewDTO(review));
    }



    private Book getBook(UUID bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                        RestException
                                .restThrow("BOOK NOT FOUND", HttpStatus.NOT_FOUND));
    }

    private Review checkExist(UUID id) {

        return reviewRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO REVIEW FOUND", HttpStatus.NOT_FOUND));
    }

    private ReviewDTO mapReviewDTO(Review review) {

        return ReviewDTO
                .builder()
                .id(review.getId())
                .date(review.getDate())
                .point((double)review.getPoint())
                .message(review.getMessage())
                .user(UserDTO2.mapUserDTO(review.getUser()))
                .book(bookService.mapBookDTO(review.getBook()))
                .build();
    }

    private List<ReviewDTO> mapReviewDTO(List<Review> reviews) {

        return reviews.stream()
                .map(this::mapReviewDTO)
                .collect(Collectors.toList());
    }

    private Review mapReview(ReviewAddDTO reviewAddDTO, User user) {

        return Review
                .builder()
                .user(user)
                .date(new Date())
                .point(reviewAddDTO.getPoint())
                .message(reviewAddDTO.getMessage())
                .book(getBook(reviewAddDTO.getBookId()))
                .build();
    }

    private ReviewsDTO mapReviewsDTO(List<Review> reviews, Long size) {
        return ReviewsDTO
                .builder()
                .size(size)
                .reviews(mapReviewDTO(reviews))
                .build();
    }

    private List<ReviewDTOList> mapReviewDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(review ->
                        ReviewDTOList
                                .builder()
                                .id(review.getId())
                                .point(review.getPoint())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
