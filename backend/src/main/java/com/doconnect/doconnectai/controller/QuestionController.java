package com.doconnect.doconnectai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectai.dto.QuestionRequest;
import com.doconnect.doconnectai.dto.QuestionResponse;
import com.doconnect.doconnectai.service.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public String createQuestion(
            @Valid @RequestBody QuestionRequest request) {

        return questionService.createQuestion(request);
    }

    @GetMapping
    public List<QuestionResponse> getAllQuestions() {

        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionResponse getQuestionById(
            @PathVariable Long id) {

        return questionService.getQuestionById(id);
    }
}