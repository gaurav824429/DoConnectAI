package com.doconnect.doconnectai.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.doconnect.doconnectai.dto.QuestionRequest;
import com.doconnect.doconnectai.entity.Question;
import com.doconnect.doconnectai.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public String createQuestion(
            QuestionRequest request) {

        Question question = Question.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();

        questionRepository.save(question);

        return "Question Created Successfully";
    }
}