package com.learnsphere.controller;
import com.learnsphere.dto.QuizResponse;
import com.learnsphere.dto.QuizSubmitRequest;
import com.learnsphere.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
@CrossOrigin(origins = {"*", "*"})
public class QuizController {
private final QuizService quizService;
@GetMapping("/{domainId}")
    public ResponseEntity<List<QuizResponse>> getQuizzesByDomain(@PathVariable Long domainId) {
        List<QuizResponse> quizzes = quizService.getQuizzesByDomain(domainId);
        return ResponseEntity.ok(quizzes);
    }
@GetMapping("/{id}/detail")
    public ResponseEntity<QuizResponse> getQuizById(@PathVariable Long id) {
        QuizResponse quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }
@PostMapping("/{quizId}/submit")
    public ResponseEntity<Map<String, Object>> submitQuizAnswers(
            @PathVariable Long quizId,
            @RequestBody QuizSubmitRequest request) {
        Map<String, Object> result = quizService.submitQuizAnswers(quizId, request);
        return ResponseEntity.ok(result);
    }
}
