package com.doconnect.doconnectai.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectai.dto.QuestionRequest;
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

    public List<Question> getAllQuestions() {

        return questionRepository.findAll();
    }
}