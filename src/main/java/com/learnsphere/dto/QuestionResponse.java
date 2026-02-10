package com.learnsphere.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private Long id;
    private String question;
    private List<String> options;
    private String correctAnswer;
    private String hint;
}
