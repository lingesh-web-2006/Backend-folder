package com.learnsphere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizSubmitRequest {
    private java.util.Map<String, String> answers;
}
