package com.learnsphere.controller;

import com.learnsphere.dto.UserProgressResponse;
import com.learnsphere.entity.User;
import com.learnsphere.service.UserProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.learnsphere.repository.UserRepository;
@RestController
@RequestMapping("/user-progress")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*", "*"})
public class UserProgressController {
private final UserProgressService userProgressService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<UserProgressResponse> getUserProgress() {
        Long userId = getCurrentUserId();
        UserProgressResponse progress = userProgressService.getUserProgress(userId);
        return ResponseEntity.ok(progress);
    }

    @PostMapping
    public ResponseEntity<UserProgressResponse> updateUserProgress(@RequestBody UserProgressResponse progressRequest) {
        Long userId = getCurrentUserId();
        UserProgressResponse progress = userProgressService.updateUserProgress(userId, progressRequest);
        return ResponseEntity.ok(progress);
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
