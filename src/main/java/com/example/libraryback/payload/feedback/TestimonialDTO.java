package com.example.libraryback.payload.feedback;

import com.example.libraryback.payload.UserDTO2;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestimonialDTO {

    private Long count;

    private boolean more;

    private List<UserDTO2> latestUsers;

    private List<FeedbackDTO> feedbacks;

}
