package com.learnsphere.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgressResponse {
    private Long userId;
    private Integer totalSkillsLearned;
    private Integer totalProjectsCompleted;
    private Integer totalQuizzesCompleted;
    private Integer currentStreak;
    private Integer totalPoints;
}
