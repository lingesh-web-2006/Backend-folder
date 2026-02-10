package com.learnsphere.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private Integer totalSkillsLearned;
    private Integer totalProjectsCompleted;
    private Integer totalQuizzesCompleted;
    private Integer currentStreak;
    private Integer totalPoints;
    private Long lastActivityDate;

    @PrePersist
    protected void onCreate() {
        totalSkillsLearned = 0;
        totalProjectsCompleted = 0;
        totalQuizzesCompleted = 0;
        currentStreak = 0;
        totalPoints = 0;
        lastActivityDate = System.currentTimeMillis();
    }
}
