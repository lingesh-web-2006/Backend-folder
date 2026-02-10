package com.learnsphere.service;

import com.learnsphere.dto.UserProgressResponse;
import com.learnsphere.entity.UserProgress;
import com.learnsphere.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProgressService {

    private final UserProgressRepository userProgressRepository;

    public UserProgressResponse getUserProgress(Long userId) {
        UserProgress progress = userProgressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User progress not found"));

        return mapToUserProgressResponse(progress);
    }

    @Transactional
    public UserProgressResponse updateUserProgress(Long userId, UserProgressResponse progressRequest) {
        UserProgress progress = userProgressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User progress not found"));

        progress.setTotalSkillsLearned(progressRequest.getTotalSkillsLearned());
        progress.setTotalProjectsCompleted(progressRequest.getTotalProjectsCompleted());
        progress.setTotalQuizzesCompleted(progressRequest.getTotalQuizzesCompleted());
        progress.setCurrentStreak(progressRequest.getCurrentStreak());
        progress.setTotalPoints(progressRequest.getTotalPoints());
        progress.setLastActivityDate(System.currentTimeMillis());

        progress = userProgressRepository.save(progress);

        return mapToUserProgressResponse(progress);
    }

    @Transactional
    public void completeProject(Long userId) {
        UserProgress progress = userProgressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User progress not found"));

        progress.setTotalProjectsCompleted(progress.getTotalProjectsCompleted() + 1);
        progress.setTotalPoints(progress.getTotalPoints() + 50);
        progress.setLastActivityDate(System.currentTimeMillis());

        userProgressRepository.save(progress);
    }

    @Transactional
    public void completeQuiz(Long userId, double score) {
        UserProgress progress = userProgressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User progress not found"));

        if (score >= 60) {
            progress.setTotalQuizzesCompleted(progress.getTotalQuizzesCompleted() + 1);
            progress.setTotalPoints(progress.getTotalPoints() + (int) score);
        }
        progress.setLastActivityDate(System.currentTimeMillis());

        userProgressRepository.save(progress);
    }

    private UserProgressResponse mapToUserProgressResponse(UserProgress progress) {
        return UserProgressResponse.builder()
                .userId(progress.getUser().getId())
                .totalSkillsLearned(progress.getTotalSkillsLearned())
                .totalProjectsCompleted(progress.getTotalProjectsCompleted())
                .totalQuizzesCompleted(progress.getTotalQuizzesCompleted())
                .currentStreak(progress.getCurrentStreak())
                .totalPoints(progress.getTotalPoints())
                .build();
    }
}
