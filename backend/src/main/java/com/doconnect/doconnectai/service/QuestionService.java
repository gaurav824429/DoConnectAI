package com.doconnect.doconnectai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectai.dto.QuestionRequest;
import com.doconnect.doconnectai.dto.QuestionResponse;
import com.doconnect.doconnectai.entity.Question;
import com.doconnect.doconnectai.entity.User;
import com.doconnect.doconnectai.repository.QuestionRepository;
import com.doconnect.doconnectai.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public String createQuestion(
            QuestionRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Question question = Question.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        questionRepository.save(question);

        return "Question Created Successfully";
    }

    public List<QuestionResponse> getAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public QuestionResponse getQuestionById(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Question Not Found"));

        return convertToResponse(question);
    }

    public List<QuestionResponse> getMyQuestions() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        return questionRepository.findByUserEmail(email)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private QuestionResponse convertToResponse(
            Question question) {

        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .description(question.getDescription())
                .author(
                        question.getUser() != null
                                ? question.getUser().getName()
                                : "Unknown"
                )
                .createdAt(question.getCreatedAt())
                .build();
    }
}