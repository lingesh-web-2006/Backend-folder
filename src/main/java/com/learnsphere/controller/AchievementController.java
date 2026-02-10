package com.learnsphere.controller;

import com.learnsphere.entity.User;
import com.learnsphere.repository.AchievementRepository;
import com.learnsphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class AchievementController {

    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<?>> getUserAchievements() {
        Long userId = getCurrentUserId();
        List<?> achievements = achievementRepository.findByUserId(userId);
        return ResponseEntity.ok(achievements);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
