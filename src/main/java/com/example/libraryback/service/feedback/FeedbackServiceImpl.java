package com.example.libraryback.service.feedback;

import com.example.libraryback.entity.Feedback;
import com.example.libraryback.entity.User;
import com.example.libraryback.exceptions.RestException;
import com.example.libraryback.payload.UserDTO;
import com.example.libraryback.payload.UserDTO2;
import com.example.libraryback.payload.api.ApiResult;
import com.example.libraryback.payload.feedback.FeedbackAddDTO;
import com.example.libraryback.payload.feedback.FeedbackDTO;
import com.example.libraryback.payload.feedback.TestimonialDTO;
import com.example.libraryback.repository.FeedbackRepository;
import com.example.libraryback.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final UserServiceImpl userService;

    private final FeedbackRepository feedbackRepository;

    @Override
    public ApiResult<?> delete(UUID id) {

        checkExist(id);

        feedbackRepository.deleteById(id);

        return ApiResult.successResponse();
    }

    @Override
    public ApiResult<List<FeedbackDTO>> get() {

        List<Feedback> feedbacks = feedbackRepository.findAll();

        return ApiResult
                .successResponse(
                        mapFeedbackDTO(feedbacks));
    }

    @Override
    public ApiResult<FeedbackDTO> get(UUID id) {

        Feedback feedback = checkExist(id);

        return ApiResult
                .successResponse(
                        mapFeedbackDTO(feedback));
    }

    @Override
    public ApiResult<TestimonialDTO> getTestimonials(Integer page, Integer size) {
        Long count = getCount();

        List<FeedbackDTO> feedbacks = getFeedbacks(page, size);

        return ApiResult
                .successResponse(TestimonialDTO.builder()
                        .count(count)
                        .more(count > 5)
                        .feedbacks(feedbacks)
                        .latestUsers(getLatestUsers(feedbacks))
                        .build());
    }

    @Override
    public ApiResult<FeedbackDTO> add(FeedbackAddDTO feedbackAddDTO, User user) {

        Feedback feedback = mapFeedback(feedbackAddDTO, user);

        feedbackRepository.save(feedback);

        return ApiResult
                .successResponse(
                        mapFeedbackDTO(feedback));
    }

    @Override
    public ApiResult<FeedbackDTO> edit(UUID id, FeedbackAddDTO feedbackAddDTO, User user) {

        checkExist(id);

        Feedback feedback = mapFeedback(feedbackAddDTO, user);

        feedback.setId(id);

        feedbackRepository.save(feedback);

        return ApiResult
                .successResponse(
                        mapFeedbackDTO(feedback));
    }



    private Long getCount() {
        return feedbackRepository.count();
    }

    private Feedback checkExist(UUID id) {

        return feedbackRepository.findById(id)
                .orElseThrow(() ->
                        RestException
                                .restThrow("NO FEEDBACK FOUND", HttpStatus.NOT_FOUND));
    }

    private FeedbackDTO mapFeedbackDTO(Feedback feedback) {

        return FeedbackDTO
                .builder()
                .id(feedback.getId())
                .date(feedback.getDate())
                .point(feedback.getPoint())
                .message(feedback.getMessage())
                .user(UserDTO2.mapUserDTO(feedback.getUser()))
                .build();
    }

    private List<UserDTO2> getLatestUsers(List<FeedbackDTO> feedbacks) {

        return feedbacks.stream()
                .map(FeedbackDTO::getUser)
                .collect(Collectors.toList());
    }

    private List<FeedbackDTO> getFeedbacks(Integer page, Integer size) {
        List<Feedback> feedbacks = feedbackRepository.findByOrderByDateDesc(PageRequest.of(page, size));

        return feedbacks.stream()
                .map(this::mapFeedbackDTO)
                .collect(Collectors.toList());
    }

    private Feedback mapFeedback(FeedbackAddDTO feedbackAddDTO, User user) {

        return Feedback
                .builder()
                .user(user)
                .date(new Date())
                .point(feedbackAddDTO.getPoint())
                .message(feedbackAddDTO.getMessage())
                .build();
    }

    private List<FeedbackDTO> mapFeedbackDTO(List<Feedback> feedbacks) {
        return feedbacks
                .stream()
                .map(this::mapFeedbackDTO)
                .collect(Collectors.toList());
    }
}
