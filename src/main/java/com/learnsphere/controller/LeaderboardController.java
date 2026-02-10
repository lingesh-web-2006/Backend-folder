package com.learnsphere.controller;
import com.learnsphere.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*", "*"})
public class LeaderboardController {
 private final UserProgressRepository userProgressRepository;
@GetMapping
    public ResponseEntity<List<?>> getLeaderboard() {
        List<?> leaderboard = userProgressRepository.findAll();
        return ResponseEntity.ok(leaderboard);
    }
}
