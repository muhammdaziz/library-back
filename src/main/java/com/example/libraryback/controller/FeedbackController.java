package com.example.libraryback.controller;

import com.example.libraryback.entity.User;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.feedback.FeedbackAddDTO;
import com.example.libraryback.payload.feedback.FeedbackDTO;
import com.example.libraryback.payload.feedback.TestimonialDTO;
import com.example.libraryback.security.CurrentUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/feedback")
public interface FeedbackController {

    @GetMapping()
    ApiResult<List<FeedbackDTO>> get();

    @DeleteMapping("/{id}")
    ApiResult<?> delete(@PathVariable UUID id);

    @GetMapping("/{id}")
    ApiResult<FeedbackDTO> get(@PathVariable UUID id);

    @GetMapping("/testimonial/{page}/{size}")
    ApiResult<TestimonialDTO> getTestimonial(@PathVariable Integer page, @PathVariable Integer size);

    @PostMapping()
    ApiResult<FeedbackDTO> add(@NotNull @ModelAttribute FeedbackAddDTO feedbackAddDTO, @CurrentUser User user);

    @PutMapping("/{id}")
    ApiResult<FeedbackDTO> edit(@PathVariable UUID id, @RequestBody FeedbackAddDTO feedbackAddDTO, @CurrentUser User user);

}
