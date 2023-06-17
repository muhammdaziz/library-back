package com.example.libraryback.service.feedback;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.feedback.FeedbackAddDTO;
import com.example.libraryback.payload.feedback.FeedbackDTO;
import com.example.libraryback.payload.feedback.TestimonialDTO;

import java.util.List;
import java.util.UUID;

public interface FeedbackService {

    ApiResult<?> delete(UUID id);

    ApiResult<List<FeedbackDTO>> get();

    ApiResult<FeedbackDTO> get(UUID id);

    ApiResult<TestimonialDTO> getTestimonials(Integer page, Integer size);

    ApiResult<FeedbackDTO> add(FeedbackAddDTO feedbackAddDTO, User user);

    ApiResult<FeedbackDTO> edit(UUID id, FeedbackAddDTO feedbackAddDTO, User user);

}
