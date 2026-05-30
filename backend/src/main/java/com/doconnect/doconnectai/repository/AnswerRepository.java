package com.doconnect.doconnectai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doconnect.doconnectai.entity.Answer;

public interface AnswerRepository
        extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(Long questionId);
}