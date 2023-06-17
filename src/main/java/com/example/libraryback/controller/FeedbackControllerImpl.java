package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.feedback.FeedbackAddDTO;
import com.example.libraryback.payload.feedback.FeedbackDTO;
import com.example.libraryback.payload.feedback.TestimonialDTO;
import com.example.libraryback.service.feedback.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FeedbackControllerImpl implements FeedbackController {

    private final FeedbackService feedbackService;

    @Override
    public ApiResult<?> delete(UUID id){
        return feedbackService.delete(id);
    }

    @Override
    public ApiResult<List<FeedbackDTO>> get(){
        return feedbackService.get();
    }

    @Override
    public ApiResult<FeedbackDTO> get(UUID id){
        return feedbackService.get(id);
    }

    @Override
    public ApiResult<TestimonialDTO> getTestimonial(Integer page, Integer size) {
        return feedbackService.getTestimonials(page, size);
    }

    @Override
    public ApiResult<FeedbackDTO> add(FeedbackAddDTO feedbackAddDTO, User user){
        return feedbackService.add(feedbackAddDTO, user);
    }

    @Override
    public ApiResult<FeedbackDTO> edit(UUID id, FeedbackAddDTO feedbackAddDTO, User user){
        return feedbackService.edit(id, feedbackAddDTO, user);
    }


}
