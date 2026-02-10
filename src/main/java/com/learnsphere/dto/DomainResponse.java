package com.learnsphere.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainResponse {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private String difficulty;
    private Integer skillCount;
    private List<String> topSkills;
    private List<SkillResponse> skills;
}
