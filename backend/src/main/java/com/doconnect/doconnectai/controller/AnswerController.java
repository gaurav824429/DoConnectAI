package com.doconnect.doconnectai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectai.dto.AnswerRequest;
import com.doconnect.doconnectai.entity.Answer;
import com.doconnect.doconnectai.service.AnswerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public String createAnswer(
            @Valid @RequestBody AnswerRequest request) {

        return answerService.createAnswer(request);
    }

    @GetMapping("/question/{questionId}")
    public List<Answer> getAnswersByQuestion(
            @PathVariable Long questionId) {

        return answerService
                .getAnswersByQuestion(questionId);
    }
}