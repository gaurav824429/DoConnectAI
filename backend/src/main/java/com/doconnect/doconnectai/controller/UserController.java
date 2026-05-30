package com.doconnect.doconnectai.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnect.doconnectai.dto.QuestionResponse;
import com.doconnect.doconnectai.entity.User;
import com.doconnect.doconnectai.service.QuestionService;
import com.doconnect.doconnectai.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserProfileService userProfileService;
    private final QuestionService questionService;

    @GetMapping("/me")
    public User getCurrentUser() {

        return userProfileService.getCurrentUser();
    }

    @GetMapping("/my-questions")
    public List<QuestionResponse> getMyQuestions() {

        return questionService.getMyQuestions();
    }
}