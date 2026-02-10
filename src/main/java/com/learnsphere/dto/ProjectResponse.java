package com.learnsphere.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private String difficulty;
    private Integer estimatedHours;
    private String starterHtml;
    private String starterCss;
    private String starterJs;
    private List<String> resources;
}
