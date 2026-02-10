package com.learnsphere.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowResponse {
    private Long projectId;
    private String projectName;
    private List<WorkflowStepResponse> steps;
}

