package com.learnsphere.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {
    private Long id;
    private String title;
    private String description;
    private Integer questionCount;
    private Integer estimatedTime;
    private List<QuestionResponse> questions;
}
