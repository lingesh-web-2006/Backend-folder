package com.learnsphere.service;

import com.learnsphere.dto.QuizResponse;
import com.learnsphere.dto.QuestionResponse;
import com.learnsphere.dto.QuizSubmitRequest;
import com.learnsphere.entity.Quiz;
import com.learnsphere.entity.Question;
import com.learnsphere.repository.QuizRepository;
import com.learnsphere.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public List<QuizResponse> getQuizzesByDomain(Long domainId) {
        return quizRepository.findByDomainId(domainId).stream()
                .map(this::mapToQuizResponse)
                .collect(Collectors.toList());
    }

    public QuizResponse getQuizById(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        return mapToQuizResponse(quiz);
    }

    public Map<String, Object> submitQuizAnswers(Long quizId, QuizSubmitRequest request) {
        List<Question> questions = questionRepository.findByQuizId(quizId);
        
        int correctCount = 0;
        for (Question question : questions) {
            String userAnswer = request.getAnswers().get(question.getId().toString());
            if (userAnswer != null && userAnswer.equals(question.getCorrectAnswer())) {
                correctCount++;
            }
        }

        double score = (double) correctCount / questions.size() * 100;

        return Map.of(
                "quizId", quizId,
                "totalQuestions", questions.size(),
                "correctAnswers", correctCount,
                "score", score,
                "passed", score >= 60
        );
    }

    private QuizResponse mapToQuizResponse(Quiz quiz) {
        List<Question> questions = questionRepository.findByQuizId(quiz.getId());

        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .questionCount(quiz.getQuestionCount())
                .estimatedTime(quiz.getEstimatedTime())
                .questions(questions.stream()
                        .map(q -> QuestionResponse.builder()
                                .id(q.getId())
                                .question(q.getQuestion())
                                .options(q.getOptions())
                                .correctAnswer(q.getCorrectAnswer())
                                .hint(q.getHint())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
