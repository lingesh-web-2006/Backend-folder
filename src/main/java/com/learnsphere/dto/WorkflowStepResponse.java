package com.learnsphere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowStepResponse {
    private Long id;
    private String name;
    private String description;
    private Integer stepOrder;
    private Boolean completed;
}
