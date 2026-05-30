package com.doconnect.doconnectai.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doconnect.doconnectai.dto.AnswerRequest;
import com.doconnect.doconnectai.entity.Answer;
import com.doconnect.doconnectai.entity.Question;
import com.doconnect.doconnectai.entity.User;
import com.doconnect.doconnectai.repository.AnswerRepository;
import com.doconnect.doconnectai.repository.QuestionRepository;
import com.doconnect.doconnectai.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public String createAnswer(
            AnswerRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Question question = questionRepository
                .findById(request.getQuestionId())
                .orElseThrow(() ->
                        new RuntimeException("Question not found"));

        Answer answer = Answer.builder()
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .user(user)
                .question(question)
                .build();

        answerRepository.save(answer);

        return "Answer Created Successfully";
    }

    public List<Answer> getAnswersByQuestion(
            Long questionId) {

        return answerRepository
                .findByQuestionId(questionId);
    }
}