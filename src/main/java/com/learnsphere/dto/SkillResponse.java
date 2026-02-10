package com.learnsphere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillResponse {
    private Long id;
    private String name;
    private String description;
    private String level;
    private Integer importance;
}
